package cafe;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class App {

  public static void main(String[] args) throws FileNotFoundException {

    Timeline timeline;
    Simulation sim;
    Scanner sc = new Scanner(System.in);
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    while (true) {

      System.out.println("Please enter the path of a .csv file to simulate or enter 'CLOSE'"); 
      String path = sc.nextLine(); // Gets the file path

      if (path.toLowerCase().equals("close")){ // If the user types close, leave the while loop
        break;
      }

      long startTime = System.nanoTime();

      timeline = FileAccessLayer.openFile(path); // Opens the file of the path the user inputted and transform it into a timeline data structure
      sim = new Simulation(timeline); // Creates a simulation using the timeline that was openend
      
      Statistics pushedIn = sim.getPushedInSimulation(); // Gets the statistics for the simulation
      System.out.println("Mean student wait time in queue with staff pushing in: " + decimalFormat.format(pushedIn.getMeanStudentWaitTime()));
      System.out.println("Mean staff wait time in queue with staff pushing in: " + decimalFormat.format(pushedIn.getMeanStaffWaitTime()));
      System.out.println("Median student wait time in queue with staff pushing in: " + decimalFormat.format(pushedIn.getMedianStudentWaitTime()));
      System.out.println("Median staff wait time in queue with staff pushing in: " + decimalFormat.format(pushedIn.getMedianStaffWaitTime()));
      System.out.println("Mode student wait time in queue with staff pushing in: " + decimalFormat.format(pushedIn.getModeStudentWaitTime()));
      System.out.println("Mode staff wait time in queue with staff pushing in: " + decimalFormat.format(pushedIn.getModeStaffWaitTime()));
      
      System.out.println("The range of student wait times in queue with staff pushing in: " + decimalFormat.format(pushedIn.getRangeOfStudentWaitTimes()));
      System.out.println("The range of staff wait times in queue with staff pushing in: " + decimalFormat.format(pushedIn.getRangeOfStaffWaitTimes()));
      System.out.println("Standard deviation of student wait time with staff pushing in was: " + decimalFormat.format(pushedIn.getStudentStandardDeviation()));
      System.out.println("Standard deviation of staff wait time was with staff pushing in was: " + decimalFormat.format(pushedIn.getStaffStandardDeviation()));
      
      
      Statistics notPushedIn = sim.getNotPushedInSimulation();
      System.out.println("Mean student wait time in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getMeanStudentWaitTime()));
      System.out.println("Mean staff wait time in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getMeanStaffWaitTime()));
      System.out.println("Median student wait time in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getMedianStudentWaitTime()));
      System.out.println("Median staff wait time in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getMedianStaffWaitTime()));
      System.out.println("Mode student wait time in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getModeStudentWaitTime()));
      System.out.println("Mode staff wait time in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getModeStaffWaitTime()));
      
      System.out.println("The range of student wait times in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getRangeOfStudentWaitTimes()));
      System.out.println("The range of staff wait times in queue with staff not pushing in: " + decimalFormat.format(notPushedIn.getRangeOfStaffWaitTimes()));
      System.out.println("Standard deviation of student wait time with staff not pushing in was: " + decimalFormat.format(pushedIn.getStudentStandardDeviation()));
      System.out.println("Standard deviation of staff wait time with staff not pushing in was: " + decimalFormat.format(pushedIn.getStaffStandardDeviation()));
      
      long endTime = System.nanoTime();

      int convertToMillisecondsFromNanoSeconds = 1000000;
      long duration = (endTime - startTime) / convertToMillisecondsFromNanoSeconds;

      System.out.println("Calculated in " + Long.valueOf(duration) + "ms");
    }

    sc.close();
  }


  
}
