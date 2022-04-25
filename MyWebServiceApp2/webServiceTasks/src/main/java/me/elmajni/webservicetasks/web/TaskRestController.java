package me.elmajni.webservicetasks.web;


import me.elmajni.webservicetasks.entities.Task;
import me.elmajni.webservicetasks.services.TaskService;
import me.elmajni.webservicetasks.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskRestController {
    @Autowired
    private TaskServiceImpl taskService;

    /**
     * Restful API 5 regles
     * 1 les URI
     * 2 les methodes http comme identifiant des operations (GET/PUT/POST/PUT/DELETE/PATCH)
     * PATCH ==> modifier que les shamps mentionnés
     * */
    @GetMapping("/tasks")
    public List<Task> tasksList(){
        return taskService.getAllTasks();
    }

    /**
     *
     * */
    @GetMapping("/tasks/{id}")
    public  Task getTask(@PathVariable(name="id") Long id){
        return taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public  Task saveTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @PutMapping("/tasks/{id}")
    public  Task updateTask(@RequestBody Task task,@PathVariable Long id){
        task.setId(id);
        return taskService.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @DeleteMapping("/tasks")
    public void deleteAllTasks(){
        taskService.deleteAllTasks();
    }

}
