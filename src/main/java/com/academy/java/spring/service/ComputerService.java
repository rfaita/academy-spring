package com.academy.java.spring.service;

import com.academy.java.spring.model.Computer;
import com.academy.java.spring.repository.ComputerRepository;
import org.springframework.stereotype.Service;

@Service
public class ComputerService {

    private final ComputerRepository repository;

    public ComputerService(ComputerRepository repository) {
        this.repository = repository;
    }

    public Computer findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Computer save(Computer computer) {

        return repository.save(computer);

    }

    public void delete(String id) {

        repository.deleteById(id);
    }

}
