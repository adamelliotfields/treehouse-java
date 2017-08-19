package song;

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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Song song = (Song) o;

    if (artist != null) {
      if (!artist.equals(song.artist)) {
        return false;
      }
    } else {
      if (song.artist != null) {
        return false;
      }
    }

    if (title != null) {
      if (!title.equals(song.title)) {
        return false;
      }
    } else {
      if (song.title != null) {
        return false;
      }
    }

    return url != null
        ? url.equals(song.url)
        : song.url == null;
  }

  @Override
  public int hashCode() {
    int result = artist != null
        ? artist.hashCode()
        : 0;

    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (url != null ? url.hashCode() : 0);

    return result;
  }

  @Override
  public String toString() {
    return String.format("Song: %s by %s%n", title, artist);
  }
}
