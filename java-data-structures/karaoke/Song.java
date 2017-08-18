package karaoke;

public class Song {
  protected String artist;
  protected String title;
  protected String url;

  public Song(String artist, String title, String url) {
    this.artist = artist;
    this.title = title;
    this.url = url;
  }

  public String getArtist() {
    return artist;
  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return String.format("Song: %s by %s%n", title, artist);
  }
}
