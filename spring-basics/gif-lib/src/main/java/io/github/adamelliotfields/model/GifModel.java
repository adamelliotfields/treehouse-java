package io.github.adamelliotfields.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;

@Data
public class GifModel {
  @NonNull private final int categoryId;
  @NonNull private final String name;
  @NonNull private final LocalDate uploadDate;
  @NonNull private final String username;
  @NonNull private final boolean favorite;
}
