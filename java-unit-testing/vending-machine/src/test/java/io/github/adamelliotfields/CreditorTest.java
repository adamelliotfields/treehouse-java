package io.github.adamelliotfields;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CreditorTest {
  private Creditor creditor;

  @Before
  // Test set up
  // Runs before each test case
  public void setUp() throws Exception {
    // Arrange
    creditor = new Creditor();
  }

  @Test
  public void addingFundsIncrementsAvailableFunds() throws Exception {
    // Act
    creditor.addFunds(25);
    creditor.addFunds(25);

    // Assert
    // Only use one assertion for each test case
    // First parameter is expected value, second is actual value
    assertEquals(50, creditor.getAvailableFunds());
  }

  @Test
  public void refundingReturnsAllAvailableFunds() throws Exception {
    creditor.addFunds(10);

    assertEquals(10, creditor.refund());
  }

  @Test
  public void refundingResetsAvailableFundsToZero() throws Exception {
    creditor.addFunds(10);
    creditor.refund();

    assertEquals(0, creditor.getAvailableFunds());
  }

  @Test(expected = NotEnoughFundsException.class)
  public void deductingFundsGreaterThanAvailableFundsIsNotAllowed() throws Exception {
    creditor.deduct(10);
  }
}
