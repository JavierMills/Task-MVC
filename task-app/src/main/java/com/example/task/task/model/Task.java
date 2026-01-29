package com.example.task.task.model;

import java.util.Objects;

public class Task {

    private String id;
    private String title;
    private String descrption;
    private Boolean completed;

    

    public Task(String id, String title, String descrption, Boolean completed) {
        this.id = id;
        this.title = title;
        this.descrption = descrption;
        this.completed = completed;
    }

    public Task() {
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescrption() {
        return descrption;
    }
    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }
    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {

        //¿Es la misma referencia en memoria?
        if (this == o) return true;
        //¿Es null o de diferente clase?
        if (o == null || getClass() != o.getClass()) return false;

        //Convertir el Object a Task
        Task task = (Task) o;
        return Objects.equals(id, task.id);
        
    }

    //NOTE: Si dos objetos son iguales según equals(), DEBEN tener el mismo hashCode()


    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", descrption=" + descrption + ", completed=" + completed + "]";
    }

    

    

}
