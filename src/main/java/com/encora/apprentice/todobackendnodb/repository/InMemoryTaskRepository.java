package com.encora.apprentice.todobackendnodb.repository;

import com.encora.apprentice.todobackendnodb.model.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTaskRepository /*extends CrudRepository<Task, Long>*/ {
    private List<Task> list = new ArrayList<>();

    public List<Task> findAll() {
        return list;
    }

    public Task findById(Long id) {
        for (Task task : list) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public Task save(Task newTask) {
        list.add(newTask);
        return newTask;
    }

    public Task updateWith(Task newTask) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(newTask.getId())) {
                list.set(i, newTask);
                return newTask;
            }
        }
        list.add(newTask);
        return newTask;
    }
    public void saveAll(List<Task> tasks){
        list.addAll(tasks);
    }

    public Task setAsDone(Long id){
        //first approach
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getId().equals(id)){
                list.get(i).setDone(true);
                list.get(i).setDoneDate(LocalDateTime.now());
                return list.get(i);
            }
        }
        return null;
    }
}
