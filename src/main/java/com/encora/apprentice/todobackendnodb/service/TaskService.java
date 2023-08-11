package com.encora.apprentice.todobackendnodb.service;

import com.encora.apprentice.todobackendnodb.model.Task;
import com.encora.apprentice.todobackendnodb.repository.InMemoryTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private final InMemoryTaskRepository repository;

    public TaskService(InMemoryTaskRepository repository) {
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
        return Optional.ofNullable(repository.findById(id));
    }
    public Task create(Task task){
        Task copy = new Task(
                new Date().getTime(),
                task.getText(),
                task.getDueDate(),
                task.getDone(),
                task.getDoneDate(),
                task.getPriority(),
                LocalDateTime.now()
        );
        return repository.save(copy);
    }
    public Optional<Task> update(Long id, Task newTask){
        return Optional.ofNullable(repository.updateWith(newTask));
    }

    public Optional<Task> setAsDone(Long id){
        return Optional.ofNullable(repository.setAsDone(id));
    }

//    public void delete(Long id){
//        repository.deleteById(id);
//    }
}
