package src;


import java.util.Stack;
import java.io.Serializable;

public class Timeline implements Serializable{

    private Stack<Minute> minuteList = new Stack<Minute>(); // Stack because LIFO
    private String path;

    public String getPath(){
        return path;
    }

    public void setPath(final String NEW_PATH){
        path = NEW_PATH;
    }

    public void addMinute(){
        Minute minuteToAdd = new Minute();
        minuteToAdd.setID(minuteList.size() + 1); // minuteID's start at 1 not 0

        // Default values
        minuteToAdd.studentsAdded = 0;
        minuteToAdd.staffAdded = 0;
        minuteToAdd.customersServed = 0;

        minuteList.push(minuteToAdd);
    }

    public void removeMinute(){
        minuteList.pop();
    }

    public Minute getMinute(final int minuteID){
        return minuteList.get(minuteID - 1); // minuteID's start at 1 not 0
    }

    public void setMinute(final int MINUTE_ID, final Minute MINUTE){
        minuteList.set(MINUTE_ID-1, MINUTE);
    }

    public Stack<Minute> getMinuteList(){
        return minuteList;
    }


    
}
