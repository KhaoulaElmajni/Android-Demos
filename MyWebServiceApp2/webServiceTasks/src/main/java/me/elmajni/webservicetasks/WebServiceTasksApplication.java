package me.elmajni.webservicetasks;

import me.elmajni.webservicetasks.entities.Status;
import me.elmajni.webservicetasks.entities.Task;
import me.elmajni.webservicetasks.repositories.TaskRepository;
import me.elmajni.webservicetasks.services.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class WebServiceTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceTasksApplication.class, args);
    }

    @Bean
    CommandLineRunner start(TaskRepository taskRepository){
        return args -> {
            Stream.of("Code","Develop","Refactor","Eat","Sleep","Revise","Drink coffe").forEach(name->{
                taskRepository.save(new Task(null,name, Status.WAITING,"Urgent Task to do before "+new Date()));
            });
        };
    }

}
