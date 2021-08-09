package cafe;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws FileNotFoundException {

    Timeline timeline; 
    Scanner sc = new Scanner(System.in); // Creates scanner for inputting information
    String answer;

    // Cultural Implication Disclaimer
    System.out.println("Culutral sensitivity warning: May show offensive content");

    while (true) {

      System.out.println("The format for a file is a .csv file with a header in this order: Minute, Students joined, Staff joined, Customers Served");
      System.out.println("(OPEN) file, (GENERATE) random or (EXIT)"); // Asks the user what they would like to do

      sc = new Scanner(System.in); // Resets it so previous writing does not interfere with new input
      answer = sc.nextLine(); // Gets the users input

      // If the user types both open and generate
      if (answer.toLowerCase().contains("open") && answer.toLowerCase().contains("generate")) {
        System.out.println("You cannot open and generate a file at the same time");
        continue; // Stops the other if-statements from running
      }

      // If the user types close, leave the while loo
      if (answer.toLowerCase().contains("exit")) { 
        break; // Ends the loop, and thus closes the software
      }

      // If the user said they would like to open a file
      if (answer.toLowerCase().contains("open")) { 
        selectedOpen(sc); 
        continue; // Stops the other if-statements from running
      }

      if (answer.toLowerCase().contains("generate")) { // If the user typed in generate
        timeline = RandomTimeline.createRandomTimeline(); // Creates a random timeline
        openFile(timeline); // Runs the program on the randomly generated timeline
        continue; // Stops the other if-statements from running
      }
      System.out.println("Could not recognise your command, please try again");
    }
  }

  // This is run if the user types open to find out the file they would like to run, and then run it
  public static void selectedOpen (Scanner sc) throws FileNotFoundException {;

    System.out.println("Enter file's location"); // Asks for the location of the file
    sc.reset(); // Resets it so previous writing does not interfere with new input
    String path = sc.nextLine(); // Gets the file path

    // Asks whether they have permission to use the data before use
    System.out.println("If you are using data that was collected from real staff and students, have you been provided with the permission from those real staff and students to do so");
    
    sc.reset(); // Resets it so previous writing does not interfere with new input
    String isDataCollectedWithPermission = sc.nextLine(); // Gets answer to previous question

    if (isDataCollectedWithPermission.toLowerCase().contains("y")) { // Did the staff and students permit their data to be used

      Timeline timeline;

      try {
        timeline = FileAccessLayer.openFile(path); // Opens the file of the path the user inputted and transform it into
        // a timeline data     structure
      } catch (FileNotFoundException e) {
        System.out.println("Could not find file directory: " + path + ", please try again (cap-sensitive)");
        selectedOpen(sc);
        return;
      } 

      // Null check
      if (timeline == null) { 
        return;
      }
      
      // Runs the program on the timeline that the user opened
      openFile(timeline); 

    } else {
      // Reminds the user that they should not use data without permission
      System.out.println("Any data collected on real staff and students must only be used with the permission of the real staff and students, whose data was collected");
    }
  }

  public static void openFile(Timeline timeline) {

    long startTime = System.nanoTime(); // Starts timer for how long the simulation took

    Simulation sim = new Simulation(timeline); // Creates a simulation using the timeline that was openend

    Statistics pushedIn = sim.getSimulation(true); // Gets the statistics for the simulation (true means pushing in)
    System.out.println("\nQueue With Staff Pushing In");
    pushedIn.printStatistics(); // Print the statistics for the simulation in the pretty box

    Statistics notPushedIn = sim.getSimulation(false); // False means the staff are not pushing in
    System.out.println("\nQueue Without Staff Pushing In");
    notPushedIn.printStatistics(); // Print the statistics for the simulation in the pretty box

    long endTime = System.nanoTime(); // Stops timer

    int convertToMillisecondsFromNanoSeconds = 1000000; // Conversion rate from nano seconds to milliseconds
    long duration = (endTime - startTime) / convertToMillisecondsFromNanoSeconds; // Calculates execution time in
                                                                                  // nanoseconds and converts to ms

    System.out.println("");
    System.out.println("Simulated and Calculated in " + Long.valueOf(duration) + "ms"); // Prints how long it took to
                                                                                        // run the simulation simulation
    System.out.println("");
  }

}
