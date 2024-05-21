package org.federaciondecafeteros.tareas.interfaces;

import org.federaciondecafeteros.tareas.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityService {
    Priority save(Priority priority);
    List<Priority> getAll();

    Optional<Priority> getById(long id);
    Priority update(Priority priority);

    void delete(long id);
}
