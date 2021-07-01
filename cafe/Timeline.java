package cafe;

import java.util.Stack;

public class Timeline {

  private Stack<Minute> minuteStack = new Stack<Minute>();

  public int size () { // Returns the size of the timeline
    return minuteStack.size();
  }

  public void addNewMinute () { // Adds a minute to the end of the timeline with the default values of 0 for everything
    int newIndex = minuteStack.size(); // Gets the index that the new minute would be to pass into its constructor
    Minute newMinute = new Minute(newIndex, 0, 0, 0); // Constructs it with blank values
    minuteStack.add(newMinute);
  }

  public void removeLastMinute () { // Removes the minute at the end of the timeline
    minuteStack.pop();
  }

  public Minute getMinute (final int index){
    return minuteStack.get(index);
  }

  public static class Minute {

    /* Constructor class */
    public Minute(int newIndex, int newStudentsAddedToQueue, int newStaffAddedToQueue, int newCustomersRemovedFromQueue){
      setIndex(newIndex);
      setStudentsAddedToQueue(newStudentsAddedToQueue);
      setStaffAddedToQueue(newStaffAddedToQueue);
      setCustomersRemovedFromQueue(newCustomersRemovedFromQueue);
    }

    private int index;

    public int getIndex () { 
      return index;
    }

    public void setIndex (final int newIndex) {
      index = newIndex;
    }

    private int studentsAddedToQueue; // The amount of students being added to the queue during this minute
    private int staffAddedToQueue; // The amount of staff members being added to the queue during this minute
    private int customersRemovedFromQueue; // The customers being remove from the queue during this minute

    public int getStudentsAddedToQueue () { // Gets the amount of students added the queue
      return studentsAddedToQueue;
    }

    public void setStudentsAddedToQueue (final int newStudentsAddedToQueue){ // Sets the amount of students added the queue
      studentsAddedToQueue = newStudentsAddedToQueue;
    }

    public int getStaffAddedToQueue () { // Gets the amount of staff members added the queue
      return staffAddedToQueue;
    }

    public void setStaffAddedToQueue (final int newStaffAddedToQueue) { // Sets the amount of staff members added to the queue
      staffAddedToQueue = newStaffAddedToQueue;
    }

    public int getCustomersRemovedFromQueue () { // Gets the amount of customers removed the queue
      return customersRemovedFromQueue;
    }

    public void setCustomersRemovedFromQueue (final int newCustomersRemovedFromQueue) { // Sets the amount of customers removed the queue
      customersRemovedFromQueue = newCustomersRemovedFromQueue;
    }

  }

}