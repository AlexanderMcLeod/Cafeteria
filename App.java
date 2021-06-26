import java.io.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        System.out.println("Create new timeline or open existing timeline?");
        String answer = scan.next();




    }

    public static Timeline openFile(final String PATH) throws IOException, ClassNotFoundException{

        FileInputStream file = new FileInputStream(PATH);
        ObjectInputStream obj = new ObjectInputStream(file);

        Timeline timeline = (Timeline) obj.readObject();

        file.close();
        obj.close();

        return timeline;

    }



}