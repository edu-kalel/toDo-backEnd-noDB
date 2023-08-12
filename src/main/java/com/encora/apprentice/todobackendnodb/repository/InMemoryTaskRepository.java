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
    public List<Task> findAllByPage(int page, int tasksPerPage){
//        int tasksPerPage = 10;
        int startingIndex = (page*tasksPerPage)-tasksPerPage;
        int pages = list.size()/tasksPerPage;
        if (list.size()%tasksPerPage!=0)
            pages++;
        int endIndex;
        if (pages==page) {
            endIndex = list.size();
        } else {
            endIndex = startingIndex=10;
        }
        List<Task> sublist = list.subList(startingIndex, endIndex);
        return sublist;
    }
    public int pages(int tasksPerPage){
//        int tasksPerPage = 10;
        int pages = list.size()/tasksPerPage;
        if (list.size()%tasksPerPage!=0)
            pages++;
        return pages;
    }
    public Long nextId(){
        return (long) (list.size()+1);
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
        newTask.setCreationDate(LocalDateTime.now());
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
    public Task setAsUndone(Long id){
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getId().equals(id)){
                list.get(i).setDone(false);
                list.get(i).setDoneDate(null);
                return list.get(i);
            }
        }
        return null;
    }
}
