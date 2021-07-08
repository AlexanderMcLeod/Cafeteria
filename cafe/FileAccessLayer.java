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

    while (fileScanner.hasNextLine()) { 

      String line = fileScanner.nextLine();
      int minuteIndex = Integer.parseInt(line.split(",")[0]); 

      while (minuteIndex > timeline.size() - 1){
        timeline.addNewMinute();
      }

      int studentCountInMinuteInCsv = Integer.parseInt(line.split(",")[1]);
      timeline.getMinute(minuteIndex).setStudentsAddedToQueue(studentCountInMinuteInCsv);

      int staffCountInMinuteInCsv = Integer.parseInt(line.split(",")[2]);
      timeline.getMinute(minuteIndex).setStaffAddedToQueue(staffCountInMinuteInCsv);

      int customersServedCountInMinuteInCsv = Integer.parseInt(line.split(",")[3]);
      timeline.getMinute(minuteIndex).setCustomersRemovedFromQueue(customersServedCountInMinuteInCsv);
      
    }

    fileScanner.close();
    return timeline;

    }
    
}
