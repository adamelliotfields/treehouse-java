package io.github.adamelliotfields;

public class Main {
  public static void main(String[] args) {
    SongBook songBook = new SongBook();

    String fileName = "songs.txt";

    songBook.importFrom(fileName);

    KaraokeMachine machine = new KaraokeMachine(songBook);

    machine.run();

    System.out.println("Saving song book...");

    songBook.exportTo(fileName);
  }
}
