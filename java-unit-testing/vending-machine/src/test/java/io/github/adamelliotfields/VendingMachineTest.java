package io.github.adamelliotfields;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
  private VendingMachine machine;

  // Inner class
  public class NotifierClass implements Notifier {

    @Override
    public void onSale(Item item) {
      return;
    }
  }

  @Before
  public void setUp() throws Exception {
    Notifier notifier = new NotifierClass();

    machine = new VendingMachine(notifier, 10, 10, 10);
    machine.restock("A1", "Twinkies", 10, 30, 75);
  }

  @Test
  public void vendingWhenStockedReturnsItem() throws Exception {
    machine.addMoney(75);

    Item item = machine.vend("A1");

    assertEquals("Twinkies", item.getName());
  }

  @Test
  public void vendingIncrementsRunningSalesTotal() throws Exception {
    machine.addMoney(75);

    machine.vend("A1");

    assertEquals(75, machine.getRunningSalesTotal());
  }
}
