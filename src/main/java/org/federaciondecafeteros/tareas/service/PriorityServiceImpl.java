package org.federaciondecafeteros.tareas.service;

import org.federaciondecafeteros.tareas.exception.NotFoundException;
import org.federaciondecafeteros.tareas.interfaces.PriorityService;
import org.federaciondecafeteros.tareas.model.Priority;
import org.federaciondecafeteros.tareas.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityServiceImpl implements PriorityService {
    @Autowired
    private PriorityRepository priorityRepository;
    @Override
    public Priority save(Priority priority) {
        Optional<Priority> existsByName = priorityRepository.findByName(priority.getName());
        if(existsByName.isPresent()){
            throw new NotFoundException("Ya existe el nombre:"+priority.getName());
        }
        return priorityRepository.save(priority);
    }

    @Override
    public List<Priority> getAll() {
        return priorityRepository.findAll();
    }

    @Override
    public Optional<Priority> getById(long id) {
        return priorityRepository.findById(id);
    }

    @Override
    public Priority update(Priority priority) {
        Optional<Priority> existsByName = priorityRepository.findByName(priority.getName());
        if(!existsByName.isPresent()){
            throw new NotFoundException("No existe una con nombre:"+priority.getName());
        }
        return priorityRepository.save(priority);
    }

    @Override
    public void delete(long id) {
        priorityRepository.deleteById(id);
    }
}
