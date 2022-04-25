package me.elmajni.webservicetasks.repositories;

import me.elmajni.webservicetasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
