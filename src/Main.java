import java.awt.BorderLayout;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to TaskPlanner");
        TaskManager newTask = new TaskManager();
        boolean applicationIsRunning = true;
        newTask.createFile();

        while (applicationIsRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please choose an option:");
            System.out.println("1. Create a task");
            System.out.println("2. Update list of tasks");
            String option = scanner.nextLine();

            switch (option) {
                case "1":{
                    System.out.println("Please enter the name of the task you would like to create:");
                    String taskDescription = scanner.nextLine();
                    newTask.AddTask(taskDescription);
                    break;
                }
                case "2":{
                    System.out.println("Please enter the name of the task you would like to update:");
                    break;
                }
            }
        }


    }
}