package com.example.task.task.model;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
        tasks.add(task);
    }

    // vamos a devolver un task por id
    public Task findById(String id) {
        // recorremos las tareas y comparamos el id
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                // encontramos la tarea y la devolvemos
                return task;
            }
        }
        // si no la encontramos devolvemos null
        return null;
    }

    public void remove(String id) {
        //almacemos la tarea en una variable
        Task task = findById(id);
        //si la tarea es diferente de nulll
        if (task != null) {
            tasks.remove(task);
        }
    }


    public List<Task> findAll() {
        return tasks;
    }

    public int findByIndexById(String id){
        //recorremos la lista con el tasks.size regresa el numero de elementos de la lista
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;

    }

}
