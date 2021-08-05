package cafe;

import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Statistics {

  private ArrayList<Customer> servedCustomerList = new ArrayList<Customer>();
  private int studentQueueSize;
  private int staffQueueSize;

  public void printServedCustomerList () {
    for (Customer customer : servedCustomerList) {
      if (customer instanceof Student) {
        System.out.println("Student, wait time: " + customer.getWaitTime());
      }
      if (customer instanceof Staff) {
        System.out.println("Staff, wait time: " + customer.getWaitTime());
      }
    }
  }

  public void addCustomer(Customer customer) { // Adds the customer to the ArrayList
    servedCustomerList.add(customer);
  }

  public ArrayList<Customer> getStudentList() {

    ArrayList<Customer> studentList = new ArrayList<Customer>();

    for (Customer customer : servedCustomerList) {

      if (customer instanceof Student) {
        studentList.add(customer);
      }

    }
    return studentList;
  }

  public ArrayList<Customer> getStaffList() {

    ArrayList<Customer> staffList = new ArrayList<Customer>();

    for (Customer customer : servedCustomerList) {

      if (customer instanceof Staff) {
        staffList.add(customer);
      }
    }
    return staffList;
  }

  public static int getStudentQueueSizeFromQueue(final CafeQueue CAFE_QUEUE) {

    int studentCount = 0;

    for (Customer customer : CAFE_QUEUE.getCustomersStillQueued()) {
      if (customer instanceof Student) {
        studentCount++;
      }
    }
    return studentCount;
  }

  public static int getStaffQueueSizeFromQueue(final CafeQueue CAFE_QUEUE) {
    int staffCount = 0;

    for (Customer customer : CAFE_QUEUE.getCustomersStillQueued()) {
      if (customer instanceof Staff) {
        staffCount++;
      }
    }
    return staffCount;
  }

  public double getStudentStandardDeviation() {

    double meanStudentWaitTime = getMeanStudentWaitTime();
    double totalStudentStandardDeviation = 0;

    ArrayList<Customer> studentList = getStudentList();

    // Checks if any students were served at all
    if (studentList.size() == 0) {
      return 0;
    }

    for (Customer customer : studentList) {
      totalStudentStandardDeviation += Math.abs(meanStudentWaitTime - customer.getWaitTime());
    }
    return totalStudentStandardDeviation / studentList.size();
  }

  public double getStaffStandardDeviation() {

    double meanStaffWaitTime = getMeanStaffWaitTime();
    double totalStaffStandardDeviation = 0;

    ArrayList<Customer> staffList = getStaffList();

    // Checks if any staff were served at all
    if (staffList.isEmpty()) {
      return -1;
    }

    for (Customer customer : staffList) {
      totalStaffStandardDeviation += Math.abs(meanStaffWaitTime - customer.getWaitTime());
    }
    return totalStaffStandardDeviation / staffList.size();
  }

  public ArrayList<Integer> getSortedStudentWaitTimeList() {

    ArrayList<Customer> studentList = getStudentList();
    ArrayList<Integer> waitTimeList = new ArrayList<Integer>();

    for (Customer customer : studentList) {
      waitTimeList.add(customer.getWaitTime()); // Adds each customers wait time to the list
    }
    Collections.sort(waitTimeList); // Sorts wait time list

    return waitTimeList;
  }

  public ArrayList<Integer> getSortedStaffWaitTimeList() {

    ArrayList<Customer> staffList = getStaffList();
    ArrayList<Integer> waitTimeList = new ArrayList<Integer>();

    for (Customer customer : staffList) {
      waitTimeList.add(customer.getWaitTime());
    }
    Collections.sort(waitTimeList);
    return waitTimeList;
  }

  public double getModeStudentWaitTime() {

    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();
         // Wait Time  Appearance Count
    HashMap<Integer,   Integer> waitTimesAndAppearanceCount = new HashMap<>();
    int currentModeFrequency = 1;
    int currentMode = sortedStudentWaitTimeList.get(0);

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
    return currentMode;
  }

  public double getModeStaffWaitTime() {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();
         // Wait Time  Appearance Count
    HashMap<Integer,   Integer> waitTimesAndAppearanceCount = new HashMap<>();
    int currentModeFrequency = 1;
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

  public double getLowestStudentWaitTime() {
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

    // Checks that atleast one staff has been served and returns 0 if not
    if (sortedStudentWaitTimeList.isEmpty()) {
      return -1;
    }

    return (double) (sortedStudentWaitTimeList.get(0));
  }

  public double getGreatestStudentWaitTime() {
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

    // Checks that atleast one student has been served and returns 0 if not
    if (sortedStudentWaitTimeList.isEmpty()) {
      return 1;
    }

    return (double) (sortedStudentWaitTimeList.get(sortedStudentWaitTimeList.size() - 1));
  }

  public double getLowestStaffWaitTime() {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    // Checks that atleast one student has been served and returns 0 if not
    if (sortedStaffWaitTimeList.isEmpty()) {
      return -1;
    }

    return (double) (sortedStaffWaitTimeList.get(0));
  }

  public double getGreatestStaffWaitTime() {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    // Checks that atleast one student has been served and returns 0 if not
    if (sortedStaffWaitTimeList.isEmpty()) {
      return -1;
    }

    return (double) (sortedStaffWaitTimeList.get(sortedStaffWaitTimeList.size() - 1));
  }

  public double getRangeOfStudentWaitTimes() {
    return (double) (getGreatestStudentWaitTime() - getLowestStudentWaitTime());
  }

  public double getRangeOfStaffWaitTimes() {
    return (double) (getGreatestStaffWaitTime() - getLowestStaffWaitTime());
  }

  public int getStudentQueueSize() {
    return studentQueueSize;
  }

  public void setStudentQueueSize(final int NEW_SIZE) {
    studentQueueSize = NEW_SIZE;
  }

  public int getStaffQueueSize() {
    return staffQueueSize;
  }

  public void setStaffQueueSize(final int NEW_SIZE) {
    staffQueueSize = NEW_SIZE;
  }

  public void printStatistics() {

    String format = "|%-20s|%-5s|%-20s|%-5s|%n"; // Used to show the tables format
    /*
     * % means that formatting will put text in that spot - means that it is
     * justified on the left s means that it is a string f means that it is a double
     * (float). The decimal formatting outputs a string so I use s in the place of f
     * | are the lines that are printed on the side to make it look like a table %n
     * finishes writing in the line
     */

    String titleFormat = "|%-26s|%-26s|%n";

    DecimalFormat df = new DecimalFormat("#.#"); // Used to round all printed decimals to one decimal point

    int studentPopulation = (getStudentQueueSize() + getStudentList().size());
    int staffPopulation = (getStaffQueueSize() + getStaffList().size());

    String studentPopulationText = "(" + Integer.valueOf(studentPopulation) + ")"; // Total students added to queue
    String staffPopulationText = "(" + Integer.valueOf(staffPopulation) + ")"; // Total staff added to queue

    // Prints the table for student and staff
    System.out.format("+--------------------------+--------------------------+\n");
    System.out.format(titleFormat, "Student " + studentPopulationText, "Staff " + staffPopulationText);
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Mean Wait Time", df.format(getMeanStudentWaitTime()), "Mean Wait Time",
        df.format(getMeanStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Median Wait Time", df.format(getMedianStudentWaitTime()), "Median Wait Time",
        df.format(getMedianStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Mode Wait Time", df.format(getModeStudentWaitTime()), "Mode Wait Time",
        df.format(getModeStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Lowest Wait Time", df.format(getLowestStudentWaitTime()), "Lowest Wait Time",
        df.format(getLowestStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Greatest Wait Time", df.format(getGreatestStudentWaitTime()), "Greatest Wait Time",
        df.format(getGreatestStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Standard Deviation", df.format(getStudentStandardDeviation()), "Standard Deviation",
        df.format(getStaffStandardDeviation()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Unserved Students", df.format(getStudentQueueSize()), "Unserved Staff",
        df.format(getStaffQueueSize()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");

  }

}
