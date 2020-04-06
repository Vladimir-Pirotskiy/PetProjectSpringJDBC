package petProject.spring.controller;

import petProject.spring.persistance.Task;
import petProject.spring.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> findAll(){
        return  ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Task task){
        taskService.create(task);
        return ResponseEntity.ok().build();
    }

}
