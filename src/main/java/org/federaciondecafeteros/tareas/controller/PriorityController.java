package org.federaciondecafeteros.tareas.controller;

import org.federaciondecafeteros.tareas.model.Priority;
import org.federaciondecafeteros.tareas.service.PriorityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/priorities")
@CrossOrigin(origins = "http://localhost:5173")
public class PriorityController {

    @Autowired
    private PriorityServiceImpl priorityServiceImp;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Priority save(@RequestBody Priority priority){
        return priorityServiceImp.save(priority);
    }

    @GetMapping
    public List<Priority> getAll(){
        return priorityServiceImp.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> getById(@PathVariable("id") long id){
        return priorityServiceImp.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Priority> update(@PathVariable("id") long id, @RequestBody Priority priority){
        return priorityServiceImp.getById(id)
                .map(myTask ->{
                    myTask.setName(priority.getName());
                    myTask.setDescription(priority.getDescription());

                    myTask = priorityServiceImp.update(myTask);
                    return new ResponseEntity<>(myTask,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id){
        priorityServiceImp.delete(id);
        return new ResponseEntity<String>("Tarea eliminada",HttpStatus.OK);
    }

}
