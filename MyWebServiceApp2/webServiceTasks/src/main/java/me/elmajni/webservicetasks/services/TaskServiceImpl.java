package me.elmajni.webservicetasks.services;

import me.elmajni.webservicetasks.entities.Task;
import me.elmajni.webservicetasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> products = taskRepository.findAll();
        return products;
    }

    @Override
    public Task getTask(Long id) {
        Task task = taskRepository.findById(id).
                orElseThrow(()->new RuntimeException("Task not found"));
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}
