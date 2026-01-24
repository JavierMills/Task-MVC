package com.example.task;

import com.example.task.task.controller.TaskController;
import com.example.task.task.model.TaskRepository;
import com.example.task.task.view.TaskView;

public class Main {
    public static void main(String[] args) {

        TaskRepository taskRepository = new TaskRepository();
        TaskController taskController = new TaskController(taskRepository);
        TaskView taskView = new TaskView(taskController);

        taskView.showmenu();
        

    }
}