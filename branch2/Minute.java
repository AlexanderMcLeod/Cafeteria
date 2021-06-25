package branch2;

import java.io.Serializable;

public class Minute implements Serializable{

    private int minuteID;

    int studentsAdded;
    int staffAdded;
    int customersServed;

    public void setID(final int ID_TO_SET){
        minuteID = ID_TO_SET;
    }

    public int getID(){
        return minuteID;
    }

    public void setStudentsAdded(final int ADDED_STUDENTS){
        studentsAdded = ADDED_STUDENTS;
    }

    public int getStudentsAdded(){
        return studentsAdded;
    }

    public void setStaffAdded(final int ADDED_STAFF){
        staffAdded = ADDED_STAFF;
    }

    public int getStaffAddedd(){
        return staffAdded;
    }

    public void setCustomersServed(final int SERVED_CUSTOMERS){
        customersServed = SERVED_CUSTOMERS;
    }

    public int getCustomersServed(){
        return customersServed;
    }
    
}
