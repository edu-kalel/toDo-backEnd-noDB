package com.encora.apprentice.todobackendnodb.controller;

import com.encora.apprentice.todobackendnodb.model.Task;
import com.encora.apprentice.todobackendnodb.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todos")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Task>> findAll(){
        List<Task> tasks = service.findAll();
        return ResponseEntity.ok().body(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable("id") Long id){
        Optional<Task> task = service.find(id);
        return ResponseEntity.of(task);
    }
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        Task created = service.create(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(
            @PathVariable("id") Long id,
            @RequestBody Task updatedTask){
        Optional<Task> updated = service.update(id, updatedTask);
        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Task created = service.create(updatedTask);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
