package cafe;

import java.util.LinkedList;

public class CafeQueue {

  private LinkedList<Customer> cafeQueue = new LinkedList<Customer>();
  private Statistics cafeStatistics = new Statistics();

  public void addManyStudents(final int CURRENT_MINUTE, final int STUDENT_COUNT) {
    for (int i = 0; i < STUDENT_COUNT; i++) {
      addStudent(CURRENT_MINUTE);
    }
  }

  public void addStudent (final int CURRENT_MINUTE) {
    Student newStudent = new Student();
    newStudent.joinQueue(CURRENT_MINUTE);
    cafeQueue.addLast(newStudent);
  }

  /* In reality staff would not push infront of other staff
   * but implementing it so that it would, would not 
   * affect mean wait times as long as everyone is served */

  public void addManyStaff(final int CURRENT_MINUTE, final boolean WILL_PUSH_IN, final int STAFF_COUNT) {
    for (int i = 0; i < STAFF_COUNT; i++) {
      addStaff(CURRENT_MINUTE, WILL_PUSH_IN);
    }
  }

  public void addStaff(final int CURRENT_MINUTE, final boolean WILL_PUSH_IN) {
    Staff newStaff = new Staff(true); // True means that they will push in
    newStaff.joinQueue(CURRENT_MINUTE); 
    cafeQueue.addFirst(newStaff); // Added to the front because they pushed in
  }

  public void removeManyFromFront (final int CURRENT_MINUTE, final int CUSTOMERS_TO_REMOVE_COUNT) {
    for (int i = 0; i < CUSTOMERS_TO_REMOVE_COUNT; i++) {
      removeFromFront(CURRENT_MINUTE);
    }
  }

  public void removeFromFront (final int CURRENT_MINUTE) {

    /* Checks whether there is a student to remove */
    if (cafeQueue.isEmpty()){
      System.out.println("Tried serving a customer when the queue was empty in minute " + CURRENT_MINUTE);
      return; // Does not try to remove a customer if the queue is empty
    }

    cafeQueue.peek().leaveQueue(CURRENT_MINUTE); // Stops the customers timer
    cafeStatistics.addCustomer(cafeQueue.peek()); // Adds the customer into statistics
    cafeQueue.removeFirst(); // Removes the customer from the queue
  }

  public LinkedList<Customer> getCustomersStillQueued () {
    return cafeQueue;
  }

  public Statistics getStatistics () { 
    /* This is so that the statistics class can get the statistics from the cafeQueue
     * and then the app class can call the printStatistics function on it
     */
    return cafeStatistics;
  }
}
