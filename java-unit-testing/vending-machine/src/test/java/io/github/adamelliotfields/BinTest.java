package io.github.adamelliotfields;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinTest {
  private Bin bin;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    bin = new Bin(10);
  }

  @Test
  public void restockingWithDifferentItemsIsNotAllowed() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Cannot restock Cheetos with Doritos");

    bin.restock("Cheetos", 1, 100, 50);
    bin.restock("Doritos", 1, 100, 50);
  }

  @Test
  public void whenEmptyPriceIsZero() throws Exception {
    assertEquals(0, bin.getItemPrice());
  }

  @Test
  public void whenEmptyNameIsNull() throws Exception {
    assertNull(bin.getItemName());
  }

  @Test
  public void overstockingIsNotAllowed() throws Exception {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("There are only 10 spots left");

    bin.restock("Fritos", 2600, 100, 50);
  }
}
