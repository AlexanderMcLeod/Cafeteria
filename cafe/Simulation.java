package cafe;

public class Simulation {

  private Timeline timeline; // Timeline that the simulator uses

  /* Constructor function*/
  public Simulation (final Timeline newTimeline) {
    timeline = newTimeline;
  }

  public Statistics getPushedInSimulation () {

    CafeQueue cafeQueue = new CafeQueue();

    for (int minuteIndex = 0; minuteIndex < timeline.size(); minuteIndex++){

      Timeline.Minute minute = timeline.getMinute(minuteIndex);

      // Adds students to the queue
      for (int i = 0; i < minute.getStudentsAddedToQueue(); i++){
        cafeQueue.addStudent(minuteIndex);
      }

      // Adds the staff to the queue
      for (int i = 0; i < minute.getStaffAddedToQueue(); i++){
        cafeQueue.addPushingInStaff(minuteIndex);
      }

      // Removes the customers from the queue
      for (int i = 0; i < minute.getCustomersRemovedFromQueue(); i++){
        cafeQueue.removeFromFront(minuteIndex);
      }

    }

    return cafeQueue.getStatistics();

  }

  public Statistics getNotPushedInSimulation () {

    CafeQueue cafeQueue = new CafeQueue();

    for (int minuteIndex = 0; minuteIndex < timeline.size(); minuteIndex++){

      Timeline.Minute minute = timeline.getMinute(minuteIndex);

      // Adds students to the queue
      for (int i = 0; i < minute.getStudentsAddedToQueue(); i++){
        cafeQueue.addStudent(minuteIndex);
      }

      // Adds the staff to the queue
      for (int i = 0; i < minute.getStaffAddedToQueue(); i++){
        cafeQueue.addNonPushingInStaff(minuteIndex);
      }

      // Removes the customers from the queue
      for (int i = 0; i < minute.getCustomersRemovedFromQueue(); i++){
        cafeQueue.removeFromFront(minuteIndex);
      }

    }

    return cafeQueue.getStatistics();

  }
  
}
