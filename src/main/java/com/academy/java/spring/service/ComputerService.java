package com.academy.java.spring.service;

import com.academy.java.spring.model.Computer;
import com.academy.java.spring.repository.ComputerRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComputerService {

    private final ComputerRepository repository;
    //private final Validator validator;

    public ComputerService(ComputerRepository repository) {
        this.repository = repository;
    }

    public Computer findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Computer save(Computer computer) {


//        Set<ConstraintViolation<Computer>> violations = validator.validate(computer);
//        if (!violations.isEmpty()) {
//            throw new ConstraintViolationException(new HashSet<>(violations));
//        }

        return repository.save(computer);
    }

    public void delete(String id) {

        repository.deleteById(id);
    }

    public List<Computer> findAll() {
        return repository.findAll();
    }

    public List<Computer> findAllByProcessorContaining(String processor) {

        return repository.findAllByProcessorContaining(processor);

    }
}
