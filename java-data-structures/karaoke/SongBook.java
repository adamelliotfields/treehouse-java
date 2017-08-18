package karaoke;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
// import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
    try (
      FileOutputStream fileOutputStream = new FileOutputStream(fileName);
      PrintWriter printWriter = new PrintWriter(fileOutputStream);
    ) {
      for (Song song : songs) {
        printWriter.printf(
            "%s|%s|%s%n",
            song.getArtist(),
            song.getTitle(),
            song.getUrl()
        );
      }
    } catch (IOException error) {
      System.out.printf("Error writing to %s %n", fileName);
      error.printStackTrace();
    }
  }

  public void importFrom(String fileName) {
    try (
      FileInputStream fileInputStream = new FileInputStream(fileName);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    ) {
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        String[] args = line.split("\\|");
        addSong(new Song(args[0], args[1], args[2]));
      }
    } catch (IOException error) {
      System.out.printf("Problems loading %s %n", fileName);
      error.printStackTrace();
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
