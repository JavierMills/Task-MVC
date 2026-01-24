package com.example.task.task.controller;

import java.util.List;

import com.example.task.task.excepciones.TaskException;
import com.example.task.task.excepciones.TaskValidationException;
import com.example.task.task.model.Task;
import com.example.task.task.model.TaskRepository;

public class TaskController {

    // no vamos a poder utilizar el new para crear un repositorio
    private final TaskRepository taskRepository;

    // cada que definimos una variable con final debemos declararla en el
    // constructor de asiganacion
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task task = new Task(id, title, description, completed);
        taskRepository.save(task);
    }

    public void removeTask(String id) throws TaskValidationException, TaskException{
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("ID no puede estar vacio");
        }
            taskRepository.remove(id);
    }

    public void findAll() throws TaskValidationException , TaskException{
        List<Task> tasks = taskRepository.findAll();

        if(tasks.isEmpty()){
            throw new TaskValidationException("No hay tareas en la lista");
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task updateTask = new Task(id, title, description, completed);
        taskRepository.update(updateTask);
    }

//ste método podría lanzar una TaskValidationException, así que quién lo llame debe estar preparado para capturarla
    public void validateTaskData(String id, String title, String description, Boolean completed) throws TaskValidationException {
        
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("ID no puede estar vacio");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new TaskValidationException("Title no puede estar vacio");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new TaskValidationException("Description no puede estar vacia");
        }

        if( completed == null){
            throw new TaskValidationException("Completed no puede ser nulo");
        }
    }
}