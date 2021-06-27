package cafe;

public class Customer {

  private int minuteJoined;
  private int minuteLeft;

  public void joinQueue (final int currentMinute) { // Saves the time that they joined the queue
    minuteJoined = currentMinute;
  }

  public void leaveQueue (final int currentMinute) { // Saves the time that they left the queue
    minuteLeft = currentMinute;
  }

  public int getWaitTime () { // Calculate how long they waited
    return (minuteLeft - minuteJoined); // Returns the minute that they joined the queue subtracted from the minute they left
  }

  protected boolean willPushIn; // Will the customer push into the front of the queue

  public boolean getWillPushIn () {
    return willPushIn;
  }

  /* Set function only in staff class
   * because student cannot change this
   * therefore cannot be in customer class 
   */
  
}
