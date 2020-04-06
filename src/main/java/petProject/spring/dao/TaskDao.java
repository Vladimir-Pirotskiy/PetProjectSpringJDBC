package petProject.spring.dao;

import petProject.spring.persistance.Task;

import java.util.List;

public interface TaskDao {

    List<Task> findAll();
    void create(Task task);
}
