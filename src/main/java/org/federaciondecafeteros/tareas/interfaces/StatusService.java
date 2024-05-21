package org.federaciondecafeteros.tareas.interfaces;
import org.federaciondecafeteros.tareas.model.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    Status save(Status status);
    List<Status> getAll();

    Optional<Status> getById(long id);
    Status update(Status status);

    void delete(long id);
}
