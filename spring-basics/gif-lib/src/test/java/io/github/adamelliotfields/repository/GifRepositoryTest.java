package io.github.adamelliotfields.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GifRepositoryTest {
  private final GifRepository gifRepository = new GifRepository();

  @Test
  public void findByCategoryId() throws Exception {
    int actual = gifRepository.findByCategoryId(2).size();
    int expected = 3;

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void findByName() throws Exception {
    String actual = gifRepository.findByName("android-explosion").getName();
    String expected = "android-explosion";

    assertThat(actual).isEqualToIgnoringCase(expected);
  }
}
