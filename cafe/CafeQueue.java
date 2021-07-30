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
    newStudent.joinQueue(CURRENT_MINUTE); // Saves what minute they joined the queue ( starts the queue timer)
    cafeQueueNotPushedIn.addLast(newStudent); // Adds them to the end of the queue with people not pushing in

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

    if (WILL_PUSH_IN) { // If they will push in
      cafeQueuePushedIn.addLast(newStaff); // If they are pushing in, add them to the end of the pushed in queue
    } else {
      cafeQueueNotPushedIn.addLast(newStaff); // If they are not pushing in, add them to the back of the non pushed in queue
    }

  }

  public void removeManyFromFront (final int CURRENT_MINUTE, final int CUSTOMERS_TO_REMOVE_COUNT) {
    for (int i = 0; i < CUSTOMERS_TO_REMOVE_COUNT; i++) {
      removeFromFront(CURRENT_MINUTE);
    }
  }

  public void removeFromFront (final int CURRENT_MINUTE) {

    if (!cafeQueuePushedIn.isEmpty()) { // If the cafe queue with people pushing in is not empty
      cafeQueuePushedIn.peek().leaveQueue(CURRENT_MINUTE); // Stops the customers queue timer
      cafeStatistics.addCustomer(cafeQueuePushedIn.peek()); // Adds them into statistics
      cafeQueuePushedIn.removeFirst(); // Removes the first person from the queue
      return; // Returns so that the other if statements do not run
    }

    /*
      This statement will only run if the pushed in queue is empty
     */

    if (!cafeQueueNotPushedIn.isEmpty()) { // If the queue with people not pushin in is not empty
      cafeQueueNotPushedIn.peek().leaveQueue(CURRENT_MINUTE); // Stops the customers queue timer
      cafeStatistics.addCustomer(cafeQueueNotPushedIn.peek()); // Adds that customer into statistics
      cafeQueueNotPushedIn.removeFirst(); // Removes the person in front from the queue
      return; // Returns so that the following statements do not run
    }

    System.out.println("Tried to serve customer that was not there in minute " + CURRENT_MINUTE);

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
