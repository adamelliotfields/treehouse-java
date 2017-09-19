package io.github.adamelliotfields.form;

import io.github.adamelliotfields.document.Category;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class GifForm {
  private MultipartFile file;

  @NotNull
  private String description;

  @NotNull
  private Category category;
}
