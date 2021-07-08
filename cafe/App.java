package cafe;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws FileNotFoundException {

    Timeline timeline;
    Scanner sc = new Scanner(System.in);
    String answer;

    while (true) {

      System.out.println("(OPEN) file, (GENERATE) random or (EXIT)"); 

      sc.reset();
      answer = sc.nextLine();

      if (answer.toLowerCase().equals("exit")){ // If the user types close, leave the while loop
        break;
      }

      if (answer.toLowerCase().equals("open")){

        System.out.println("Enter file's location");
        sc.reset();
        String path = sc.nextLine(); // Gets the file path
    
        timeline = FileAccessLayer.openFile(path); // Opens the file of the path the user inputted and transform it into a timeline data structure
        openFile(timeline);
      }

      if (answer.toLowerCase().equals("generate")){
        timeline = RandomTimeline.createRandomTimeline();
        openFile(timeline);
      }
    }
    sc.close();
  }

  public static void openFile (Timeline timeline) {

    long startTime = System.nanoTime(); // Starts timer

    Simulation sim = new Simulation(timeline); // Creates a simulation using the timeline that was openend
    
    Statistics pushedIn = sim.getSimulation(true); // Gets the statistics for the simulation (true means pushing in)
    System.out.println("\nQueue With Staff Pushing In");
    pushedIn.printStatistics();
    
    Statistics notPushedIn = sim.getSimulation(false); // False means the staff are not pushing in
    System.out.println("\nQueue Without Staff Pushing In");
    notPushedIn.printStatistics();

    long endTime = System.nanoTime(); // Stops timer

    int convertToMillisecondsFromNanoSeconds = 1000000;
    long duration = (endTime - startTime) / convertToMillisecondsFromNanoSeconds; // Calculates execution time in nanoseconds and converts to ms

    System.out.println("");
    System.out.println("Simulated and Calculated in " + Long.valueOf(duration) + "ms"); // Prints how long it took to run simulation
    System.out.println("");
  }


  
}
