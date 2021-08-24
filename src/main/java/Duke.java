import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) throws IllegalTaskException {
        ArrayList<Task> list = new ArrayList<Task>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String command = input.split(" ")[0];
        while(!command.equals("bye")){
            try{ 
                if(command.equals("list")){
                    System.out.println("Here are the tasks in your list: ");
                    for(int i = 0; i < list.size(); i++){
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                } else if (command.equals("done")) {
                    int toComplete = Integer.parseInt(input.split(" ")[1]) - 1;
                    list.get(toComplete).complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("  %s", list.get(toComplete)));
                } else if (command.equals("delete")) {
                    int toDelete = Integer.parseInt(input.split(" ")[1]) - 1;
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(String.format("  %s", list.get(toDelete)));
                    list.remove(toDelete);
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                } else if(command.equals("todo")) {
                    String task = input.replaceFirst("todo ","");
                    if (task.equals("todo")){
                        throw new IllegalTaskException();
                    } else { 
                        ToDo toDo = new ToDo(task);
                        list.add(toDo);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(String.format("  %s", toDo.toString()));
                        System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                    }
                } else if (command.equals("deadline")) {
                    String[] taskDate = input.replaceFirst("deadline ", "").split("/by ");
                    String task = taskDate[0];
                    String date = taskDate[1];
                    String[] splitDateTime = date.split(" ");
                    String[] splitDate = splitDateTime[0].split("/");
                    LocalDate localDate;
                    if (splitDate[1].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
                    } else if (splitDate[0].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
                    } else { 
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
                    }
                    LocalTime localTime;
                    localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
                    Deadline deadline = new Deadline(task, localDate, localTime);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(String.format("  %s", deadline.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                } else if (command.equals("event")) {
                    String[] taskDate = input.replaceFirst("event ", "").split("/at ");
                    String task = taskDate[0];
                    String date = taskDate[1];
                    String[] splitDateTime = date.split(" ");
                    String[] splitDate = splitDateTime[0].split("/");
                    LocalDate localDate;
                    if (splitDate[1].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-0" + splitDate[1] + "-" + splitDate[0]);
                    } else if (splitDate[0].length() == 1){
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-0" + splitDate[0]);
                    } else { 
                        localDate = LocalDate.parse(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
                    }
                    LocalTime localTime;
                    localTime = LocalTime.parse(splitDateTime[1], DateTimeFormatter.ofPattern("HHmm"));
                    Event event = new Event(task, localDate, localTime);
                    list.add(event);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(String.format("  %s", event.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                } else {
                    throw new IllegalCommandException();
                }
            } catch (IllegalCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IllegalTaskException e){
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            input = sc.nextLine();
            command = input.split(" ")[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
