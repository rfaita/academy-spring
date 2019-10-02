package com.academy.java.spring.controller;

import com.academy.java.spring.model.Computer;
import com.academy.java.spring.service.ComputerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/computer")
public class ComputerController {

    private final ComputerService service;

    public ComputerController(ComputerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Computer findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Computer save(@RequestBody Computer computer) {


        return service.save(computer);
    }

    @PutMapping("/{id}")
    public Computer update(@PathVariable String id, @RequestBody Computer computer) {

        computer.setId(id);

        return service.save(computer);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }


}
