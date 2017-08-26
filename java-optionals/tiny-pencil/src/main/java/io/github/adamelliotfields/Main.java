package io.github.adamelliotfields;

public class Main {
  public static void main(String[] args) {
    Party party = new Party("Andrew", "Craig", "Dave", "Alena");
    Helpers.randomizeParty(party);

    Dashboard dashboard = new Dashboard();
    dashboard.displayWinner(party);
    dashboard.displaySummary(party);
    dashboard.displayPartyDetail(party);
  }
}
