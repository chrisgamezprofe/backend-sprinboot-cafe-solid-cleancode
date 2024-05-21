package org.federaciondecafeteros.tareas.service;

import org.federaciondecafeteros.tareas.exception.NotFoundException;
import org.federaciondecafeteros.tareas.interfaces.TaskService;
import org.federaciondecafeteros.tareas.model.Task;
import org.federaciondecafeteros.tareas.repository.TaskRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(task -> {
            Hibernate.initialize(task.getPriority()); // Cargar información de la prioridad asociada
            Hibernate.initialize(task.getStatus()); // Cargar información el estado asociado
        });
        return tasks;
    }

    @Override
    public Optional<Task> getById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task update(Task task) {
        Optional<Task> existsByName = taskRepository.findByName(task.getName());
        if(!existsByName.isPresent()){
            throw new NotFoundException("No existe una con nombre:"+task.getName());
        }
        return taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        taskRepository.deleteById(id);
    }
}
