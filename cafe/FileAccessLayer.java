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
      String studentCountInMinuteInCsvAsString = line.split(",")[1];
      // Removes all whitespace from the string
      studentCountInMinuteInCsvAsString = studentCountInMinuteInCsvAsString.replaceAll("\\s+","");
      // Converts to an integer
      int studentCountInMinuteInCsv = Integer.parseInt(studentCountInMinuteInCsvAsString);
      timeline.getMinuteStack().get(minuteIndex).setStudentsAddedToQueue(studentCountInMinuteInCsv); 

      // Sets the staff count of the minute to the one in the file
      String staffCountInMinuteInCsvAsString = line.split(",")[2];
      // Removes all whitespace from the string
      staffCountInMinuteInCsvAsString = staffCountInMinuteInCsvAsString.replaceAll("\\s+","");
      // Converts to an integer
      int staffCountInMinuteInCsv = Integer.parseInt(staffCountInMinuteInCsvAsString);
      timeline.getMinuteStack().get(minuteIndex).setStaffAddedToQueue(staffCountInMinuteInCsv);

      // Sets the customers served count of the minute to the one in the file
      String customersServedCountInMinuteInCsvAsString = line.split(",")[3];
      // Removes all whitespace from the string
      customersServedCountInMinuteInCsvAsString = customersServedCountInMinuteInCsvAsString.replaceAll("\\s+","");
      // Converts to an integer
      int customersServedCountInMinuteInCsv = Integer.parseInt(customersServedCountInMinuteInCsvAsString);
      timeline.getMinuteStack().get(minuteIndex).setCustomersRemovedFromQueue(customersServedCountInMinuteInCsv);
      
    }

    fileScanner.close(); // Stops reading the file
    return timeline; // Returns the timeline

    }
    
}
