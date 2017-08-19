public class Main {
  public static void main(String[] args) {
    // Instantiate a new Prompter object and prompt for the story template
    Prompter prompter = new Prompter();
    System.out.println("Please give me a story, with placeholders in between double underscores.");
    System.out.println("(e.g. '__name__', '__adjective__', '__noun__')");

    // Use the prompter object to have it do the prompting, censoring and outputting.  Call Prompter.run
    String story = prompter.promptForStory();
    Template template = new Template(story);

    // This should really happen in the Prompter.run method, let's get these implementation details out of the main method
    prompter.run(template);
  }
}
