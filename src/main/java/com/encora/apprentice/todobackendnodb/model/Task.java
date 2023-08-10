package com.encora.apprentice.todobackendnodb.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String text;
    private LocalDateTime dueDate;
    private Boolean done;
    private LocalDateTime doneDate;
    private Integer priority;
    private LocalDateTime creationDate;

    public Task(Long id, String text, LocalDateTime dueDate, Boolean done, LocalDateTime doneDate, Integer priority, LocalDateTime creationDate) {
        this.id = id;
        this.text = text;
        this.dueDate = dueDate;
        this.done = done;
        this.doneDate = doneDate;
        this.priority = priority;
        this.creationDate = creationDate;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public Task updateWith(Task task){
        return new Task(
                this.id,
                task.text,
                task.dueDate,
                task.done,
                task.doneDate,
                task.priority,
                task.creationDate
        );
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dueDate=" + dueDate +
                ", done=" + done +
                ", doneDate=" + doneDate +
                ", priority=" + priority +
                ", creationDate=" + creationDate +
                '}';
    }
}
