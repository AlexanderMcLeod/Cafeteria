package cafe;

import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

  private ArrayList<Customer> servedCustomerList = new ArrayList<Customer>();

  public void addCustomer (Customer customer) { // Adds the customer to the ArrayList
    servedCustomerList.add(customer);
  }

  public ArrayList<Customer> getStudentList () {

    ArrayList<Customer> studentList = new ArrayList<Customer>();
    
    for (Customer customer : servedCustomerList) {
      
      if (customer instanceof Student) {
        studentList.add(customer);
      }
    }
    return studentList;
  } 

  public ArrayList<Customer> getStaffList () {

    ArrayList<Customer> staffList = new ArrayList<Customer>();
    
    for (Customer customer : servedCustomerList) {
      
      if (customer instanceof Staff) {
        staffList.add(customer);
      }
    }
    return staffList;
  } 

  public double getStudentStandardDeviation () {

    double meanStudentWaitTime = getMeanStudentWaitTime();
    double totalStudentStandardDeviation = 0;

    ArrayList<Customer> studentList = getStudentList();

    for (Customer customer: studentList){
      totalStudentStandardDeviation += Math.abs(meanStudentWaitTime - customer.getWaitTime());
    }
    return totalStudentStandardDeviation / studentList.size();
  }

  public double getStaffStandardDeviation () {

    double meanStaffWaitTime = getMeanStaffWaitTime();
    double totalStaffStandardDeviation = 0;

    ArrayList<Customer> staffList = getStaffList();

    for (Customer customer: staffList){
      totalStaffStandardDeviation += Math.abs(meanStaffWaitTime - customer.getWaitTime());
    }
    return totalStaffStandardDeviation / staffList.size();
  }

  public ArrayList<Integer> getSortedStudentWaitTimeList () {

    ArrayList<Customer> studentList = getStudentList();
    ArrayList<Integer> waitTimeList = new ArrayList<Integer>();

    for (Customer customer : studentList ) {
      waitTimeList.add(customer.getWaitTime());
    }
    Collections.sort(waitTimeList);
    return waitTimeList;
  }

  public ArrayList<Integer> getSortedStaffWaitTimeList () {

    ArrayList<Customer> staffList = getStaffList();
    ArrayList<Integer> waitTimeList = new ArrayList<Integer>();

    for (Customer customer : staffList ) {
      waitTimeList.add(customer.getWaitTime());
    }
    Collections.sort(waitTimeList);
    return waitTimeList;
  }

    public double getModeStudentWaitTime () {

      ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

      int currentAppearanceCount = 0;
      int currentWaitTime = 0;

      int currentModeWaitTime = 0;
      int currentModeAppearanceCount = 0;

      for (Integer waitTime : sortedStudentWaitTimeList) {

        if (waitTime == currentModeWaitTime) {
          currentAppearanceCount++;
        }
        if (waitTime != currentWaitTime) {
          if (currentAppearanceCount > currentModeAppearanceCount) {
            currentModeWaitTime = currentWaitTime;
            currentModeAppearanceCount = currentAppearanceCount;
          }
          currentWaitTime = waitTime;
          currentAppearanceCount = 1;
        }
      }
      return (double) currentModeWaitTime;
    }

  public double getModeStaffWaitTime () {

    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    int currentAppearanceCount = 0;
    int currentWaitTime = 0;

    int currentModeWaitTime = 0;
    int currentModeAppearanceCount = 0;

    for (Integer waitTime : sortedStaffWaitTimeList) {

      if (waitTime == currentModeWaitTime) {
        currentAppearanceCount++;
      }
      if (waitTime != currentWaitTime) {
        if (currentAppearanceCount > currentModeAppearanceCount) {
          currentModeWaitTime = currentWaitTime;
          currentModeAppearanceCount = currentAppearanceCount;
        }
        currentWaitTime = waitTime;
        currentAppearanceCount = 1;
      }
    }
    return (double) currentModeWaitTime;
  }

  public double getMedianStudentWaitTime () {

    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

    int medianIndex = (int) Math.ceil(sortedStudentWaitTimeList.size() / 2);
    return (double)sortedStudentWaitTimeList.get(medianIndex);
  }

  public double getMedianStaffWaitTime () {
    
    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    int medianIndex = (int) Math.ceil(sortedStaffWaitTimeList.size() / 2);
    return (double)sortedStaffWaitTimeList.get(medianIndex);
  }

  public double getMeanStudentWaitTime () { // Get the mean (average) time that students have waited

    double totalStudentWaitTime = 0;

    ArrayList<Customer> studentList = getStudentList();

    for (Customer customer : studentList){
      totalStudentWaitTime += customer.getWaitTime();
    }
    return totalStudentWaitTime / studentList.size();
  }

  public double getMeanStaffWaitTime () { // Get the mean (average) time that students have waited

    double totalStaffWaitTime = 0;

    ArrayList<Customer> staffList = getStaffList();

    for (Customer customer : staffList){
      totalStaffWaitTime += customer.getWaitTime();
    }
    return totalStaffWaitTime / staffList.size();
  }

  public double getLowestStudentWaitTime () {
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();
    return (double)(sortedStudentWaitTimeList.get(0));
  }

  public double getGreatestStudentWaitTime () {
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();
    return (double)(sortedStudentWaitTimeList.get(sortedStudentWaitTimeList.size() - 1));
  }

  public double getLowestStaffWaitTime () {
    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();
    return (double)(sortedStaffWaitTimeList.get(0));
  }

  public double getGreatestStaffWaitTime () {
    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();
    return (double)(sortedStaffWaitTimeList.get(sortedStaffWaitTimeList.size() - 1));
  }

  public double getRangeOfStudentWaitTimes () {
    return (double)(getGreatestStudentWaitTime() - getLowestStudentWaitTime());
  }

  public double getRangeOfStaffWaitTimes () {
    return (double) (getGreatestStaffWaitTime() - getLowestStaffWaitTime());
  }

  public void printStatistics () {

    String format = "|%-20s|%-5s|%-20s|%-5s|%n"; // Used to show the tables format
    /* % means that formatting will put text in that spot
     * - means that it is justified on the left
     * s means that it is a string
     * f means that it is a double (float). The decimal formatting outputs a string so I use s in the place of f
     * | are the lines that are printed on the side to make it look like a table
     * %n finishes writing in the line
     */

    String titleFormat = "|%-26s|%-26s|%n";

    DecimalFormat df = new DecimalFormat("#.#"); // Used to round all printed decimals to one decimal point

    // Prints the table for student and staff
    System.out.format("+--------------------------+--------------------------+\n");
    System.out.format(titleFormat, "Student", "Staff");
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Mean Wait Time", df.format(getMeanStudentWaitTime()), "Mean Wait Time", df.format(getMeanStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Median Wait Time", df.format(getMedianStudentWaitTime()), "Median Wait Time", df.format(getMedianStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Mode Wait Time", df.format(getModeStudentWaitTime()), "Mode Wait Time", df.format(getModeStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Lowest Wait Time", df.format(getLowestStudentWaitTime()), "Lowest Wait Time", df.format(getLowestStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Greatest Wait Time", df.format(getGreatestStudentWaitTime()), "Greatest Wait Time", df.format(getGreatestStaffWaitTime()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
    System.out.format(format, "Standard Deviation", df.format(getStudentStandardDeviation()), "Standard Deviation", df.format(getStaffStandardDeviation()));
    System.out.format("+--------------------+-----+--------------------+-----+\n");
  }

}
