package cafe;

import java.lang.Math; // This is for getting the absolute valuse
import java.text.DecimalFormat; // This is for rounding in the table
import java.util.ArrayList; 
import java.util.Collections;
import java.util.HashMap; // This is needed for calculating the mode

public class Statistics {

  private ArrayList<Customer> servedCustomerList = new ArrayList<Customer>();
  private int studentQueueSize;
  private int staffQueueSize;

  // This prints the wait time of every customer that had been served
  // This is for debugging purposes and is not used in the software at all
  public void printServedCustomerList () {

    // For every served customer
    for (Customer customer : servedCustomerList) {

      // If they are a student
      if (customer instanceof Student) {
        // Print their wait time
        System.out.println("Student, wait time: " + customer.getWaitTime());
      }
      // If they are staff
      if (customer instanceof Staff) {
        // Print their wait time
        System.out.println("Staff, wait time: " + customer.getWaitTime());
      }
    }
  }

  // This is used to add a customer into the statistics once they have been served
  public void addCustomer(Customer customer) { 
    servedCustomerList.add(customer);
  }

  // Returns a list of every student that has been served
  public ArrayList<Customer> getStudentList() {

    // Instantiate an empty array to add values into later
    ArrayList<Customer> studentList = new ArrayList<Customer>();

    // For every customer that has been served
    for (Customer customer : servedCustomerList) {

      // If they are a student
      if (customer instanceof Student) {
        // Add them to the list of students 
        studentList.add(customer);
      }

    }
    // Return the list with all of the students in
    return studentList;
  }

  // Returns a list of every staff that has been served
  public ArrayList<Customer> getStaffList() {

    // Instantiate a new empty array to add values to later
    ArrayList<Customer> staffList = new ArrayList<Customer>();

    // For every customer that has been served
    for (Customer customer : servedCustomerList) {

      // If they are staff
      if (customer instanceof Staff) {
        // Add them to the array
        staffList.add(customer);
      }
    }
    // Return the array of every staff member that has been served
    return staffList;
  }

  // Get the size of how many students are in the queue
  public static int getStudentQueueSizeFromQueue(final CafeQueue CAFE_QUEUE) {

    // New counter variable for counting the students
    int studentCount = 0;

    // For every customer that is currently in the queue
    for (Customer customer : CAFE_QUEUE.getCustomersStillQueued()) {
      // If they are a student
      if (customer instanceof Student) {
        // Increment the counter
        studentCount++;
      }
    }
    // Return the counter
    return studentCount;
  }

    // Get the size of how many staff members there are in the queue
  public static int getStaffQueueSizeFromQueue(final CafeQueue CAFE_QUEUE) {

    // New counter variable for counting the staff
    int staffCount = 0;

    // For every customer that is currently in the queue
    for (Customer customer : CAFE_QUEUE.getCustomersStillQueued()) {
      // If they are a staff member
      if (customer instanceof Staff) {
        // Increment the counter
        staffCount++;
      }
    }
    // Return the counter
    return staffCount;
  }

  // Get the standard deviation of student wait times
  // Standard deviation is the average deviation that a wait time is from the mean wait time https://en.wikipedia.org/wiki/Standard_deviation
  public double getStudentStandardDeviation() {

    // Gets the mean student wait time
    double meanStudentWaitTime = getMeanStudentWaitTime();

    // Instantiates a counter for the total deviation
    double totalStudentStandardDeviation = 0;

    // Gets the list of students that have been served
    ArrayList<Customer> studentList = getStudentList();

    // Checks if any students were served at all
    if (studentList.size() == 0) {
      return 0;
    }

    // For every customer in the student list
    // Even though it is every customer, they can still only be students
    for (Customer customer : studentList) {
      /* Increase the total deviation by that students deviation
       * Math.abs() means that it will be the absolute value or the positive value. This is because displacement is a vector and is always positive.
       * This takes the students wait time subtracted from the average, this is the displacement, or deviation, from the mean
       */
      totalStudentStandardDeviation += Math.abs(meanStudentWaitTime - customer.getWaitTime());
    }
    // This returns the total deviation, divided by the amount of students, to find the standard deviation, because it is the mean of all deviation
    return totalStudentStandardDeviation / studentList.size();
  }

  // Get the standard deviation of staff wait times
  // Standard deviation is the average deviation that a wait time is from the mean wait time https://en.wikipedia.org/wiki/Standard_deviation
  public double getStaffStandardDeviation() {

    
    // Gets the mean staff wait time
    double meanStaffWaitTime = getMeanStaffWaitTime();

    // Instantiates a counter for the total deviation
    double totalStaffStandardDeviation = 0;

    // Gets the list of staff that have been served
    ArrayList<Customer> staffList = getStaffList();

    // Checks if any staff were served at all
    if (staffList.isEmpty()) {
      return -1;
    }

    // For every customer in the staff list
    // Even though it is every customer, they can still only be staff
    for (Customer customer : staffList) {
      /* Increase the total deviation by that staff deviation
       * Math.abs() means that it will be the absolute value or the positive value. This is because displacement is a vector and is always positive.
       * This takes the staffs wait time subtracted from the average, this is the displacement, or deviation, from the mean
       */
      totalStaffStandardDeviation += Math.abs(meanStaffWaitTime - customer.getWaitTime());
    }
    // This returns the total deviation, divided by the amount of students, to find the standard deviation, because it is the mean of all deviation
    return totalStaffStandardDeviation / staffList.size();
  }

  // This gets the list of student wait times, and returns it sorted
  public ArrayList<Integer> getSortedStudentWaitTimeList() {

    // Gets the list of students that have been served
    ArrayList<Customer> studentList = getStudentList();

    // Instantiates a new list to numerically contain all of the student wait times
    ArrayList<Integer> waitTimeList = new ArrayList<Integer>();

    // For every student that has been served
    for (Customer customer : studentList) {
      // Add their wait time into the list
      waitTimeList.add(customer.getWaitTime()); 
    }
    // Sort their wait times from lowest-highest
    Collections.sort(waitTimeList); 
    // Return the list of every wait time in order from lowest-highest
    return waitTimeList;
  }

    // This gets the list of staff wait times, and returns it sorted
  public ArrayList<Integer> getSortedStaffWaitTimeList() {

    // Gets the list of staff that have been served
    ArrayList<Customer> staffList = getStaffList();

    // Instantiates a new list to numerically contain all of the staff wait times
    ArrayList<Integer> waitTimeList = new ArrayList<Integer>();

    // For every staff that has been served
    for (Customer customer : staffList) {
      // Add their wait time into the list
      waitTimeList.add(customer.getWaitTime());
    }
    // Sort their wait times from lowest-highest
    Collections.sort(waitTimeList);
    // Return the list of every wait time in order from lowest-highest
    return waitTimeList;
  }

  // This returns the most common wait time for students
  public double getModeStudentWaitTime() {

    // Gets every wait time in order from lowest-highest
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

         // Wait Time  Appearance Count
    HashMap<Integer,   Integer> waitTimesAndAppearanceCount = new HashMap<>();
    // The frequency that the current most common value has
    int currentModeFrequency = 1;

    // Check to see if there are any students that have been served at all
    if (sortedStudentWaitTimeList.isEmpty()) {
      return -1;
    }

    // The current most frequent value
    int currentMode = sortedStudentWaitTimeList.get(0);

    // For every wait time in the list
    for (Integer waitTime : sortedStudentWaitTimeList) {

      // If we have already seen this wait time before
      if (waitTimesAndAppearanceCount.containsKey(waitTime)){

        // Find out how many times it has already shown up
        int currentCount = waitTimesAndAppearanceCount.get(waitTime);
        // Increase that number by one
        waitTimesAndAppearanceCount.put(waitTime, currentCount+1);

        // If this is more frequent than the currently most frequent wait time
        if (currentCount+1 > currentModeFrequency){
          currentMode = waitTime; // Make the new mode this wait time
          currentModeFrequency=currentCount+1; // Make the new modes frequency this modes frequency
        }

      } else { // If we have not already seen this before
        waitTimesAndAppearanceCount.put(waitTime, 1); // Set the wait times frequency to 1
      }
    }
    // Returns the most common value
    return currentMode;
  }

  public double getModeStaffWaitTime() {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();
         // Wait Time  Appearance Count
    HashMap<Integer,   Integer> waitTimesAndAppearanceCount = new HashMap<>();
    int currentModeFrequency = 1;

    // Check to see if there are any staff that have been served at all
    if (sortedStaffWaitTimeList.isEmpty()) {
      return -1;
    }

    int currentMode = sortedStaffWaitTimeList.get(0);

    for (Integer waitTime : sortedStaffWaitTimeList) {

      // If we have already seen this wait time before
      if (waitTimesAndAppearanceCount.containsKey(waitTime)){

        // Find out how many times it has already shown up
        int currentCount = waitTimesAndAppearanceCount.get(waitTime);
        // Increase that number by one
        waitTimesAndAppearanceCount.put(waitTime, currentCount+1);

        // If this is more frequent than the currently most frequent wait time
        if (currentCount+1 > currentModeFrequency){
          currentMode = waitTime; // Make the new mode this wait time
          currentModeFrequency=currentCount+1; // Make the new modes frequency this modes frequency
        }

      } else { // If we have not already seen this before
        waitTimesAndAppearanceCount.put(waitTime, 1); // Set the wait times frequency to 1
      }
    }
    return currentMode;
  }

  public double getMedianStudentWaitTime () {

    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

    // Checks that atleast one student has been served and returns 0 if not
    if (sortedStudentWaitTimeList.size() == 0) {
      return 0;
    }

    int medianIndex = (int) Math.ceil(sortedStudentWaitTimeList.size() / 2); // Half way and rounded up

    return (double)sortedStudentWaitTimeList.get(medianIndex);
  }

  public double getMedianStaffWaitTime() {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    // Checks that atleast one staff has been served and returns 0 if not
    if (sortedStaffWaitTimeList.size() == 0) {
      return 0;
    }

    int medianIndex = (int) Math.ceil(sortedStaffWaitTimeList.size() / 2);
    return (double) sortedStaffWaitTimeList.get(medianIndex);
  }

  public double getMeanStudentWaitTime() { // Get the mean (average) time that students have waited

    double totalStudentWaitTime = 0;

    ArrayList<Customer> studentList = getStudentList();

    // Checks that atleast one student has been served and returns 0 if not
    if (studentList.size() == 0) {
      return 0;
    }

    for (Customer customer : studentList) {
      totalStudentWaitTime += customer.getWaitTime();
    }
    return totalStudentWaitTime / studentList.size();
  }

  public double getMeanStaffWaitTime() { // Get the mean (average) time that students have waited

    double totalStaffWaitTime = 0;

    ArrayList<Customer> staffList = getStaffList();

    // Checks that atleast one staff has been served and returns 0 if not
    if (staffList.isEmpty()) {
      return -1;
    }

    // For every customer in the staff list add their wait time to the total wait time
    for (Customer customer : staffList) {
      totalStaffWaitTime += customer.getWaitTime();
    }
    return totalStaffWaitTime / staffList.size();
  }

  // This iterates over all the students that had been served and returns the shortest wait time
  public double getLowestStudentWaitTime() {
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

    // Checks that atleast one staff has been served and returns 0 if not
    if (sortedStudentWaitTimeList.isEmpty()) {
      return -1;
    }

    return (double) (sortedStudentWaitTimeList.get(0));
  }

  // This iterates over all the student that had been served and returns the longest wait time
  public double getGreatestStudentWaitTime() {
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

    // Checks that atleast one student has been served and returns 0 if not
    if (sortedStudentWaitTimeList.isEmpty()) {
      return 1;
    }

    return (double) (sortedStudentWaitTimeList.get(sortedStudentWaitTimeList.size() - 1));
  }

  // This iterates over all the staff that had been served and returns the shortest wait time
  public double getLowestStaffWaitTime() {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    // Checks that atleast one student has been served and returns 0 if not
    if (sortedStaffWaitTimeList.isEmpty()) {
      return -1;
    }

    return (double) (sortedStaffWaitTimeList.get(0));
  }

  // This iterates over all the staff that had been served and returns the longest wait time
  public double getGreatestStaffWaitTime() {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    // Checks that atleast one student has been served and returns 0 if not
    if (sortedStaffWaitTimeList.isEmpty()) {
      return -1;
    }

    return (double) (sortedStaffWaitTimeList.get(sortedStaffWaitTimeList.size() - 1));
  }

  // Gets the range of student wait times
  public double getRangeOfStudentWaitTimes() {
    return (double) (getGreatestStudentWaitTime() - getLowestStudentWaitTime());
  }

  // Gets the range of staff wait times
  public double getRangeOfStaffWaitTimes() {
    return (double) (getGreatestStaffWaitTime() - getLowestStaffWaitTime());
  }

  // Gets the amount of students in the queue at the moment
  public int getStudentQueueSize() {
    return studentQueueSize;
  }

  // Sets the amount of students in the queue at the moment
  public void setStudentQueueSize(final int NEW_SIZE) {
    studentQueueSize = NEW_SIZE;
  }

  // Gets the amount of students in the queue at that moment
  public int getStaffQueueSize() {
    return staffQueueSize;
  }

  // Sets the amount of students in the queue at that moment
  public void setStaffQueueSize(final int NEW_SIZE) {
    staffQueueSize = NEW_SIZE;
  }

  // This prints the statistics in a neat table
  public void printStatistics() {
    /*
     * % means that formatting will put text in that spot - means that it is
     * justified on the left s means that it is a string f means that it is a double
     * (float). The decimal formatting outputs a string so I use s in the place of f
     * | are the lines that are printed on the side to make it look like a table %n
     * finishes writing in the line
     */

    // This is the format for the header or title of the table
    String titleFormat = "|%-26s|%-26s|%n";

    DecimalFormat df = new DecimalFormat("#.#"); // Used to round all printed decimals to one decimal point

    // Gets the integer amount of total students and staff that were in the queue
    int studentPopulation = (getStudentQueueSize() + getStudentList().size());
    int staffPopulation = (getStaffQueueSize() + getStaffList().size());

    /* 
     * Gets the population of both students and staff,
     * sets it as a string
     * and puts brackets around it
     */

    String studentPopulationText = "(" + Integer.valueOf(studentPopulation) + ")"; // Total students added to queue
    String staffPopulationText = "(" + Integer.valueOf(staffPopulation) + ")"; // Total staff added to queue

    // Prints the header for the table
    System.out.format("+--------------------------+--------------------------+\n");
    System.out.format(titleFormat, "Student " + studentPopulationText, "Staff " + staffPopulationText);
    System.out.format("+--------------------+-----+--------------------+-----+\n");

    String studentBorderFormat;
    String studentTextFormat;

    String[] studentStatTitleList = new String[7];
    String[] studentStatNumberList = new String[7];

    String staffBorderFormat;
    String staffTextFormat;
    String[] staffStatTitleList = new String[7];
    String[] staffStatNumberList = new String[7];

    if (getStudentQueueSize() == studentPopulation) { // If no students were served 

      /*
       * This sets the border for the student side of the table
       * This is printed after every row on the student side of the table
       */
      studentBorderFormat = "+--------------------------"; 

      // This is how the text is formatted into the table
      studentTextFormat = "|%-25s%-1s"; 

   // Column 1                                     // Column 2
      studentStatTitleList[0] = "No Students Served"; studentStatNumberList[0] = ""; // Row 1
      studentStatTitleList[1] = "";                   studentStatNumberList[1] = ""; // Row 2
      studentStatTitleList[2] = "";                   studentStatNumberList[2] = ""; // Row 3
      studentStatTitleList[3] = "";                   studentStatNumberList[3] = ""; // Row 4
      studentStatTitleList[4] = "";                   studentStatNumberList[4] = ""; // Row 5
      studentStatTitleList[5] = "";                   studentStatNumberList[5] = ""; // Row 6
      studentStatTitleList[6] = "";                   studentStatNumberList[6] = ""; // Row 7
    }  else {

      /*
       * This sets the border for the student side of the table
       * This is printed after every row on the student side of the table
       */

      studentBorderFormat = "+--------------------+-----";

      // This is how the text is formatted into the table
      studentTextFormat = "|%-20s|%-5s";

   // Column 1                                     // Column 2
      studentStatTitleList[0] = "Mean Wait Time";     studentStatNumberList[0] = df.format(getMeanStudentWaitTime()); // Row 1
      studentStatTitleList[1] = "Median Wait Time";   studentStatNumberList[1] = df.format(getMedianStudentWaitTime()); // Row 2
      studentStatTitleList[2] = "Mode Wait Time";     studentStatNumberList[2] = df.format(getModeStudentWaitTime()); // Row 3
      studentStatTitleList[3] = "Lowest Wait Time";   studentStatNumberList[3] = df.format(getLowestStudentWaitTime()); // Row 4
      studentStatTitleList[4] = "Greatest Wait Time"; studentStatNumberList[4] = df.format(getGreatestStudentWaitTime()); // Row 5
      studentStatTitleList[5] = "Standard Deviation"; studentStatNumberList[5] = df.format(getStudentStandardDeviation()); // Row 6
      studentStatTitleList[6] = "Unserved Students";  studentStatNumberList[6] = df.format(getStudentQueueSize()); // Row 7
    }

    if (getStaffQueueSize() == staffPopulation) { // If no staff were served

      /*
       * This sets the border for the staff side of the table
       * This is printed after every row on the student side of the table
       */

      staffBorderFormat = "+--------------------------";

      // This is how the text is formatted into the table
      staffTextFormat = "|%-25s%-1s";

   // Column 1                                   // Column 2
      staffStatTitleList[0] = "No Staff Served";    staffStatNumberList[0] = "";
      staffStatTitleList[1] = "";                   staffStatNumberList[1] = "";
      staffStatTitleList[2] = "";                   staffStatNumberList[2] = "";
      staffStatTitleList[3] = "";                   staffStatNumberList[3] = "";
      staffStatTitleList[4] = "";                   staffStatNumberList[4] = "";
      staffStatTitleList[5] = "";                   staffStatNumberList[5] = "";
      staffStatTitleList[6] = "";                   staffStatNumberList[6] = "";

    }  else {
      staffBorderFormat = "+--------------------+-----";
      staffTextFormat = "|%-20s|%-5s";
      staffStatTitleList[0] = "Mean Wait Time";     staffStatNumberList[0] = df.format(getMeanStaffWaitTime());
      staffStatTitleList[1] = "Median Wait Time";   staffStatNumberList[1] = df.format(getMedianStaffWaitTime());
      staffStatTitleList[2] = "Mode Wait Time";     staffStatNumberList[2] = df.format(getModeStaffWaitTime());
      staffStatTitleList[3] = "Lowest Wait Time";   staffStatNumberList[3] = df.format(getLowestStaffWaitTime());
      staffStatTitleList[4] = "Greatest Wait Time"; staffStatNumberList[4] = df.format(getGreatestStaffWaitTime());
      staffStatTitleList[5] = "Standard Deviation"; staffStatNumberList[5] = df.format(getStaffStandardDeviation());
      staffStatTitleList[6] = "Unserved Staff";     staffStatNumberList[6] = df.format(getStaffQueueSize());
    }

    // This combines the border format so that the two columns are connected
    StringBuilder addedBorderFormat = new StringBuilder();
    addedBorderFormat.append(studentBorderFormat);
    addedBorderFormat.append(staffBorderFormat);
    addedBorderFormat.append("+%n");

    // This combines the text format so that the two columns are connected
    StringBuilder addedTextFormat = new StringBuilder();
    addedTextFormat.append(studentTextFormat);
    addedTextFormat.append(staffTextFormat);
    addedTextFormat.append("|%n");

    // For every row in the table
    for (int i = 0; i < studentStatTitleList.length; i++) {
      // Print the text and number that should be shown
      System.out.format(addedTextFormat.toString(), studentStatTitleList[i], studentStatNumberList[i], 
                                staffStatTitleList[i],   staffStatNumberList[i]);
      // Print the border underneath it
      System.out.format(addedBorderFormat.toString());   
    }

  }

}
