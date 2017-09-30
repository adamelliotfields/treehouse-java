package io.github.adamelliotfields.form;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class GifForm {
  // We can't use validation here, as the user could send an empty file when updating the GIF
  private MultipartFile file;

  // We need to use NotEmpty here, as an empty string is technically "" and not null
  @NotEmpty(message = "Description field is required.")
  private String description;

  @NotNull(message = "Category field is required.")
  private CategoryForm category;
}
