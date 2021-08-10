package cafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileAccessLayer {

  public static Timeline openFile (final String PATH) throws FileNotFoundException {

    final int MAX_VALUE = 400;

    Timeline timeline = new Timeline (); // Empty timeline to be read into

    final File FILE = new File(PATH); // File that is being read
    Scanner fileScanner = new Scanner(FILE); // Scanner for reading the file

    if (!fileScanner.hasNextLine()) {
      System.out.println("");
      System.out.println("Error: Cannot use empty file, please try again");
      System.out.println("");
      fileScanner.close();
      return null;
    }

    fileScanner.nextLine(); // Skips the headers of the csv file

    while (fileScanner.hasNextLine()) { // While the file has another line

      String line = fileScanner.nextLine(); // Reads and saves the line

      // Gets the minute from the line
      String minuteIndexAsString = line.split(",")[0];

      // Removes all of the whitespace
      minuteIndexAsString = minuteIndexAsString.replaceAll("\\s+","");

      int minuteIndex;

      // Checks that the string can be converted into an integer
      try { 
        minuteIndex = Integer.parseInt(minuteIndexAsString); // Finds what minute that line has data for
      } catch (NumberFormatException e) {
        // Prints an error message if the string cannot be converted into an integer
        System.out.println("Error: Selected .csv file contains illegal characters, please remove illegal characters and try again");
        // Closes the file
        fileScanner.close();
        // Stops running the function
        return null;
      }

      // Checks whether they are using a value that is greater than the maximum value
      if (minuteIndex > MAX_VALUE) {
        // Prints an error message
        System.out.println("Error: Minute is out of range ( > " + Integer.valueOf(MAX_VALUE) + " ): " + Integer.valueOf(minuteIndex));
        // Stops reading the file
        fileScanner.close();
        // Stops the rest of the function from running
        return null;
      }

      /* Adds minutes so that the minute it is trying to edit 
      is in the timeline at all
      */

      // If that minute does not exist keep adding minutes into the timeline until it does
      while (minuteIndex > timeline.getMinuteStack().size() - 1){ 
        timeline.getMinuteStack().add(new Timeline.Minute(minuteIndex,0,0,0));
      }

      // Sets the student count of the minute to the one in the file
      String studentCountInMinuteInCsvAsString;
      // Checks whether the entry is long enough
      if (line.split(",").length > 1) { 
        studentCountInMinuteInCsvAsString = line.split(",")[1];
      } else {
        studentCountInMinuteInCsvAsString = "0"; // Sets it to zero if the file does not contain the information
      }

      // Removes all whitespace from the string
      studentCountInMinuteInCsvAsString = studentCountInMinuteInCsvAsString.replaceAll("\\s+","");
      
      // Converts to an integer
      int studentCountInMinuteInCsv;
      try { // Makes sure that the string can be converted into an integer
        studentCountInMinuteInCsv = Integer.parseInt(studentCountInMinuteInCsvAsString);
      } catch (NumberFormatException e) {
        System.out.println("Error: Selected .csv file contains illegal characters, please remove illegal characters and try again");
        fileScanner.close();
        return null;
      }

      // Checks that the value is not greater than the maximum value
      if (studentCountInMinuteInCsv > MAX_VALUE) {
        System.out.println("Error: Student count is out of range ( >  " + Integer.valueOf(MAX_VALUE) + " ): " + Integer.valueOf(studentCountInMinuteInCsv));
        fileScanner.close();
        return null;
      }

      // Checks that the value is not negative
      if (studentCountInMinuteInCsv < 0) {
        System.out.println("Error: Student count cannot be negative");
        fileScanner.close();
        return null;
      }

      timeline.getMinuteStack().get(minuteIndex).setStudentsAddedToQueue(studentCountInMinuteInCsv); 

      // Sets the staff count of the minute to the one in the file

      String staffCountInMinuteInCsvAsString;
      // Checks whether the entry is long enough
      if (line.split(",").length > 2) { 
        staffCountInMinuteInCsvAsString = line.split(",")[2];
      } else {
        staffCountInMinuteInCsvAsString = "0"; // Sets it to zero if the file does not contain the information
      }


      // Removes all whitespace from the string
      staffCountInMinuteInCsvAsString = staffCountInMinuteInCsvAsString.replaceAll("\\s+","");

      // Converts to an integer
      int staffCountInMinuteInCsv;
      try { // Makes sure that the string can be converted into an integer
        staffCountInMinuteInCsv = Integer.parseInt(staffCountInMinuteInCsvAsString);
      } catch (NumberFormatException e) {
        System.out.println("Error: Selected .csv file contains illegal characters, please remove illegal characters and try again");
        fileScanner.close();
        return null;
      }

      if (staffCountInMinuteInCsv > MAX_VALUE) {
        System.out.println("Error: Staff count is out of range ( > " + Integer.valueOf(MAX_VALUE) + " ): " + Integer.valueOf(staffCountInMinuteInCsv));
        fileScanner.close();
        return null;
      }

      // Checks that the value is not negative
      if (staffCountInMinuteInCsv < 0) {
        System.out.println("Error: Staff count cannot be negative");
        fileScanner.close();
        return null;
      }
      
      timeline.getMinuteStack().get(minuteIndex).setStaffAddedToQueue(staffCountInMinuteInCsv);

      // Sets the customers served count of the minute to the one in the file

      String customersServedCountInMinuteInCsvAsString;

      // Checks whether the entry is long enough
      if (line.split(",").length > 3) { 
        customersServedCountInMinuteInCsvAsString = line.split(",")[3];
      } else {
        customersServedCountInMinuteInCsvAsString = "0";
      }


      // Removes all whitespace from the string
      customersServedCountInMinuteInCsvAsString = customersServedCountInMinuteInCsvAsString.replaceAll("\\s+","");
      // Converts to an integer
      int customersServedCountInMinuteInCsv;
      try { // Makes sure that the string can be converted into an integer
        customersServedCountInMinuteInCsv = Integer.parseInt(customersServedCountInMinuteInCsvAsString);
      } catch (NumberFormatException e) {
        System.out.println("Error: Selected .csv file contains illegal characters, please remove illegal characters and try again");
        fileScanner.close();
        return null;
      }

      // Checks that the value entered is not greater than the maximum value
      if (customersServedCountInMinuteInCsv > MAX_VALUE) {
        System.out.println("Error: Customer served count is out of range ( > " +  Integer.valueOf(MAX_VALUE) + " ): " + Integer.valueOf(customersServedCountInMinuteInCsv));
        fileScanner.close();
        return null;
      }

      // Checks that the value is not negative
      if (customersServedCountInMinuteInCsv < 0) {
        System.out.println("Error: Customers served count cannot be negative");
        fileScanner.close();
        return null;
      }

      // Saves into the timeline
      timeline.getMinuteStack().get(minuteIndex).setCustomersRemovedFromQueue(customersServedCountInMinuteInCsv);
      
    }

    fileScanner.close(); // Stops reading the file
    return timeline; // Returns the timeline

    }
    
}
