package branch2;

public class Simulation{

    public static void main(String[] args){

        Timeline timeline = new Timeline();

        timeline.addMinute();
        timeline.getMinute(1).setStudentsAdded(4);
        timeline.getMinute(1).setStaffAdded(3);
        timeline.getMinute(1).setCustomersServed(2);

        timeline.addMinute();
        timeline.getMinute(2).setStudentsAdded(2);
        timeline.getMinute(2).setStaffAdded(1);
        timeline.getMinute(2).setCustomersServed(2);

        timeline.addMinute();
        timeline.getMinute(3).setStudentsAdded(1);
        timeline.getMinute(3).setStaffAdded(5);
        timeline.getMinute(3).setCustomersServed(12);

        CafeteriaQueue noPushing = runSimulation(timeline, false);
        CafeteriaQueue pushing = runSimulation(timeline, true);

        System.out.println("Student mean wait time in no pushing simulation: " + noPushing.getMeanStudentWaitTime());
        System.out.println("Staff mean wait time in no pushing simulation: " + noPushing.getMeanStaffWaitTime());

        System.out.println("Student mean wait time in pushing simulation: " + pushing.getMeanStudentWaitTime());
        System.out.println("Staff mean wait time in pushing simulation: " + pushing.getMeanStaffWaitTime());

    }

    public static CafeteriaQueue runSimulation(final Timeline timeline, final boolean PUSHING){

        CafeteriaQueue cafeteriaQueue = new CafeteriaQueue();

        for (Minute minute : timeline.getMinuteList()){

            // Add students
            for (int i = 0; i < minute.getStudentsAdded(); i++){
                cafeteriaQueue.addStudent(minute.getID());
            }

            // Add staff
            for (int i = 0; i < minute.getStaffAddedd(); i++){
                cafeteriaQueue.addStaff(PUSHING, minute.getID());
            }

            // Serve customers
            for (int i = 0; i < minute.getCustomersServed(); i++){
                cafeteriaQueue.serveCustomer(minute.getID());
            }

        }

        return cafeteriaQueue;



    }





}