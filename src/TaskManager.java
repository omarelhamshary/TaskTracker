import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    String filePath = "data/tasks.json";
    // Method to create an empty file.
    public void createFile(){
        try{
            File file = new File(filePath);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("[]");
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Adding a new task
    public void AddTask(String taskDescription){
        // Read existing tasks from the json file
        // Append the new task
        // Save the updated list back to the file
        File file = new File(filePath);
        try {
            List<Task> tasks = readTasks();
            Task newTask = new Task(taskDescription);
            tasks.add(newTask);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("[");
            for(int i =0; i< tasks.size(); i++){
                fileWriter.write(tasks.get(i).toString());
                if(i < tasks.size()-1){
                    fileWriter.write(",");
                }
            }
            fileWriter.write("]");
            fileWriter.close();
            System.out.printf("Task added: %s\n", taskDescription);

        } catch (IOException e) {
            System.out.println("Error, Please check: " + e.getMessage());
        }

    }

    public void UpdateTaskStatus(Task task, TaskStatus newStatus) {
        task.setStatus(newStatus);
        task.setModifiedAt(LocalDateTime.now());
    }

    public List<Task> readTasks() throws IOException {
        FileReader fileReader = new FileReader(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            StringBuilder jsonContent = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                jsonContent.append(line);
            }
            bufferedReader.close();

            String content = jsonContent.toString().trim();
            if(content.equals("[]") || content.isEmpty()){
                return tasks;
            }
            else{
                String[] taskArray = content.substring(1, content.length() - 1).split("\\},\\{");
                for(String taskString : taskArray){
                    taskString = taskString.replace("{", "").replace("}", "");
                    String [] fields = taskString.split(",");
                    int id = Integer.parseInt(fields[0].split(":")[1].trim());
                    String description = fields[1].split(":")[1].trim().replace("\"", "");
                    String status = fields[2].split(":")[1].trim().replace("\"", "");

                    Task task = new Task(description);
                    task.setStatus(TaskStatus.valueOf(status));
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file" + e.getMessage());
        }
        return tasks;
    }

}
