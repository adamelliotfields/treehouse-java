package karaoke;

import java.util.ArrayList;
import java.util.List;

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
}
