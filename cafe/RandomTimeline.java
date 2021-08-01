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

    int totalStudentsAddedCount = 0;
    int totalStaffAddedCount = 0;

    int personCount = 0;
    

    for (int minuteIndex = 0; minuteIndex < minuteCount; minuteIndex++) {

      Timeline.Minute minToBeAdded = new Timeline.Minute(minuteIndex,0,0,0); // Instantiated a new minute to set the values for

      int randomStudentCount = new Random().nextInt(MAX_STUDENTS_PER_MINUTE); // Randomly chooses how many students are being added in that minute
      minToBeAdded.setStudentsAddedToQueue(randomStudentCount); // Sets that random number in the timeline
      
      totalStudentsAddedCount += randomStudentCount; // Updates the amount of students that have been added in total
      personCount += randomStudentCount; // Updates the amount of people currently in the queue

      int randomStaffCount = new Random().nextInt(MAX_STAFF_PER_MINUTE); // Randomly chooses how many staff are being added to the queue in that minute
      minToBeAdded.setStaffAddedToQueue(randomStaffCount); // Sets that random number in the timeline
      
      totalStaffAddedCount += randomStaffCount; // Updates the amount of staff that have been added in total
      personCount += randomStaffCount; // Updates the amount of people currently in the queue

      int randomCustomersRemovedCount = new Random().nextInt(Math.min(MAX_CUSTOMERS_SERVED_PER_MINUTE, personCount)); // Randomly chooses how many people are being served in that minute
      
      minToBeAdded.setCustomersRemovedFromQueue(randomCustomersRemovedCount); // Sets that random number in the timeline
      personCount -= randomCustomersRemovedCount; // Updates the amount of people currently in the queue

      timeline.getMinuteStack().add(minToBeAdded);

    }

    if (totalStaffAddedCount == 0 || totalStudentsAddedCount == 0) { // Makes sure that both students and staff have atleast one person
      return createRandomTimeline(); // Tries to create a new timeline if they do not 
    } else {
      return timeline;
    }
    
  }


}