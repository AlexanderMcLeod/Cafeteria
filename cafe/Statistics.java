package cafe;

public class Statistics {

  private int totalStudentCount = 0;
  private int totalStaffCount = 0;

  private int totalStudentWaitTime = 0;
  private int totalStaffWaitTime = 0;

  public void addCustomer (Customer customer) { // This will add the customer into the summary statistcs by 

    /* Checks whether the customer is a student or staff */

    if (customer instanceof Student){ 
      addStudent(customer); // If they are a student, increase the statistics for students
    }
    if (customer instanceof Staff){ // If they are a staff, increase the statistics for staff
      addStaff(customer); 
    }
  }

  public void addStudent (Customer student){
    totalStudentCount++; // Increase the amount of students by one
    totalStudentWaitTime += student.getWaitTime(); // Add the time they waited to the total time students have waited
  }

  public void addStaff (Customer staff){
    totalStaffCount++; // Increase the amount of staff by one
    totalStaffWaitTime += staff.getWaitTime(); // Add the time they waited to the total time staff have waited
  }

  public double getMeanStudentWaitTime () { // Get the mean (average) time that students have waited
    return (totalStudentWaitTime / totalStudentCount);
  }

  public double getMeanStaffWaitTime () { // Get the mean (average) time that staff have waited
    return (totalStaffWaitTime / totalStaffCount);
  }
  
}
