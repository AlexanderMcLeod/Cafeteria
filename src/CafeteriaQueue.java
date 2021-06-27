package src;

import java.util.LinkedList;
import java.util.ArrayList;

public class CafeteriaQueue {

    private LinkedList<Customer> pushedInQueue = new LinkedList<>();
    private LinkedList<Customer> nonPushedInQueue = new LinkedList<>();  

    private ArrayList<Customer> servedCustomers = new ArrayList<>();

    public void addStaff(final boolean CAN_PUSH_IN, final int MINUTE_ID){

        Staff staffToAdd = new Staff(CAN_PUSH_IN);
        staffToAdd.joinedQueue(MINUTE_ID);

        if (CAN_PUSH_IN){
            pushedInQueue.addLast(staffToAdd);
        } 
        if (!CAN_PUSH_IN){
            nonPushedInQueue.addLast(staffToAdd);
        }

    }

    public void addStudent(final int MINUTE_ID){
        Student studentToAdd = new Student();
        studentToAdd.joinedQueue(MINUTE_ID);
        nonPushedInQueue.addLast(studentToAdd);
    }

    public void serveCustomer(final int MINUTE_ID){

        if (!pushedInQueue.isEmpty()){
            pushedInQueue.peek().leftQueue(MINUTE_ID);
            servedCustomers.add(pushedInQueue.peek());
            pushedInQueue.remove();
        }
        if (pushedInQueue.isEmpty() && !nonPushedInQueue.isEmpty()){
            nonPushedInQueue.peek().leftQueue(MINUTE_ID);
            servedCustomers.add(nonPushedInQueue.peek());
            nonPushedInQueue.remove();
        }

    }

    public int getStaffCount(){

        int total = 0;

        for (Customer customer : servedCustomers){
            if (customer instanceof Staff){
                total++;
            }
        }
        return total;
    }

    public int getStudentCount(){

        int total = 0;

        for (Customer customer : servedCustomers){
            if (customer instanceof Student){
                total++;
            }
        }
        return total;
    }

    public int getStudentWaitTime(){

        int totalWaitTime = 0;

        for (Customer customer : servedCustomers){
            if (customer instanceof Student){
                totalWaitTime += customer.getMinutesWaited();
            }
        }
        return totalWaitTime;
    }

    public int getStaffWaitTime(){

        int totalWaitTime = 0;

        for (Customer customer : servedCustomers){
            if (customer instanceof Staff){
                totalWaitTime += customer.getMinutesWaited();
            }
        }
        return totalWaitTime;
    }

    public double getMeanStudentWaitTime(){

        int studentCount = getStudentCount();
        if (studentCount == 0){
            return 0.0;
        }
        return (double) getStudentWaitTime() / (double) studentCount;
    }

    public double getMeanStaffWaitTime(){

        int staffCount = getStudentCount();
        if (staffCount == 0){
            return 0.0;
        }
        return (double) getStaffWaitTime() / (double) staffCount;
    }
    
}
