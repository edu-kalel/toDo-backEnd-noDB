package com.encora.apprentice.todobackendnodb.controller;

import com.encora.apprentice.todobackendnodb.model.Task;
import com.encora.apprentice.todobackendnodb.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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
    ////////////// a pagination
    @GetMapping("/page/{page}")
    public ResponseEntity<List<Task>> findAllByPage(@PathVariable("page")int page){
        List<Task> tasks = service.findAllByPage(page);
        return ResponseEntity.ok().body(tasks);
    }
    //////////////

    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable("id") Long id){
        Optional<Task> task = service.find(id);
        return ResponseEntity.of(task);
    }
//    @GetMapping(params = {"page, size"})
//    public List<Task> findPaginated(@RequestParam("page") int page,
//                                    @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
//                                    HttpServletResponse response){
//        Page<Task> resultPage = service.findPaginated(page, size)
//    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        Task created = service.create(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Task> update(
            @PathVariable("id") Long id,
            @RequestBody Task updatedTask){
        Optional<Task> updated = service.update(id, updatedTask);
        return ResponseEntity.of(updated);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Task> setAsDone(@PathVariable("id") Long id){
        Optional<Task> task = service.setAsDone(id);
        return ResponseEntity.of(task);
    }
    @PutMapping("/{id}/undone")
    public ResponseEntity<Task> setAsUndone(@PathVariable("id") Long id){
        Optional<Task> task = service.setAsUndone(id);
        return ResponseEntity.of(task);
    }

}
