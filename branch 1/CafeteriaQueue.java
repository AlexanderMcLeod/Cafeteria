import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.Serializable;

public class CafeteriaQueue implements Serializable{

    private int timeSinceStart; // Measure in seconds, shows time since the start of the simulation

    Queue<Customer> pushedInQueue = new LinkedList<>();
    Queue<Customer> nonPushedInQueue = new LinkedList<>();

    ArrayList<Customer> servedCustomers = new ArrayList<>();

    int studentWaitTimeTotal = 0;
    int staffWaitTimeTotal = 0;

    int studentsServedCount = 0;
    int staffServedCount = 0;

    public int getStudentWaitTimeTotal(){
        return studentWaitTimeTotal;
    }

    public int getStaffWaitTimeTotal(){
        return staffWaitTimeTotal;
    }

    public int getStudentsServedCount(){
        return studentsServedCount;
    }

    public int getStaffServedCount(){
        return staffServedCount;
    }

    public double getMeanStudentWaitTimeMinutes(){ // Mean

        if (getStudentsServedCount() == 0){
            return 0.0;
        }

        return (getStudentWaitTimeTotal() / getStudentsServedCount());
    }

    public double getMeanStaffWaitTimeMinutes(){ // Mean

        if (getStaffServedCount() == 0){
            return 0.0;
        }

        return (getStaffWaitTimeTotal() / getStaffServedCount());
    }


    public void addCustomerToQueue(Customer customerToAdd){

        if (customerToAdd.getCanPushIn() == true){
            pushedInQueue.add(customerToAdd);
            customerToAdd.joinQueue(timeSinceStart);
        }

        if (customerToAdd.getCanPushIn() == false){
            nonPushedInQueue.add(customerToAdd);
            customerToAdd.joinQueue(timeSinceStart);
        }
    }

    public void serveAllCustomers(){

        final int CUSTOMER_COUNT = pushedInQueue.size() + nonPushedInQueue.size();

        for (int i = 0; i < CUSTOMER_COUNT; i++){
            serveCustomer();
        }
    }

    public void serveCustomer(){

        // Serves pushedInQueue firstS
        if (!pushedInQueue.isEmpty()){

            // Does not include serve time in wait time
            pushedInQueue.peek().leaveQueue(timeSinceStart); // This will stop the timer for time spent in the queue
            timeSinceStart += pushedInQueue.peek().getOrderTime(); // Adds persons wait time to time passed
            
            addTime(pushedInQueue.peek());

            servedCustomers.add(pushedInQueue.peek()); // Adds the person to the list of people that were served
            pushedInQueue.remove(); // Removes the person from the line

            return;

        }

        if (pushedInQueue.isEmpty() && !nonPushedInQueue.isEmpty()){

            // Does not include serve time in wait time
            nonPushedInQueue.peek().leaveQueue(timeSinceStart); // This will stop the timer for time spent in the queue
            timeSinceStart += nonPushedInQueue.peek().getOrderTime(); // This will get how long it takes to serve the person and add it to the time

            addTime(nonPushedInQueue.peek());

            servedCustomers.add(nonPushedInQueue.peek()); // Adds the person to the list of people that were served
            nonPushedInQueue.remove(); // Removes the person from the line
            return;
        }
        return;
    }

    public void addTime(Customer customer){

        if (customer.getRole().equals("Staff")){
            staffWaitTimeTotal += customer.getWaitTime();
            staffServedCount++;
        }

        if (customer.getRole().equals("Student")){
            studentWaitTimeTotal += customer.getWaitTime();
            studentsServedCount++;
        }
    }

    public void wait(final int TIME_TO_WAIT){
        timeSinceStart += TIME_TO_WAIT;
    }
    
}
