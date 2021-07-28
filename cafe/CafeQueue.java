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
    cafeQueueNotPushedIn.addLast(newStudent); // Adds them to the end of the queue

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

    if (WILL_PUSH_IN) {
      cafeQueuePushedIn.addLast(newStaff); // If they are pushing in, add them to the end of the pushed in queue
    } else if (!WILL_PUSH_IN) {
      cafeQueueNotPushedIn.addLast(newStaff); // If they are not pushing in, add them to the back 
    }

  }

  public void removeManyFromFront (final int CURRENT_MINUTE, final int CUSTOMERS_TO_REMOVE_COUNT) {
    for (int i = 0; i < CUSTOMERS_TO_REMOVE_COUNT; i++) {
      removeFromFront(CURRENT_MINUTE);
    }
  }

  public void removeFromFront (final int CURRENT_MINUTE) {

    LinkedList<Customer> cafeQueueCombined = getCustomersStillQueued();

    /* Checks whether there is a customer to remove */

    if (cafeQueueCombined.isEmpty()) {
      System.out.println("Tried to serve customer that was not there in minute " + CURRENT_MINUTE);
    }

      cafeQueueCombined.peek().leaveQueue(CURRENT_MINUTE); // Stops the customers timer
      cafeStatistics.addCustomer(cafeQueueCombined.peek()); // Adds the customer into statistics

      if (cafeQueueCombined.peek().getWillPushIn()) {
        cafeQueuePushedIn.removeFirst();
      } else if (!cafeQueueCombined.peek().getWillPushIn()) {
        cafeQueueNotPushedIn.removeFirst();
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
