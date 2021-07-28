package cafe;

import java.util.Random;

public class RandomTimeline { 

  public static Timeline createRandomTimeline () {

    Timeline timeline = new Timeline ();

    final int MINUTES_IN_A_LUNCH_TIME = 60; // Maximum amount of time in one randomly generated timeline
    final int minuteCount = new Random().nextInt(MINUTES_IN_A_LUNCH_TIME); 

    final int MAX_STUDENTS_PER_MINUTE = 6;
    final int MAX_STAFF_PER_MINUTE = 5;
    final int MAX_CUSTOMERS_SERVED_PER_MINUTE = 6;

    int personCount = 0;
    
    for (int minuteIndex = 0; minuteIndex < minuteCount; minuteIndex++) {

      timeline.addNewMinute(); // Adds a new minute to the timeline

      int randomStudentCount = new Random().nextInt(MAX_STUDENTS_PER_MINUTE); // Randomly chooses how many students are being added in that minute
      timeline.getMinute(minuteIndex).setStudentsAddedToQueue(randomStudentCount); // Sets that random number in the timeline
      personCount += randomStudentCount; // Updates the amount of people currently in the queue

      int randomStaffCount = new Random().nextInt(MAX_STAFF_PER_MINUTE); // Randomly chooses how many staff are being added to the queue in that minute
      timeline.getMinute(minuteIndex).setStaffAddedToQueue(randomStaffCount); // Sets that random number in the timeline
      personCount += randomStaffCount; // Updates the amount of people currently in the queue

      int randomCustomersRemovedCount = new Random().nextInt(Math.min(MAX_CUSTOMERS_SERVED_PER_MINUTE, personCount)); // Randomly chooses how many people are being served in that minute
      timeline.getMinute(minuteIndex).setCustomersRemovedFromQueue(randomCustomersRemovedCount); // Sets that random number in the timeline
      personCount -= randomCustomersRemovedCount; // Updates the amount of people currently in the queue

    }

    int minuteIndex = minuteCount - 1; 
    timeline.getMinute(minuteIndex).setCustomersRemovedFromQueue(personCount); // If there are still people who have not been served, serve all of them
    return timeline; 
    
  }


}