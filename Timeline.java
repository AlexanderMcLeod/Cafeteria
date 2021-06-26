

import java.util.Stack;
import java.io.Serializable;

public class Timeline implements Serializable{

    private Stack<Minute> minuteList = new Stack<Minute>(); // Stack because LIFO

    public void addMinute(){
        Minute minuteToAdd = new Minute();
        minuteToAdd.setID(minuteList.size() + 1); // minuteID's start at 1 not 0
        minuteList.push(minuteToAdd);
    }

    public void removeMinute(){
        minuteList.pop();
    }

    public Minute getMinute(final int minuteID){
        return minuteList.get(minuteID - 1); // minuteID's start at 1 not 0
    }

    public Stack<Minute> getMinuteList(){
        return minuteList;
    }


    
}
