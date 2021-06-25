package branch2;

public class Customer {

    protected boolean canPushIn = false;

    private int minuteJoinedQueue;
    private int minuteLeftQueue;
    private int minutesWaitedInQueue;

    public void joinedQueue(final int MINUTE_ID){
        minuteJoinedQueue = MINUTE_ID;
    }

    public void leftQueue(final int MINUTE_ID){
        minuteLeftQueue = MINUTE_ID;
        minutesWaitedInQueue = minuteLeftQueue - minuteJoinedQueue;
    }

    public int getMinutesWaited(){
        return minutesWaitedInQueue;
    }
    
}
