package org.federaciondecafeteros.tareas.interfaces;

import org.federaciondecafeteros.tareas.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task save(Task task);
    List<Task> findAll();

    Optional<Task> getById(long id);
    Task update(Task task);

    void delete(long id);
}
