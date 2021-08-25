package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.add(task);
        ui.printAdd(task, tasks);
    }
}