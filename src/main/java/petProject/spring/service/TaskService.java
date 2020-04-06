package petProject.spring.service;

import petProject.spring.persistance.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
     void create(Task task);
}
