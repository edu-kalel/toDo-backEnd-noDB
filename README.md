# toDo-backEnd-noDB
* mvn spring-boot:run -> To run the back-end application
* It runs on port 9090
## Endpoints
http://localhost:9090/todos
* GET 
  * Returns list of all the tasks stored. The project already contains 3 tasks to display/ modify
* GET /{id}
  * Returns a single task, corresponding to the specified id
* POST
  * Add a new Task
* PUT /{id}/update
  * Updates the specified task (id will always stay the same, if task does not exist it will be created)
* PUT /{id}/done
  * Marks task with the id specified as done, and sets the done date
* PUT /{id}/undone
  * Marks task with the id specified as undone
* GET /page/{page}
  * Returns a list of 10 tasks, depending on the number of page