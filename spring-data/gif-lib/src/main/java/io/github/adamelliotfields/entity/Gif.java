package io.github.adamelliotfields.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import lombok.val;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Gif {
  @Id
  private String id;

  // Name this field "file" so it aligns with the GifForm DTO
  private Binary file;
  private String description;

  // DBRef annotation is required for the repository query to work
  // Lazy loading only loads the referenced Category document when requested
  @DBRef(lazy = true)
  private Category category;

  private LocalDateTime dateUploaded = LocalDateTime.now();
  private String username = "You";
  private boolean favorite = false;

  public Gif(Binary file, String description, Category category) {
    this.file = file;
    this.description = description;
    this.category = category;
  }

  // Returns "time since uploaded" for the GIF Details template
  public String getTimeSinceUploaded() {
    String unit;
    long time;

    val now = LocalDateTime.now();
    val seconds = ChronoUnit.SECONDS.between(dateUploaded, now);
    val minutes = ChronoUnit.MINUTES.between(dateUploaded, now);
    val hours = ChronoUnit.HOURS.between(dateUploaded, now);
    val days = ChronoUnit.DAYS.between(dateUploaded, now);
    val months = ChronoUnit.MONTHS.between(dateUploaded, now);
    val years = ChronoUnit.YEARS.between(dateUploaded, now);

    if (seconds < 60) {
      time = seconds;
      unit = "seconds";
    } else if (minutes < 60) {
      time = minutes;
      unit = "minutes";
    } else if (hours < 24) {
      time = hours;
      unit = "hours";
    } else if (days < 30) {
      time = days;
      unit = "days";
    } else if (months < 12) {
      time = months;
      unit = "months";
    } else {
      time = years;
      unit = "years";
    }

    return String.format("%d %s", time, unit);
  }
}
