package test;

import cafe.Timeline;

public class TimelineTest {

  public static boolean addNewMinuteTest () {
    Timeline timelineTest = new Timeline();
    timelineTest.addNewMinute();
    return (timelineTest.size() != 0);
  }

  public static boolean editMinuteTest () {
    Timeline timelineTest = new Timeline();

    timelineTest.addNewMinute();
    timelineTest.getMinute(0).setStudentsAddedToQueue(1);

    return (timelineTest.getMinute(0).getStudentsAddedToQueue() == 1);
  }

  public static void main(String[] args){
    System.out.println("Testing Adding New Minute Function: " + addNewMinuteTest());
    System.out.println("Testing Replace Minute With Function: " + editMinuteTest());
  }
  
}
