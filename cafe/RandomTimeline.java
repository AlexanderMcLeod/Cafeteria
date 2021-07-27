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

      timeline.addNewMinute();

      int randomStudentCount = new Random().nextInt(MAX_STUDENTS_PER_MINUTE);
      timeline.getMinute(minuteIndex).setStudentsAddedToQueue(randomStudentCount);
      personCount += randomStudentCount;

      int randomStaffCount = new Random().nextInt(MAX_STAFF_PER_MINUTE);
      timeline.getMinute(minuteIndex).setStaffAddedToQueue(randomStaffCount);
      personCount += randomStaffCount;

      int randomCustomersRemovedCount = new Random().nextInt(Math.min(MAX_CUSTOMERS_SERVED_PER_MINUTE, personCount));
      timeline.getMinute(minuteIndex).setCustomersRemovedFromQueue(randomCustomersRemovedCount);
      personCount -= randomCustomersRemovedCount;

    }

    int minuteIndex = minuteCount - 1;
    timeline.getMinute(minuteIndex).setCustomersRemovedFromQueue(personCount);
    return timeline;
    
  }


}