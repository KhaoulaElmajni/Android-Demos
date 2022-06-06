package me.elmajni.webservicetasks.services;

import me.elmajni.webservicetasks.entities.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    List<Task> getAllTasks();
    Task getTask(Long id);
    Task updateTask(Task task);
    void deleteTask(Long id);
    void deleteAllTasks();
}
