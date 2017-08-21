package song;

import lombok.Getter;

public class SongRequest {
  @Getter
  private String singerName;

  @Getter
  private Song song;

  public SongRequest(String singerName, Song song) {
    this.singerName = singerName;
    this.song = song;
  }
}
