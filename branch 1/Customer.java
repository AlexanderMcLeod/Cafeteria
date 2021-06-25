import java.io.Serializable;

public class Customer implements Serializable{

    private final boolean CAN_PUSH_IN;
    private final String ROLE;
    private double orderTimeMinutes; // How long it takes to serve to customer

    public Customer(final String ROLE_SET_TO, final boolean CAN_PUSH_IN_SET_TO, final double ORDER_TIME_MINUTES){

        ROLE = ROLE_SET_TO;
        orderTimeMinutes = ORDER_TIME_MINUTES;
        
        if (ROLE_SET_TO.equals("Student")){
            CAN_PUSH_IN = false;
        } else {
            CAN_PUSH_IN = CAN_PUSH_IN_SET_TO;
        }
        
    }

    public String getRole(){
        return ROLE;
    }

    public boolean getCanPushIn(){
        return CAN_PUSH_IN;
    }

    private double joinQueueTimeMinutes; // When the user joined the queue in seconds since the start of the simulation
    private double leaveQueueTimeMinutes; // When the user left the queue in seconds since the start of the simulation
    private double waitTimeMinutes; // How many seconds the user was in the queue for

    public void joinQueue(double timeJoined){
        joinQueueTimeMinutes = timeJoined; // Gets the clocks time in milliseconds
    }

    public void leaveQueue(double timeLeft){
        leaveQueueTimeMinutes = timeLeft;
        waitTimeMinutes = leaveQueueTimeMinutes - joinQueueTimeMinutes;
    }

    public double getWaitTime(){
        return waitTimeMinutes;
    }

    public double getOrderTime(){ // Getter function for order size
        return orderTimeMinutes;
    }

    
    
    


    
}
