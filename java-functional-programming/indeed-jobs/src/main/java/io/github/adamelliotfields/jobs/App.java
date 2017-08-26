package io.github.adamelliotfields.jobs;

import io.github.adamelliotfields.jobs.model.Job;
import io.github.adamelliotfields.jobs.service.JobService;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
  private static JobService service = new JobService();
  private static List<Job> jobs;

  private static void getJobs(boolean shouldRefresh) {
    try {
      if (shouldRefresh) {
        service.refresh();
      }

      jobs = service.loadJobs();

      System.out.println("Total jobs: " + jobs.size());
      System.out.println();
    } catch (IOException error) {
      System.out.println("Error: Could not load jobs...");
      error.printStackTrace();
    }
  }

  private static void filterJobsByStateAndCity(String state, String city) {
    jobs.stream()
        .filter(job -> job.getState().equals(state))
        .filter(job -> job.getCity().equals(city))
        .forEach(System.out::println);
  }

  // Predicate function
  private static boolean isJuniorJob(Job job) {
    String title = job.getTitle().toLowerCase();

    return title.contains("junior") || title.contains("jr");
  }

  private static List<String> getJuniorJobs(int limit) {
    return jobs.stream()
               .filter(App::isJuniorJob)
               .map(Job::getCaption)
               .limit(limit)
               .collect(Collectors.toList());
  }

  private static Map<String, Long> getSnippetWordCounts() {
    return jobs.stream()
               .map(Job::getSnippet)
               .map(snippet -> snippet.split("\\W+"))
               .flatMap(Stream::of)
               .filter(word -> word.length() > 0)
               .map(String::toLowerCase)
               .collect(Collectors.groupingBy(
                   Function.identity(),
                   Collectors.counting()
               ));
  }

  private static Optional<String> getLongestCompanyName() {
    return jobs.stream()
               .map(Job::getCompany)
               .max(Comparator.comparingInt(String::length));
  }

  private static String getFirstMatchingJob(String string) {
    return jobs.stream()
               .filter(job -> job.getTitle().contains(string.toLowerCase()))
               // Returns an optional
               .findFirst()
               // Optionals have a map method
               .map(Job::getTitle)
               // Return a string if the optional is empty
               .orElse("No job found");
  }

  private static Stream<String> getCompanyNames(int limit) {
    List<String> companies = jobs.stream()
                                 .map(Job::getCompany)
                                 .distinct()
                                 .sorted()
                                 .collect(Collectors.toList());

    return IntStream.rangeClosed(1, limit)
             .mapToObj(i -> String.format("%d. %s", i, companies.get(i - 1)));
  }

  public static void main(String[] args) {
    getJobs(false);

    System.out.println("Boston, MA Jobs:");

    filterJobsByStateAndCity("MA", "Boston");

    System.out.println();
    System.out.println("Junior Jobs:");

    getJuniorJobs(5).forEach(System.out::println);

    // System.out.println();
    // System.out.println("Word Counts:");

    // getSnippetWordCounts().forEach((key, value) -> System.out.println(key + ": " + value));

    System.out.println();
    System.out.println("Longest Company Name:");

    System.out.println(getLongestCompanyName());

    System.out.println();
    System.out.println("First Matching Java Job:");

    System.out.println(getFirstMatchingJob("Java"));

    // System.out.println();
    // System.out.println("First 20 Companies:");

    // getCompanyNames(20).forEach(System.out::println);
  }
}
