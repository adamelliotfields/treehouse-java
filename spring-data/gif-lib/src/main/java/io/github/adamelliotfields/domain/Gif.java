package io.github.adamelliotfields.domain;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Gif {
  @Id private String id;

  private byte[] bytes;

  private String description;

  private Category category;
  private LocalDateTime dateUploaded = LocalDateTime.now();
  private String username = "You";
  private boolean favorite;
  private String hash;
}
