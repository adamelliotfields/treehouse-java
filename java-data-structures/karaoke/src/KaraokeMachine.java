import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import song.Song;
import song.SongBook;
import song.SongRequest;

public class KaraokeMachine {
  private SongBook songBook;
  private BufferedReader reader;
  private Queue<SongRequest> songQueue;
  private Map<String, String> menu;

  public KaraokeMachine(SongBook songBook) {
    this.songBook = songBook;
    this.reader = new BufferedReader(new InputStreamReader(System.in));
    this.songQueue = new ArrayDeque<>();
    this.menu = new HashMap<>();

    menu.put("add", "Add a new song to the song book.");
    menu.put("choose", "Choose a song.");
    menu.put("play", "Play the next song in the queue.");
    menu.put("quit", "Exit the program.");
  }

  private String promptAction() throws IOException {
    System.out.printf(
        "There are %d songs available and %d in the queue. Your options are: %n",
        songBook.getSongCount(),
        songQueue.size()
    );

    menu.forEach((key, value) -> System.out.println(key + " - " + value));

    System.out.print("What do you want to do:  ");

    String choice = reader.readLine();

    return choice.trim().toLowerCase();
  }

  public void run() {
    String choice = "";

    do {
      try {
        choice = promptAction();

        switch (choice) {
          case "add":
            Song song = promptNewSong();

            songBook.addSong(song);

            System.out.printf("%s added successfully! %n%n", song);
            break;
          case "choose":
            String singerName = promptForSingerName();
            String artist = promptArtist();
            Song artistSong = promptSongForArtist(artist);

            SongRequest songRequest = new SongRequest(singerName, artistSong);

            if (songQueue.contains(songRequest)) {
              System.out.printf(
                  "%n%nWhoops! %s already requested %s %n",
                  singerName,
                  artistSong
              );
            }

            songQueue.add(songRequest);

            System.out.printf("%nYou chose: %s %n", artistSong);
            break;
          case "play":
            playNext();
            break;
          case "quit":
            System.out.println("Thanks for playing!");
            break;
          default:
            System.out.printf("Unknown choice: %s. Try again.%n%n", choice);
        }
      } catch (IOException error) {
        System.out.println("Problem with input.");
        error.printStackTrace();
      }
    } while (!choice.equals("quit"));
  }

  private String promptForSingerName() throws IOException {
    System.out.println("Enter the singer's name:  ");

    return reader.readLine();
  }

  private Song promptNewSong() throws IOException {
    System.out.print("Enter the artist's name:  ");
    String artist = reader.readLine();

    System.out.print("Enter the title:  ");
    String title = reader.readLine();

    System.out.print("Enter the YouTube URL:  ");
    String url = reader.readLine();

    return new Song(artist, title, url);
  }

  private String promptArtist() throws IOException {
    System.out.println("Available artists:");

    List<String> artists = new ArrayList<>(songBook.getArtists());

    int index = promptForIndex(artists);

    return artists.get(index);
  }

  private Song promptSongForArtist(String artist) throws IOException {
    List<Song> songs = songBook.getSongsForArtist(artist);
    List<String> songTitles = new ArrayList<>();

    songs.forEach(item -> songTitles.add(item.getTitle()));

    System.out.printf("Available songs for %s: %n", artist);

    int index = promptForIndex(songTitles);

    return songs.get(index);
  }

  private int promptForIndex(List<String> options) throws IOException {
    int counter = 1;

    for (String option : options) {
      System.out.println(counter + ". " + option);
      counter++;
    }

    System.out.print("Your choice:  ");
    String optionAsString = reader.readLine();

    int choice = Integer.parseInt(optionAsString.trim());

    return choice - 1;
  }

  public void playNext() {
    SongRequest songRequest = songQueue.poll();

    if (songRequest == null) {
      System.out.println("Sorry, there are no songs in the queue. Select 'choose' from the menu to add one.");
    } else {
      Song song = songRequest.getSong();

      System.out.printf(
          "%nReady %s? Open %s to hear %s by %s %n%n",
          songRequest.getSingerName(),
          song.getUrl(),
          song.getTitle(),
          song.getArtist()
      );
    }
  }
}
