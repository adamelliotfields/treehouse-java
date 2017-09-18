package io.github.adamelliotfields.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
  private String username;
  private String password;
}
