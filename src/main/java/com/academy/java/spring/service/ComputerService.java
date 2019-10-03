package com.academy.java.spring.service;

import com.academy.java.spring.model.Computer;
import com.academy.java.spring.repository.ComputerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<Computer> findAll(Integer page) {
        return repository.findAll(PageRequest.of(page, 3));
    }

    public Page<Computer> findAll(Integer page, String orderBy) {
        return repository.findAll(PageRequest.of(page, 3, Sort.by(Sort.Order.asc(orderBy))));
    }

    public List<Computer> findAllByProcessorContaining(String processor) {

        return repository.findAllByProcessorContaining(processor);

    }
}
