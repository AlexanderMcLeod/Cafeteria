package cafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileAccessLayer {

  public static Timeline openFile (final String PATH) throws FileNotFoundException {

    Timeline timeline = new Timeline (); // Empty timeline to be read into

    final File FILE = new File(PATH); // File that is being read
    Scanner fileScanner = new Scanner(FILE); // Scanner for reading the file

    fileScanner.nextLine(); // Skips the headers of the csv file

    while (fileScanner.hasNextLine()) { // While the file has another line

      String line = fileScanner.nextLine(); // Reads and saves the line
      int minuteIndex = Integer.parseInt(line.split(",")[0]); // Finds what minute that line has data for

      /* Adds minutes so that the minute it is trying to edit 
      is in the timeline at all
      */

      while (minuteIndex > timeline.getMinuteStack().size() - 1){ 
        timeline.getMinuteStack().add(new Timeline.Minute(minuteIndex,0,0,0));
      }

      // Sets the student count of the minute to the one in the file
      int studentCountInMinuteInCsv = Integer.parseInt(line.split(",")[1]);
      timeline.getMinuteStack().get(minuteIndex).setStudentsAddedToQueue(studentCountInMinuteInCsv); 

      // Sets the staff count of the minute to the one in the file
      int staffCountInMinuteInCsv = Integer.parseInt(line.split(",")[2]);
      timeline.getMinuteStack().get(minuteIndex).setStaffAddedToQueue(staffCountInMinuteInCsv);

      // Sets the customers served count of the minute to the one in the file
      int customersServedCountInMinuteInCsv = Integer.parseInt(line.split(",")[3]);
      timeline.getMinuteStack().get(minuteIndex).setCustomersRemovedFromQueue(customersServedCountInMinuteInCsv);
      
    }

    fileScanner.close(); // Stops reading the file
    return timeline; // Returns the timeline

    }
    
}
