package io.github.adamelliotfields;

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

  public Song getSong() {
    return song;
  }
}
