package cafe;

public class Simulation {

  private Timeline timeline; // Timeline that the simulator uses

  /* Constructor function*/
  public Simulation (final Timeline newTimeline) {
    timeline = newTimeline;
  }

  public Statistics getSimulation (final boolean STAFF_WILL_PUSH_IN) {

    CafeQueue cafeQueue = new CafeQueue();

    for (int minuteIndex = 0; minuteIndex < timeline.size(); minuteIndex++){

      Timeline.Minute minute = timeline.getMinute(minuteIndex);

      cafeQueue.addManyStudents(minuteIndex, minute.getStudentsAddedToQueue());
      cafeQueue.addManyStaff(minuteIndex, STAFF_WILL_PUSH_IN, minute.getStaffAddedToQueue());
      cafeQueue.removeManyFromFront(minuteIndex, minute.getCustomersRemovedFromQueue());

    }

    cafeQueue.getStatistics().setStudentQueueSize(Statistics.getStudentQueueSizeFromQueue(cafeQueue));
    cafeQueue.getStatistics().setStaffQueueSize(Statistics.getStaffQueueSizeFromQueue(cafeQueue));

    return cafeQueue.getStatistics();

  }
  
}
