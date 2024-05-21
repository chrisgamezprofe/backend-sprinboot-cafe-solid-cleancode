package org.federaciondecafeteros.tareas.controller;

import org.federaciondecafeteros.tareas.model.Status;
import org.federaciondecafeteros.tareas.service.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
@CrossOrigin(origins = "http://localhost:5173")
public class StatusController {

    @Autowired
    private StatusServiceImpl statusServiceImp;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Status save(@RequestBody Status status){
        return statusServiceImp.save(status);
    }

    @GetMapping
    public List<Status> getAll(){
        return statusServiceImp.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(@PathVariable("id") long id){
        return statusServiceImp.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> update(@PathVariable("id") long id, @RequestBody Status status){
        return statusServiceImp.getById(id)
                .map(myTask ->{
                    myTask.setName(status.getName());
                    myTask.setDescription(status.getDescription());

                    myTask = statusServiceImp.update(myTask);
                    return new ResponseEntity<>(myTask,HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id){
        statusServiceImp.delete(id);
        return new ResponseEntity<String>("Tarea eliminada",HttpStatus.OK);
    }

}
