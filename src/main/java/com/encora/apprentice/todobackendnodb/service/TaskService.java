package com.encora.apprentice.todobackendnodb.service;

import com.encora.apprentice.todobackendnodb.model.Task;
import com.encora.apprentice.todobackendnodb.repository.InMemoryTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
//    @Autowired
    private final InMemoryTaskRepository repository;

    @Autowired
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
                        2,
                        LocalDateTime.now()
                        ),
                new Task(2L,
                        "Jimmy's birthday",
                        LocalDateTime.of(2023, Month.AUGUST, 24, 12, 00),
                        false,
                        null,
                        3,
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
    public List<Task> findAllByPage(int page){
//        List<Task> list = new ArrayList<>();
//        Iterable<Task> tasks = repository.findAllByPage(page, 10);
//        tasks.forEach(list::add);
        return repository.findAllByPage(page, 10);
    }
    public Optional<Task> find(Long id){
        return Optional.ofNullable(repository.findById(id));
    }
    public Task create(Task task){
        Long nextId = repository.nextId();
        Task copy = new Task(
                nextId,
                task.getText(),
                task.getDueDate(),
                false,
                task.getDoneDate(),
                task.getPriority(),
                LocalDateTime.now()
        );
        return repository.save(copy);
    }
    public Optional<Task> update(Long id, Task newTask){
        newTask.setId(id);
        return Optional.ofNullable(repository.updateWith(newTask));
    }

    public Optional<Task> setAsDone(Long id){
        return Optional.ofNullable(repository.setAsDone(id));
    }
    public Optional<Task> setAsUndone(Long id){
        return Optional.ofNullable(repository.setAsUndone(id));
    }

//    public void delete(Long id){
//        repository.deleteById(id);
//    }
}
