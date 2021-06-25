import java.io.*;

public class Simulation {

    public static void runSimulation(final String PATH) throws ClassNotFoundException, IOException{

        Timeline timeline  = openFile(PATH);

        for (String command : timeline.getTimeline()){

        }


    }

    public static CafeteriaQueue runCommand(final String COMMAND){

        if (COMMAND.substring(0, ))

    }

    public static Timeline openFile(final String PATH) throws ClassNotFoundException, IOException{

        FileInputStream file = new FileInputStream(PATH);
        ObjectInputStream obj = new ObjectInputStream(file);

        Timeline cafeteriaQueueToReturn = (Timeline) obj.readObject();

        obj.close();
        file.close();

        return cafeteriaQueueToReturn;

    }

    public static void main(String[] args){

        CafeteriaQueue cq = new CafeteriaQueue();

        Customer c1 = new Customer("Student", false, 30);
        Customer c2 = new Customer("Staff", false, 30);
        Customer c3 = new Customer("Staff", false, 30);
        Customer c4 = new Customer("Student", false, 30);

        cq.addCustomerToQueue(c1);
        cq.addCustomerToQueue(c2);
        cq.serveCustomer();
        cq.addCustomerToQueue(c3);
        cq.serveCustomer();
        cq.serveCustomer();
        cq.wait(21);
        cq.addCustomerToQueue(c4);
        cq.serveCustomer();

        System.out.println("Staff average wait time: " + Double.valueOf(cq.getMeanStaffWaitTimeMinutes()));
        System.out.println("Student average wait time: " + Double.valueOf(cq.getMeanStudentWaitTimeMinutes()));

    }
    
}
