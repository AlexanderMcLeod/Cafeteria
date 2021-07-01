package cafe;

import java.lang.Math;
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

    public int getModeStudentWaitTime () {

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
      return currentModeWaitTime;
    }

  public int getModeStaffWaitTime () {

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
    return currentModeWaitTime;
  }

  public int getMedianStudentWaitTime () {

    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();

    int medianIndex = (int) Math.ceil(sortedStudentWaitTimeList.size() / 2);
    return sortedStudentWaitTimeList.get(medianIndex);
  }

  public int getMedianStaffWaitTime () {
    
    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();

    int medianIndex = (int) Math.ceil(sortedStaffWaitTimeList.size() / 2);
    return sortedStaffWaitTimeList.get(medianIndex);
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

  public int getRangeOfStudentWaitTimes () {
    ArrayList<Integer> sortedStudentWaitTimeList = getSortedStudentWaitTimeList();
    return (sortedStudentWaitTimeList.get(sortedStudentWaitTimeList.size() - 1) - sortedStudentWaitTimeList.get(0));
  }

  public int getRangeOfStaffWaitTimes () {
    ArrayList<Integer> sortedStaffWaitTimeList = getSortedStaffWaitTimeList();
    return (sortedStaffWaitTimeList.get(sortedStaffWaitTimeList.size() - 1) - sortedStaffWaitTimeList.get(0));
  }
}
