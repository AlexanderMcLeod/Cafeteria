package cafe;

public class Customer {

  private int minuteJoined;
  private int minuteLeft;

  public void joinQueue (final int CURRENT_MINUTE) { // Saves the time that they joined the queue
    minuteJoined = CURRENT_MINUTE;
  }

  public void leaveQueue (final int CURRENT_MINUTE) { // Saves the time that they left the queue
    minuteLeft = CURRENT_MINUTE;
  }

  public int getWaitTime () { // Calculate how long they waited
    return (minuteLeft - minuteJoined); // Returns the minute that they joined the queue subtracted from the minute they left
  }

  protected boolean willPushIn; // Will the customer push into the front of the queue

  public boolean getWillPushIn () {
    return willPushIn;
  }

  /* Set function for willPushinIn only in staff (child) class
   * because student cannot change this
   * therefore cannot be in customer class 
   */
  
}
