package org.federaciondecafeteros.tareas.controller;

import org.federaciondecafeteros.tareas.model.Task;
import org.federaciondecafeteros.tareas.service.PriorityServiceImpl;
import org.federaciondecafeteros.tareas.service.StatusServiceImpl;
import org.federaciondecafeteros.tareas.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImp;
    @Autowired
    private PriorityServiceImpl priorityServiceImp;
    @Autowired
    private StatusServiceImpl statusServiceImp;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task save(@RequestBody Task task){
        // Antes de guardar la tarea, cargar la información completa de la prioridad y el estado asociados
        task.setPriority(priorityServiceImp.getById(task.getPriority().getId()).get());
        task.setStatus(statusServiceImp.getById(task.getStatus().getId()).get());
        return taskServiceImp.save(task);
    }

    @GetMapping
    public List<Task> getAll(){
        List<Task> tasks = taskServiceImp.findAll();
        tasks.forEach(task -> {
            // Cargar la información completa de la prioridad y el estado asociados
            task.setPriority(priorityServiceImp.getById(task.getPriority().getId()).get());
            task.setStatus(statusServiceImp.getById(task.getStatus().getId()).get());
        });
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable("id") long id){
        return taskServiceImp.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") long id, @RequestBody Task task){
        return taskServiceImp.getById(id)
                .map(myTask ->{
                    myTask.setName(task.getName());
                    myTask.setDescription(task.getDescription());
                    myTask.setStatus(task.getStatus());
                    myTask.setPriority(task.getPriority());
                    myTask.setBeginDate(task.getBeginDate());
                    myTask.setEndDate(task.getEndDate());

                    myTask = taskServiceImp.update(myTask);
                    return new ResponseEntity<>(myTask,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id){
        taskServiceImp.delete(id);
        return new ResponseEntity<String>("Tarea eliminada",HttpStatus.OK);
    }

}
