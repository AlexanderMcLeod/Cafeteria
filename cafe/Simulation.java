package cafe;

import java.util.Stack;

public class Simulation {

  private Timeline timeline; // Timeline that the simulator uses

  /* Constructor function*/
  public Simulation (final Timeline newTimeline) {
    timeline = newTimeline;
  }

  public Statistics getPushedInSimulation () {

    Stack<Timeline.Minute> minuteStack = timeline.toStack();
    CafeQueue cafeQueue = new CafeQueue();

    for (Timeline.Minute minute : minuteStack){

      // Adds students to the queue
      for (int i = 0; i < minute.getStudentsAddedToQueue(); i++){
        cafeQueue.addStudent(minute.getIndex());
      }

      // Adds the staff to the queue
      for (int i = 0; i < minute.getStaffAddedToQueue(); i++){
        cafeQueue.addPushingInStaff(minute.getIndex());
      }

      // Removes the customers from the queue
      for (int i = 0; i < minute.getCustomersRemovedFromQueue(); i++){
        cafeQueue.removeFromFront(minute.getIndex());
      }
    }
    return cafeQueue.getStatistics();
  }

  public Statistics getNonPushedInSimulation () {

    Stack<Timeline.Minute> minuteStack = timeline.toStack();
    CafeQueue cafeQueue = new CafeQueue();

    for (Timeline.Minute minute : minuteStack){

      // Adds students to the queue
      for (int i = 0; i < minute.getStudentsAddedToQueue(); i++){
        cafeQueue.addStudent(minute.getIndex());
      }

      // Adds the staff to the queue
      for (int i = 0; i < minute.getStaffAddedToQueue(); i++){
        cafeQueue.addNonPushingInStaff(minute.getIndex());
      }

      // Removes the customers from the queue
      for (int i = 0; i < minute.getCustomersRemovedFromQueue(); i++){
        cafeQueue.removeFromFront(minute.getIndex());
      }
    }
    return cafeQueue.getStatistics();
  }






  
}
