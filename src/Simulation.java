package src;

public class Simulation{

    public static CafeteriaQueue runSimulation(final Timeline timeline, final boolean PUSHING){

        CafeteriaQueue cafeteriaQueue = new CafeteriaQueue();

        for (Minute minute : timeline.getMinuteList()){

            // Add students first
            for (int i = 0; i < minute.getStudentsAdded(); i++){
                // System.out.println("Adding student: " + Integer.valueOf(i));
                cafeteriaQueue.addStudent(minute.getID());
            }

            // Add staff first
            for (int i = 0; i < minute.getStaffAddedd(); i++){
                // System.out.println("Adding staff: " + Integer.valueOf(i));
                cafeteriaQueue.addStaff(PUSHING, minute.getID());
            }

            // Serve customers first
            for (int i = 0; i < minute.getCustomersServed(); i++){
                // System.out.println("Serving customer: " + Integer.valueOf(i));
                cafeteriaQueue.serveCustomer(minute.getID());
            }
        }
        return cafeteriaQueue;
    }

}