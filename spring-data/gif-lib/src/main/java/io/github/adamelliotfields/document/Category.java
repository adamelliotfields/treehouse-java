package io.github.adamelliotfields.document;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Category {
  @Id
  private String id;

  @NotNull
  @Size(min = 3, max = 12)
  private String name;

  @NotNull
  @Pattern(regexp = "#[0-9a-fA-F]{6}")
  private String colorCode;

  private List<Gif> gifs;

  public Category(String name, String colorCode) {
    this.name = name;
    this.colorCode = colorCode;
    this.gifs = new ArrayList<>();
  }
}
