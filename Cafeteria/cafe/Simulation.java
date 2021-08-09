package cafe;

public class Simulation {

  private Timeline timeline; // Timeline that the simulator uses

  /* Constructor function*/
  public Simulation (final Timeline newTimeline) {
    timeline = newTimeline;
  }

  public Statistics getSimulation (final boolean STAFF_WILL_PUSH_IN) {

    CafeQueue cafeQueue = new CafeQueue(); // Instantiate a new queue

    for (int minuteIndex = 0; minuteIndex < timeline.getMinuteStack().size(); minuteIndex++){ // For every minute in the timeline

      Timeline.Minute minute = timeline.getMinuteStack().get(minuteIndex); // Get the current minute from the timeline

      /*
       * This will add students into the queue first, 
       * then add staff,
       * then start serving the customers
       */

      cafeQueue.addManyStudents(minuteIndex, minute.getStudentsAddedToQueue()); // Add the amount of students added in that minute to the queue
      cafeQueue.addManyStaff(minuteIndex, STAFF_WILL_PUSH_IN, minute.getStaffAddedToQueue()); // Add the amount of staff added in that minute to the queue
      cafeQueue.removeManyFromFront(minuteIndex, minute.getCustomersRemovedFromQueue()); // Remove the amount of customers served in that minute from the queue

    }

    cafeQueue.getStatistics().setStudentQueueSize(Statistics.getStudentQueueSizeFromQueue(cafeQueue)); // Add the amount of people still in the queue to the statistics
    cafeQueue.getStatistics().setStaffQueueSize(Statistics.getStaffQueueSizeFromQueue(cafeQueue));

    return cafeQueue.getStatistics(); // Return the statistics from the simulation

  }
  
}
