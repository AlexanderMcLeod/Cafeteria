package test;

import java.util.Stack;
import cafe.Timeline;

public class TimelineTest {

  public static boolean toStackTest () {
    Stack<Timeline.Minute> emptyStack = new Stack<Timeline.Minute>(); // Creates an empty stack
    Timeline timelineTest = new Timeline();
    return (timelineTest.toStack().equals(emptyStack)); // Checks whether our new stack (which should be empty) is equal to our empty stack
  }

  public static boolean addNewMinuteTest () {
    Timeline timelineTest = new Timeline();
    timelineTest.addNewMinute();
    return (!timelineTest.toStack().isEmpty());
  }

  public static boolean replaceMinuteWithTest () {
    Timeline timelineTest = new Timeline();

    timelineTest.addNewMinute();
    timelineTest.replaceMinuteWith(new Timeline.Minute(0, 1, 1, 1));
    return (timelineTest.getMinuteFromIndex(0).getStudentsAddedToQueue() == 1);
  }

  public static void main(String[] args){
    System.out.println("Testing Timeline to Stack Function: " + toStackTest());
    System.out.println("Testing Adding New Minute Function: " + addNewMinuteTest());
    System.out.println("Testing Replace Minute With Function: " + replaceMinuteWithTest());
  }
  
}
