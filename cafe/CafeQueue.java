package cafe;

import java.util.LinkedList;

public class CafeQueue {

  private LinkedList<Customer> cafeQueuePushedIn = new LinkedList<Customer>(); // Linked list for those pushing in
  private LinkedList<Customer> cafeQueueNotPushedIn = new LinkedList<Customer>(); // Linked list for those not pushing in

  private Statistics cafeStatistics = new Statistics();

  /* This adds a specified number
   * of students to the queue
   */

  public void addManyStudents(final int CURRENT_MINUTE, final int STUDENT_COUNT) {
    for (int i = 0; i < STUDENT_COUNT; i++) { 
      addStudent(CURRENT_MINUTE);
    }
  }

  /* Adds a new student 
   * to the queue
   */

  public void addStudent (final int CURRENT_MINUTE) { 
    
    Student newStudent = new Student(); // Instantiates new student
    newStudent.joinQueue(CURRENT_MINUTE); // Saves what minute they joined the queue

    if (newStudent.getWillPushIn()) {
      cafeQueuePushedIn.addLast(newStudent);
    } else if (!newStudent.getWillPushIn()) {
      cafeQueueNotPushedIn.addLast(newStudent); // Adds them to the end of the queue
    }

  }

  // Adding multiple staff simnultaneously
  public void addManyStaff(final int CURRENT_MINUTE, final boolean WILL_PUSH_IN, final int STAFF_COUNT) {
    for (int i = 0; i < STAFF_COUNT; i++) {
      addStaff(CURRENT_MINUTE, WILL_PUSH_IN);
    }
  }

  public void addStaff(final int CURRENT_MINUTE, final boolean WILL_PUSH_IN) {

    Staff newStaff = new Staff(WILL_PUSH_IN); // True means that they will push in
    newStaff.joinQueue(CURRENT_MINUTE); // Start the timer that they joined in

    if (newStaff.getWillPushIn()) {
      cafeQueuePushedIn.addLast(newStaff); // If they are pushing in, add them to the end of the pushed in queue
    } else if (!newStaff.getWillPushIn()) {
      cafeQueueNotPushedIn.addLast(newStaff); // If they are not pushing in, add them to the back 
    }

  }

  public void removeManyFromFront (final int CURRENT_MINUTE, final int CUSTOMERS_TO_REMOVE_COUNT) {
    for (int i = 0; i < CUSTOMERS_TO_REMOVE_COUNT; i++) {
      removeFromFront(CURRENT_MINUTE);
    }
  }

  public void removeFromFront (final int CURRENT_MINUTE) {

    /* Checks whether there is a student to remove */

    if (!cafeQueuePushedIn.isEmpty()) {
      cafeQueuePushedIn.peek().leaveQueue(CURRENT_MINUTE); // Stops the customers timer
      cafeStatistics.addCustomer(cafeQueuePushedIn.peek()); // Adds the customer into statistics
      cafeQueuePushedIn.removeFirst(); // Removes the customer from the queue
    } 
    
    if (cafeQueuePushedIn.isEmpty() && !cafeQueueNotPushedIn.isEmpty()) {
      cafeQueueNotPushedIn.peek().leaveQueue(CURRENT_MINUTE); // Stops the customers timer
      cafeStatistics.addCustomer(cafeQueueNotPushedIn.peek()); // Adds the customer into statistics
      cafeQueueNotPushedIn.removeFirst(); // Removes the customer from the queue
    }


    if (cafeQueuePushedIn.isEmpty() && cafeQueueNotPushedIn.isEmpty()){
      System.out.println("Tried serving a customer when the queue was empty in minute " + CURRENT_MINUTE);
      return; // Does not try to remove a customer if the queue is empty
    }

  }

  public LinkedList<Customer> getCustomersStillQueued () {

    /* Combines the linked list of people that have pushed in
     * with people that have not pushed in
     * and returns that
     */

    LinkedList<Customer> cafeQueueCombined = new LinkedList<Customer>();

    for (Customer customer : cafeQueuePushedIn) {
      cafeQueueCombined.addLast(customer);
    }

    for (Customer customer : cafeQueueNotPushedIn) {
      cafeQueueCombined.addLast(customer);
    }

    return cafeQueueCombined;

  }

  public Statistics getStatistics () { 
    /* This is so that the statistics class can get the statistics from the cafeQueue
     * and then the app class can call the printStatistics function on it
     */
    return cafeStatistics;
  }
}
