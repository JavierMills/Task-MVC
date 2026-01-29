package com.example.task.task.view;

import com.example.task.task.controller.TaskController;
import com.example.task.task.excepciones.TaskValidationException;
import com.example.task.task.model.Task;
import com.example.task.task.excepciones.TaskException;

import java.util.Scanner;


public class TaskView {

    private final TaskController taskController;
    private final Scanner scanner;

    // iniciallizamos las variables en el contructor para garantizar que el objeto
    // esté completamente inicializado
    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showmenu() {

        while (true) {
            System.out.println("\n Gestión de Tareas ");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Eliminar Tarea");
            System.out.println("3. Actualizar Tarea");
            System.out.println("4. Mostrar Tareas");
            System.out.println("5. Actualizar estado de la tarea");
            System.out.println("6. Mostrar Tareas completas");
            System.out.println("7. Mostrar Tareas pendientes");
            System.out.println("8 Salir");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addTaskView();
                    // código para agregar tarea
                    break;
                case "2":
                    removeTaskView();
                    break;
                case "3":
                    updateTaskView();
                    // código para actualizar tarea
                    break;
                case "4":
                    showTaskView();
                    // código para mostrar tareas
                    break;
                case "5":
                    updateCompletedTaskView();
                    // código para mostrar tareas
                    break;
                 case "6":
                    showCompletedTask();
                    // código para mostrar tareas
                    break;

                 case "7":
                    showPendingTask();
                    // código para mostrar tareas
                    break;
                case "8":
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        }
    }

    // metodo que capturara los datos para agregar una tarea
    public void addTaskView() {

        try {
            Task task = getInputTask();
            taskController.addTask(task.getId(), task.getTitle(), task.getDescrption(), task.getCompleted());
            System.out.println("-------------Tarea agregada exitosamente-------------");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error al agregar tarea: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            // es un método que imprime la traza completa del error (stack trace) mostrando
            // exactamente dónde y cuándo ocurrió la excepción.
            e.printStackTrace();
        }

    }

    public void removeTaskView() {
        try {
            System.out.print("Ingrese ID de la tarea a eliminar: ");
            String id = scanner.nextLine();
            taskController.removeTask(id);
            System.out.println("Tarea eliminada exitosamente.");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error al eliminar tarea: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showTaskView() {
        try {
            System.out.println("\n Lista de Tareas:");
            taskController.findAll();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error al mostrar tareas: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateTaskView() {
        try {
            Task task = getInputTask();
            taskController.updateTask(task.getId(), task.getTitle(), task.getDescrption(), task.getCompleted());
            System.out.println("Tarea actualizada exitosamente.");

        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error al actualizar tarea: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateCompletedTaskView() {
        try {
            System.out.println("Ingrese en ID de la tarea");
            String id = scanner.nextLine();
            Boolean completed = null;

            while (completed == null) {

                System.out.print("¿La tarea está completada? (true/false): ");
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("true")) {
                    completed = true;
                } else if (input.equals("false")) {
                    completed = false;
                } else {
                    System.out.println("Entrada no válida. Por favor ingrese 'true' o 'false'.");

                }

            }
            taskController.updateTaskCompleted(id, completed);
            System.out.println("Estado actual de la tarea actualizado correctamente.");

        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error al actualizar tarea: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showCompletedTask() {
        try {
            System.out.println("Tareas completadas ");
            taskController.findTaskCompleted();
        } catch (TaskException | TaskValidationException e) {
           System.out.println("error " + e.getMessage());
        }
    }

    public void showPendingTask() {
        try {
            System.out.println("Tareas pendientes ");
            taskController.findTaskPending();
        } catch (TaskException | TaskValidationException e) {
           System.out.println("error " + e.getMessage());
        }
    }

    private Task getInputTask() {
        String id;

        // hacemos un do while para validar que el id no este vacio ya que se debe de
        // ejecutar al menos una vez
        do {
            System.out.print("Ingrese ID de la tarea a actualizar: ");
            id = scanner.nextLine();
            // como se ejecuto una vez e do while, validamos si el id esta vacio
            if (id.trim().isEmpty()) {
                System.out.println("El ID no puede estar vacío.");
            }

            // validamos por primera vez y si esta vacio volvemos a pedir el id si esta
            // vacio se vuelve a repetir el ciclo
        } while (id.trim().isEmpty());

        String title;

        do {
            System.out.print("Ingrese título de la tarea a actualizar: ");
            title = scanner.nextLine();
            // como se ejecuto una vez e do while, validamos si el id esta vacio
            if (title.trim().isEmpty()) {
                System.out.println("El título no puede estar vacío.");
            }

            // validamos por primera vez y si esta vacio volvemos a pedir el id si esta
            // vacio se vuelve a repetir el ciclo
        } while (title.trim().isEmpty());

        String description;
        do {
            System.out.print("Ingrese descripción de la tarea a actualizar: ");
            description = scanner.nextLine();
            // como se ejecuto una vez e do while, validamos si el id esta vacio
            if (description.trim().isEmpty()) {
                System.out.println("La descripción no puede estar vacía.");
            }

            // validamos por primera vez y si esta vacio volvemos a pedir el id si esta
            // vacio se vuelve a repetir el ciclo
        } while (description.trim().isEmpty());

        Boolean completed = null;

        while (completed == null) {

            System.out.print("¿La tarea está completada? (true/false): ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("true")) {
                completed = true;
            } else if (input.equals("false")) {
                completed = false;
            } else {
                System.out.println("Entrada no válida. Por favor ingrese 'true' o 'false'.");

            }

        }

        return new Task(id, title, description, completed);
    }

}
