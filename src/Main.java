import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to TaskPlanner");
        TaskManager newTask = new TaskManager();
        boolean applicationIsRunning = true;
        newTask.createFile();

        while (applicationIsRunning) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please choose an option:");
            System.out.println("1. Create a task");
            System.out.println("2. Update list of tasks");
            System.out.println("3. Delete a task");
            System.out.println("4. List all tasks");
            System.out.println("5. Exit");
            String option = scanner.nextLine();

            switch (option) {
                case "1":{
                    System.out.println("Please enter the name of the task you would like to create:");
                    String taskDescription = scanner.nextLine();
                    newTask.AddTask(taskDescription);
                    break;
                }
                case "2":{
                    System.out.println("Please enter the task Id:");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter the new Status:");
                    String newStatus = scanner.nextLine();
                    newTask.UpdateTaskStatus(taskId, TaskStatus.valueOf(newStatus));
                    break;
                }
                case "3":{
                    System.out.println("Please enter the task Id to delete:");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    newTask.deleteTasks(taskId);
                    break;
                }
                case "4":{
                    System.out.println("here is the list of tasks:" + newTask.readTasks());
                    break;
                }
                case "5":{
                    System.out.println("Exiting application");
                    applicationIsRunning = false;
                    break;
                }
            }
        }


    }
}