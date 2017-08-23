package io.github.adamelliotfields;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import org.apache.tika.Tika;

public class App {
  private static final String FILE_TYPE = "text/csv";
  private static final String DIR_TO_WATCH = "C:\\Users\\Adam\\Downloads";

  public static void main(String[] args) throws Exception {
    Path dir = Paths.get(DIR_TO_WATCH);
    Tika tika = new Tika();
    WatchService watchService = FileSystems.getDefault().newWatchService();

    dir.register(watchService, ENTRY_CREATE);

    WatchKey key;
    do {
      key = watchService.take();

      key.pollEvents().stream()
          .filter(event -> {
            Path filename = (Path) event.context();
            String type = tika.detect(filename.toString());
            return FILE_TYPE.equals(type);
          })
          .forEach(event -> System.out.printf("File found: %s%n", event.context()));
    } while (key.reset());
  }
}
