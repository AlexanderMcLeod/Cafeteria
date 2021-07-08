package cafe;

import java.util.LinkedList;

public class CafeQueue {

  private LinkedList<Customer> cafeQueue = new LinkedList<Customer>();
  private Statistics cafeStatistics = new Statistics();

  public void addStudent (final int currentMinute) {
    Student newStudent = new Student();
    newStudent.joinQueue(currentMinute);
    cafeQueue.addLast(newStudent);
  }

  /* In reality staff would not push infront of other staff
   * but implementing it so that it would, would not 
   * affect mean wait times as long as everyone is served */

  public void addPushingInStaff (final int currentMinute) {
    Staff newStaff = new Staff(true); // True means that they will push in
    newStaff.joinQueue(currentMinute); 
    cafeQueue.addFirst(newStaff); // Added to the front because they pushed in
  }

  public void addNonPushingInStaff (final int currentMinute) {
    Staff newStaff = new Staff(false); // False means that they will not push in
    newStaff.joinQueue(currentMinute); 
    cafeQueue.addLast(newStaff);
  }

  public void removeFromFront (final int currentMinute) {

    /* Checks whether there is a student to remove */
    if (cafeQueue.isEmpty()){
      return; // Does not try to remove a customer if the queue is empty
    }

    cafeQueue.peek().leaveQueue(currentMinute); // Stops the customers timer
    cafeStatistics.addCustomer(cafeQueue.peek()); // Adds the customer into statistics
    cafeQueue.removeFirst(); // Removes the customer from the queue
  }

  public Statistics getStatistics () {
    return cafeStatistics;
  }


  
}
