package org.federaciondecafeteros.tareas.service;

import org.federaciondecafeteros.tareas.exception.NotFoundException;
import org.federaciondecafeteros.tareas.interfaces.StatusService;
import org.federaciondecafeteros.tareas.model.Status;
import org.federaciondecafeteros.tareas.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public Status save(Status status) {
        Optional<Status> existsByName = statusRepository.findByName(status.getName());
        if(existsByName.isPresent()){
            throw new NotFoundException("Ya existe el nombre:"+status.getName());
        }
        return statusRepository.save(status);
    }

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> getById(long id) {
        return statusRepository.findById(id);
    }

    @Override
    public Status update(Status status) {
        Optional<Status> existsByName = statusRepository.findByName(status.getName());
        if(!existsByName.isPresent()){
            throw new NotFoundException("No existe una con nombre:"+status.getName());
        }
        return statusRepository.save(status);
    }

    @Override
    public void delete(long id) {
        statusRepository.deleteById(id);
    }
}
