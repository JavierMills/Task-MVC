package com.example.task.task.model;

import java.util.ArrayList;
import java.util.List;

import com.example.task.task.excepciones.TaskException;
import com.example.task.task.persistence.TaskPersistence;

public class TaskRepository {

    List<Task> tasks;

    public TaskRepository() {
        // cargamos las tareas desde el archivo TaskPersistence al iniciar el
        // repositorio, ES ESTE PUNTO TENDREMOS UNA LISTA CON LAS TAREAS GUARDADAS
        this.tasks = TaskPersistence.loadTasksFromFile();
    }

    public void save(Task task) throws TaskException {
        if (tasks.contains(task)) {
            throw new TaskException("La tarea ya en base de datos");
        }
        tasks.add(task);
        // guardamos las tareas en el archivo cada que se agrega una nueva tarea
        TaskPersistence.saveTasksToFile(tasks);
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

    public List<Task> findCompletedTask() throws TaskException {

        List<Task> complatedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getCompleted()) {
                complatedTasks.add(task);
            }
        }

        if (complatedTasks.isEmpty()) {
            throw new TaskException("No hay tareas completadas en la lista");
        }
        // si no la encontramos devolvemos null
        return complatedTasks;
    }

    public List<Task> findPendingTask() throws TaskException {

        List<Task> pendingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (!task.getCompleted()) {
                pendingTasks.add(task);
            }
        }

        if (pendingTasks.isEmpty()) {
            throw new TaskException("No hay tareas pendientes en la lista");
        }
        // si no la encontramos devolvemos null
        return pendingTasks;
    }

    public void remove(String id) throws TaskException {
        // almacemos la tarea en una variable
        Task task = findById(id);

        if (task == null) {
            throw new TaskException("La tarea no exciste en la lista");
        }
        tasks.remove(task);
        // guardamos las tareas en el archivo cada que se elimina una tarea
        TaskPersistence.saveTasksToFile(tasks);
    }

    public void remove(Task task) throws TaskException {
        if (task == null) {
            throw new TaskException("La tarea no puede ser nula");
        }
        // le decimos que si a tarea no esta en la lista, ! significa NO
        if (!tasks.contains(task)) {
            // no esta en la lista, mandamos excepcion
            throw new TaskException("La tarea no existe en el repositorio");
        }

        tasks.remove(task);
        // guardamos las tareas en el archivo cada que se elimina una tarea
        TaskPersistence.saveTasksToFile(tasks);
    }

    public List<Task> findAll() throws TaskException {
        if (tasks.isEmpty()) {
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

        if (index == -1) {
            throw new TaskException("La tarea no existe en la l");

        }
        tasks.set(index, task);
        // guardamos las tareas en el archivo cada que se actualiza una tarea
        TaskPersistence.saveTasksToFile(tasks);
    }

    public void updateCompleted(String id, Boolean completed) throws TaskException {

        // buscamos el index de la tarea a actualizar
        int index = findByIndexById(id);

        if (index == -1) {
            throw new TaskException("La tarea no existe en la l");

        }
        tasks.get(index).setCompleted(completed);
        // guardamos las tareas en el archivo cada que se actualiza una tarea
        TaskPersistence.saveTasksToFile(tasks);
    }

}
