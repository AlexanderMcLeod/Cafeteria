package cafe;

import java.util.Random;

public class RandomTimeline { 

  public static Timeline createRandomTimeline () {

    Timeline timeline = new Timeline ();

    int minutesInALunchTime = 60;
    int minuteCount = new Random().nextInt(minutesInALunchTime); 

    int maxStudentsPerMinute = 6;
    int maxStaffPerMinute = 5;

    int personCount = 0;
    
    for (int minuteIndex = 0; minuteIndex < minuteCount; minuteIndex++) {

      timeline.addNewMinute();

      int randomStudentCount = new Random().nextInt(maxStudentsPerMinute);
      timeline.getMinute(minuteIndex).setStudentsAddedToQueue(randomStudentCount);
      personCount += randomStudentCount;

      int randomStaffCount = new Random().nextInt(maxStaffPerMinute);
      timeline.getMinute(minuteIndex).setStaffAddedToQueue(randomStaffCount);
      personCount += randomStaffCount;

      int randomCustomersRemovedCount = new Random().nextInt(personCount);
      timeline.getMinute(minuteIndex).setCustomersRemovedFromQueue(randomCustomersRemovedCount);
      personCount -= randomCustomersRemovedCount;

    }

    timeline.getMinute(minuteCount-1).setCustomersRemovedFromQueue(personCount);
    return timeline;
    
  }


}