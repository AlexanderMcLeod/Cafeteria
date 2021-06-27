import src.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import src.CafeteriaQueue;
import src.Minute;
import src.Simulation;
import src.Timeline;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException{

        Scanner scan = new Scanner(System.in);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  

        Timeline openedTimeline = createNewOrOpen(scan);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
        openedTimeline = editTimeline(scan, openedTimeline);

        scan.close();
    }

    public static Timeline editTimeline(Scanner scan, final Timeline TIMELINE) throws ClassNotFoundException, IOException, InterruptedException, InterruptedException{

        Timeline editedTimeline = TIMELINE;

        while (true){

            System.out.println("(ADD) minute, (REMOVE) minute, (EDIT) (MINUTE NUMBER), (EXIT) or (RUN)");
            printTimeline(TIMELINE);

            String answer = scan.nextLine();


            if (answer.toLowerCase().split(" ")[0].equals("add")){
                editedTimeline.addMinute();
            }

            if (answer.toLowerCase().split(" ")[0].equals("remove")){
                if (editedTimeline.getMinuteList().size() > 1){
                    editedTimeline.removeMinute();
                } else {
                    System.out.println("Cannot remove minute");
                }
                
            }

            if (answer.toLowerCase().split(" ")[0].equals("edit")){
                editedTimeline.setMinute(Integer.parseInt(answer.split(" ")[1]), editMinute(scan, editedTimeline.getMinute(Integer.parseInt(answer.split(" ")[1])), TIMELINE));
            }

            if (answer.toLowerCase().split(" ")[0].equals("run")){

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
                printSummaryStatistics(TIMELINE);

                System.out.println("Enter anything to continue");
                answer = scan.nextLine();

            }

            if (answer.toLowerCase().split(" ")[0].equals("exit")){
                saveFile(editedTimeline, editedTimeline.getPath());
                break;
            }

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  

        }

        return editedTimeline;

    }

    public static void printSummaryStatistics(final Timeline TIMELINE){

        CafeteriaQueue pushingInSim = Simulation.runSimulation(TIMELINE, true);
        CafeteriaQueue notPushingInSim = Simulation.runSimulation(TIMELINE, false);

        String leftAlignFormat = "| %-21s | %-11s | %-20s |%n";

        System.out.format("+-----------------------+-------------+----------------------+%n");
        System.out.format("| Simulation Type       | Role        | Wait Time (Minutes)  |%n");
        System.out.format("+-----------------------+-------------+----------------------+%n");

        System.out.format(leftAlignFormat, "Pushing In" , "Student", Double.valueOf(pushingInSim.getMeanStudentWaitTime()));
        
        System.out.format("| Simulation            |-------------+----------------------+%n");

        System.out.format(leftAlignFormat, "" , "Staff", Double.valueOf(pushingInSim.getMeanStaffWaitTime()));

        System.out.format("+-----------------------+-------------+----------------------+%n");

        System.out.format(leftAlignFormat, "Not Pushing In" , "Student", Double.valueOf(notPushingInSim.getMeanStudentWaitTime()));

        System.out.format("| Simulation            |-------------+----------------------+%n");

        System.out.format(leftAlignFormat, "" , "Student", Double.valueOf(notPushingInSim.getMeanStaffWaitTime()));

        System.out.format("+-----------------------+-------------+----------------------+%n");
        
    }

    public static Minute editMinute(Scanner scan, final Minute MINUTE, final Timeline TIMELINE) throws IOException, InterruptedException{

        Minute editedMinute = MINUTE;

        while (true){

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  

            System.out.println("Set (STUDENTS) (INTEGER), (STAFF) (INTEGER), (CUSTOMERS) (INTEGER) or (EXIT)");
            printTimeline(TIMELINE);

            String answer = scan.nextLine();


            if (answer.toLowerCase().split(" ")[0].equals("students")){
                editedMinute.setStudentsAdded(Integer.parseInt(answer.split(" ")[1]));
            }

            if (answer.toLowerCase().split(" ")[0].equals("staff")){
                editedMinute.setStaffAdded(Integer.parseInt(answer.split(" ")[1]));
            }

            if (answer.toLowerCase().split(" ")[0].equals("customers")){
                editedMinute.setCustomersServed(Integer.parseInt(answer.split(" ")[1]));
            }

            if (answer.toLowerCase().split(" ")[0].equals("exit")){
                break;
            }
        }


        return editedMinute;

    }

    public static void printMinute(final Minute MINUTE){

        String leftAlignFormat = "| %-20s | %-4d | ";
        int minuteCount = 1;


        for (int i = 0; i < minuteCount; i++){
            System.out.format("+----------------------+------+");
            System.out.format(" ");
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format("| Minute " + MINUTE.getID() +  "             |      |");
            System.out.format(" ");
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format("+----------------------+------+");
            System.out.format(" ");
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format(leftAlignFormat, "Students Added" , MINUTE.getStudentsAdded());
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format(leftAlignFormat, "Staff Added" , MINUTE.getStaffAddedd());
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format(leftAlignFormat, "Customers Served" , MINUTE.getCustomersServed());
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format("+----------------------+------+");
            System.out.format(" ");
        } System.out.format("%n");


    }

    public static void printTimeline(final Timeline TIMELINE){

        String leftAlignFormat = "| %-20s | %-4d | ";
        int minuteCount = TIMELINE.getMinuteList().size();


        for (int i = 0; i < minuteCount; i++){
            System.out.format("+----------------------+------+");
            System.out.format(" ");
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format("| Minute " + Integer.valueOf(i+1) +  "             |      |");
            System.out.format(" ");
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format("+----------------------+------+");
            System.out.format(" ");
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format(leftAlignFormat, "Students Added" , TIMELINE.getMinute(i+1).getStudentsAdded());
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format(leftAlignFormat, "Staff Added" , TIMELINE.getMinute(i+1).getStaffAddedd());
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format(leftAlignFormat, "Customers Served" , TIMELINE.getMinute(i+1).getCustomersServed());
        } System.out.format("%n");

        for (int i = 0; i < minuteCount; i++){
            System.out.format("+----------------------+------+");
            System.out.format(" ");
        } System.out.format("%n");


    }

    public static Timeline createNewOrOpen(Scanner scan) throws ClassNotFoundException, IOException, InterruptedException{

        while (true){

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  

            System.out.println("Create (NEW) (FILENAME), (OPEN) (FILENAME) OR (REMOVE) (FILENAME):\n");

            System.out.println("Available Files:");
            showPaths();
            System.out.println(); // Adds blank line

            String answer = scan.nextLine();

            if (answer.toLowerCase().split(" ")[0].toLowerCase().equals("new")){ // If the response contains the letter w

                createNew(answer.split(" ")[1]);
            }

            if (answer.toLowerCase().split(" ")[0].toLowerCase().equals("open")){ // If the response contains the letter o

                Timeline new_timeline = openFile("saved_data/" + answer.split(" ")[1]);
                new_timeline.setPath("saved_data/" + answer.split(" ")[1]);

                return new_timeline;
            }

            if (answer.toLowerCase().split(" ")[0].toLowerCase().equals("remove")){ // If the response contains the letter o

                removeFile("saved_data/" + answer.split(" ")[1]);
            }

            if (answer.toLowerCase().split(" ")[0].toLowerCase().equals("exit")){ // If the response contains the letter o
                break;
            }
        }

        return null;

    }
    
    public static void createNew(final String FILE_NAME) throws ClassNotFoundException, IOException{

        Timeline newTimeline = new Timeline();
        newTimeline.addMinute();

        saveFile(newTimeline, "saved_data/" + FILE_NAME);
    }


    public static void showPaths() throws ClassNotFoundException, IOException{

        ArrayList<String> nameList = getAllFileNames();

        for (String fileName : nameList){
            System.out.println(fileName);
        }

    }

    public static ArrayList<String> getAllFileNames() throws IOException, ClassNotFoundException{

        File[] files = new File("saved_data").listFiles();
        ArrayList<String> nameList = new ArrayList<String>();
        
        for (File file : files){
            nameList.add(file.getName());
        }

        if (files.length == 0){
            nameList.add("None");
        }

        return nameList;

    }

    public static Timeline openFile(final String PATH) throws IOException, ClassNotFoundException{

        FileInputStream file = new FileInputStream(PATH);
        ObjectInputStream obj = new ObjectInputStream(file);

        Timeline timeline = (Timeline) obj.readObject();

        file.close();
        obj.close();

        return timeline;

    }

    public static void saveFile(final Timeline TIMELINE, final String PATH) throws IOException, ClassNotFoundException{

        File newFile = new File(PATH);
        newFile.createNewFile();

        FileOutputStream file = new FileOutputStream(newFile, false);
        ObjectOutputStream obj = new ObjectOutputStream(file);

        obj.writeObject(TIMELINE);

        file.close();
        obj.close();

    }

    public static void removeFile(final String PATH){
        new File(PATH).delete();
    }




}