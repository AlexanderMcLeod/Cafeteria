package cafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws FileNotFoundException {

    Timeline timeline;
    Simulation sim;
    Scanner sc = new Scanner(System.in);

    while (true) {

      System.out.println("Please enter the path of a .csv file to simulate or enter 'CLOSE'"); 
      String path = sc.nextLine(); // Gets the file path

      if (path.toLowerCase().equals("close")){ // If the user types close, leave the while loop
        break;
      }

      timeline = FileAccessLayer.openFile(path); // Opens the file of the path the user inputted and transform it into a timeline data structure
      sim = new Simulation(timeline); // Creates a simulation using the timeline that was openend
      
      Statistics pushedIn = sim.getPushedInSimulation(); // Gets the statistics for the simulation
      System.out.println("Mean student wait time in queue with staff pushing in: " + Double.valueOf(pushedIn.getMeanStudentWaitTime()));
      System.out.println("Mean staff wait time in queue with staff pushing in: " + pushedIn.getMeanStaffWaitTime());
      Statistics notPushedIn = sim.getNotPushedInSimulation();
      System.out.println("Mean student wait time in queue with staff not pushing in: " + notPushedIn.getMeanStudentWaitTime());
      System.out.println("Mean staff wait time in queue with staff not pushing in: " + notPushedIn.getMeanStaffWaitTime());
    
    }

    sc.close();
  }


  
}
