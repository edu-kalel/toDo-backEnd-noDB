package com.encora.apprentice.todobackendnodb.service;

import com.encora.apprentice.todobackendnodb.model.Task;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Service
@EnableMapRepositories
public class TaskService {
    private final CrudRepository<Task, Long> repository;

    public TaskService(CrudRepository<Task, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultTasks());
    }
    private static List<Task> defaultTasks(){
        return List.of(
                new Task(1L,
                        "Update blog with new entries",
                        LocalDateTime.of(2023, Month.AUGUST, 20, 12, 00),
                        false,
                        null,
                        1,
                        LocalDateTime.now()
                        ),
                new Task(2L,
                        "Jimmy's birthday",
                        LocalDateTime.of(2023, Month.AUGUST, 24, 12, 00),
                        false,
                        null,
                        1,
                        LocalDateTime.now()
                ),
                new Task(3L,
                        "Dad's birthday",
                        LocalDateTime.of(2024, Month.AUGUST, 28, 12, 00),
                        false,
                        null,
                        1,
                        LocalDateTime.now()
                )
        );
    }
    public List<Task> findAll(){
        List<Task> list = new ArrayList<>();
        Iterable<Task> tasks = repository.findAll();
        tasks.forEach(list::add);
        return list;
    }
    public Optional<Task> find(Long id){
        return repository.findById(id);
    }
    public Task create(Task task){
        Task copy = new Task(
                new Date().getTime(),
                task.getText(),
                task.getDueDate(),
                task.getDone(),
                task.getDoneDate(),
                task.getPriority(),
                task.getCreationDate()
        );
        return repository.save(copy);
    }
    public Optional<Task> update(Long id, Task newTask){
        return repository.findById(id)
                .map(oldTask -> {
                    Task updated = oldTask.updateWith(newTask);
                    return repository.save(updated);
                });
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
}
