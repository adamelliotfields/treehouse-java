package io.github.adamelliotfields;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

public class SongBook {
  private List<Song> songs;

  public SongBook() {
    songs = new ArrayList<>();
  }

  public void addSong(Song song) {
    songs.add(song);
  }

  public int getSongCount() {
    return songs.size();
  }

  public void exportTo(String fileName) {
    String dirName = System.getProperty("user.dir");
    String pathName = dirName + File.separator + fileName;

    try (
      FileOutputStream fileOutputStream = new FileOutputStream(pathName);
      PrintWriter printWriter = new PrintWriter(fileOutputStream)
    ) {
      songs.forEach(song -> {
        String karaokeSong = song.getArtist() + "|" + song.getTitle() + "|" + song.getUrl();
        printWriter.println(karaokeSong);
      });
    } catch (IOException error) {
      System.err.println("Error: Unable to write to " + fileName);
    }
  }

  public void importFrom(String fileName) {
    String dirName = System.getProperty("user.dir");
    String pathName = dirName + File.separator + fileName;

    Path path = Paths.get(pathName);

    try (Stream<String> stream = Files.lines(path)) {
      stream.forEach(line -> {
        String[] args = line.split("\\|");
        addSong(new Song(args[0], args[1], args[2]));
      });
    } catch (IOException error) {
      System.err.println("Error: Could not read file " + fileName);
    }
  }

  private Map<String, List<Song>> byArtist() {
    Map<String, List<Song>> byArtist = new TreeMap<>();

    for (Song song : songs) {
      // if (artistSongs == null) {
      //   artistSongs = new ArrayList<>();
      //
      //   byArtist.put(song.getArtist(), artistSongs);
      // }

      List<Song> artistSongs = byArtist.computeIfAbsent(song.getArtist(), k -> new ArrayList<>());

      artistSongs.add(song);
    }

    return byArtist;
  }

  public Set<String> getArtists() {
    return byArtist().keySet();
  }

  public List<Song> getSongsForArtist(String artistName) {
    List<Song> songs =  byArtist().get(artistName);

    // songs.sort(new Comparator<Song>() {
    //   @Override
    //   public int compare(Song song1, Song song2) {
    //     if (song1.equals(song2)) {
    //       return 0;
    //     }
    //     return song1.title.compareTo(song2.title);
    //   }
    // });

    songs.sort((song1, song2) -> {
      if (song1.equals(song2)) {
        return 0;
      }
      return song1.title.compareTo(song2.title);
    });

    return songs;
  }
}
