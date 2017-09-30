package io.github.adamelliotfields.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CategoryForm {
  private String id;

  @NotEmpty(message = "You must enter a Category name.")
  @Size(min = 3, max = 12, message = "Category name must be between 3 and 12 characters.")
  private String name;

  @NotEmpty(message = "You must select a color code.")
  @Pattern(regexp = "#[0-9a-fA-F]{6}", message = "Invalid color code.")
  private String colorCode;
}
