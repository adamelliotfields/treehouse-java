package io.github.adamelliotfields.domain;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Size.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Category {
  @Id private String id;

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
    this.gifs = new ArrayList<Gif>();
  }
}
