package io.github.adamelliotfields.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@Document
public class Gif {
  @Id
  private String id;

  private MultipartFile file;
  private String description;
  private Category category;
  private LocalDateTime dateUploaded = LocalDateTime.now();
  private String username = "You";
  private boolean favorite = false;

  public Gif(MultipartFile file, String description, Category category) {
    this.file = file;
    this.description = description;
    this.category = category;
  }

  public String getTimeSinceUploaded() {
    LocalDateTime now = LocalDateTime.now();
    String unit = "";
    long diff;

    if ((diff = ChronoUnit.SECONDS.between(dateUploaded, now)) < 60) {
      unit = "secs";
    } else if ((diff = ChronoUnit.MINUTES.between(dateUploaded, now)) < 60) {
      unit = "mins";
    } else if ((diff = ChronoUnit.HOURS.between(dateUploaded, now)) < 24) {
      unit = "hours";
    } else if ((diff = ChronoUnit.DAYS.between(dateUploaded, now)) < 30) {
      unit = "days";
    } else if ((diff = ChronoUnit.MONTHS.between(dateUploaded, now)) < 12) {
      unit = "months";
    } else {
      diff = ChronoUnit.YEARS.between(dateUploaded, now);
    }

    return String.format("%d %s", diff, unit);
  }
}
