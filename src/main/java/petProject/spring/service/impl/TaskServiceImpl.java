package petProject.spring.service.impl;

import petProject.spring.dao.TaskDao;
import petProject.spring.persistance.Task;
import petProject.spring.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;
    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public void create(Task task) {
    taskDao.create(task);
    }
}
