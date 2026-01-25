package com.example.task.task.persistence;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.task.task.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class TaskPersistence {


    private static final String FILE_PATH = "tasks.json";

    //variable para formatear el json
    private static final Gson json = new GsonBuilder().setPrettyPrinting().create();



    public static void saveTasksToFile( List<Task> tasks) {
    //Esto se llama try-with-resources (introducido en Java 7). Es una forma mejorada de manejar recursos que deben cerrarse automáticamente.
    //writer es una variable para escribir un archivo
        try(Writer writer = new FileWriter(FILE_PATH)) {
            //convierte una lista de objetos Task a formato JSON y escribe ese JSON directamente en el archivo.
            json.toJson(tasks, writer);
        } catch (Exception e) {
            System.out.println("Error al guardar las tareas en el archivo: " + e.getMessage());
        }

    }   


    public static List<Task> loadTasksFromFile() {

        File file = new File(FILE_PATH);
        if (!file.exists()) { 
           // si no existe un file, devuelve una lista vacia  
            return new ArrayList<>(); // Devuelve una lista vacía si el archivo no existe
        }

         try(Reader reader = new FileReader(FILE_PATH)) {
           
    //•	Crea una clase anónima
	//•	Captura el tipo genérico List<Task>
	//•	Evita que se pierda por el type erasure
    //en tiempo de ejecucion las listas son genericas de objetos, no listas de un tipo especifico como Task
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            //le pasamos el archivo que esta leyendo y el tipo de dato que queremos obtener
            return json.fromJson(reader, taskListType);

        } catch (Exception e) {
            System.out.println("Error al guardar las tareas en el archivo: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacía en caso de error
        }
       
    }   



}


