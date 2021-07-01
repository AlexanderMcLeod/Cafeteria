package cafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileAccessLayer {

  public static Timeline openFile (final String path) throws FileNotFoundException {

    Timeline timeline = new Timeline (); // Empty timeline to be read into

    File file = new File(path); // File that is being read
    Scanner fileScanner = new Scanner(file); // Scanner for reading the file

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

      System.out.println(Integer.valueOf(minuteIndex) + ", " + Integer.valueOf(studentCountInMinuteInCsv) + ", " + Integer.valueOf(staffCountInMinuteInCsv) + ", " + Integer.valueOf(customersServedCountInMinuteInCsv));
      System.out.println(Integer.valueOf(minuteIndex) + ", " + Integer.valueOf(timeline.getMinute(minuteIndex).getStudentsAddedToQueue()) + ", " + Integer.valueOf(timeline.getMinute(minuteIndex).getStaffAddedToQueue()) + ", " + Integer.valueOf(timeline.getMinute(minuteIndex).getCustomersRemovedFromQueue()));

    }

    fileScanner.close();
    return timeline;

    }
    
}
