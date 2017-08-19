package song;

public class SongRequest {
  private String singerName;
  private Song song;

  public SongRequest(String singerName, Song song) {
    this.singerName = singerName;
    this.song = song;
  }

  public String getSingerName() {
    return singerName;
  }

  public void setSingerName(String singerName) {
    this.singerName = singerName;
  }

  public Song getSong() {
    return song;
  }

  public void setSong(Song song) {
    this.song = song;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SongRequest that = (SongRequest) o;

    // if (!singerName.equals(that.singerName)) {
    //   return false;
    // }
    //
    // return song.equals(that.song);

    return singerName.equals(that.singerName) && song.equals(that.song);
  }

  @Override
  public int hashCode() {
    int result = singerName.hashCode();
    result = 31 * result + song.hashCode();

    return result;
  }

  @Override
  public String toString() {
    return "SongRequest{"
      + "singerName='" + singerName + '\''
      + ", song=" + song
      + '}';
  }
}
