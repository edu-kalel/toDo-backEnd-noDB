package com.encora.apprentice.todobackendnodb.repository;

import com.encora.apprentice.todobackendnodb.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface InMemoryTaskRepository extends CrudRepository<Task, Long> {

}
