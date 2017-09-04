package io.github.adamelliotfields.repository;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.adamelliotfields.model.CategoryModel;
import org.junit.Test;

public class CategoryRepositoryTest {
  private final CategoryRepository categoryRepository = new CategoryRepository();

  @Test
  public void findById() throws Exception {
    CategoryModel result = categoryRepository.findById(1);

    assertThat(result.getName()).isEqualToIgnoringCase("technology");
  }
}
