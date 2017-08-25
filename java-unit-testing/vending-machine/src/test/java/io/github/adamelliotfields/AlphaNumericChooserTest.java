package io.github.adamelliotfields;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AlphaNumericChooserTest {
  private AlphaNumericChooser chooser;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    // Arrange
    chooser = new AlphaNumericChooser(26, 10);
  }

  @Test
  public void validInputReturnsProperLocation() throws Exception {
    // Act
    AlphaNumericChooser.Location location = chooser.locationFromInput("B4");

    // Assert
    assertEquals("proper row", 1, location.getRow());
    assertEquals("proper column", 3, location.getColumn());
  }

  // This tests that the method does throw
  @Test(expected = InvalidLocationException.class)
  public void choosingWrongInputIsNotAllowed() throws Exception {
    chooser.locationFromInput("WRONG");
  }

  @Test(expected = InvalidLocationException.class)
  public void choosingLargerThanMaxIsNotAllowed() throws Exception {
    chooser.locationFromInput("B52");
  }

  @Test
  public void constructingLargerThanAlphabetIsNotAllowed() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Maximum rows supported is 26");

    new AlphaNumericChooser(27, 10);
  }
}
