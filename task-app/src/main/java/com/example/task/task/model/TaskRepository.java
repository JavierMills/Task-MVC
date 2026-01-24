package com.example.task.task.model;

import java.util.ArrayList;
import java.util.List;

import com.example.task.task.excepciones.TaskException;

public class TaskRepository {

    List<Task> tasks = new ArrayList<>();

    public void save(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("La tarea no puede ser nula");
        }
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

    public void remove(String id) throws TaskException {
        // almacemos la tarea en una variable
        Task task = findById(id);

         if (task == null) {
            throw new TaskException("La tarea no exciste en la lista");
        }
        // si la tarea es diferente de nulll
        if (task != null) {
            tasks.remove(task);
        }
    }

     public void remove(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("La tarea no puede ser nula");
        }
        // le decimos que si a tarea no esta en la lista, ! significa NO
        if (!tasks.contains(task)) {
           // no esta en la lista, mandamos excepcion
            throw new TaskException("La tarea no existe en el repositorio");
        } else {

            // si esta en la lista la eliminamos
            tasks.remove(task);
        }
    }

    public List<Task> findAll() throws TaskException {
        if(tasks.isEmpty()){
            throw new TaskException("No hay tareas en la lista");
        }
        return tasks;
    }

    public int findByIndexById(String id) {
        // recorremos la lista con el tasks.size regresa el numero de elementos de la
        // lista
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;

    }

    public void update(Task task) throws TaskException {

         if (task == null) {
            throw new TaskException("La tarea no puede ser nula");
        }

        // buscamos el index de la tarea a actualizar
        int index = findByIndexById(task.getId());

        if(index == -1){
            throw new TaskException("La tarea no existe en la l");

        }
        tasks.set(index, task);

    }

}
