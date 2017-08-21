package song;

import lombok.Getter;

public class Song {
  @Getter
  protected String artist;

  @Getter
  protected String title;

  @Getter
  protected String url;

  public Song(String artist, String title, String url) {
    this.artist = artist;
    this.title = title;
    this.url = url;
  }

  @Override
  public String toString() {
    return String.format("Song: %s by %s%n", title, artist);
  }
}
