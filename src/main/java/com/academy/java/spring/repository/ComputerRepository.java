package com.academy.java.spring.repository;

import com.academy.java.spring.model.Computer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends MongoRepository<Computer, String> {

    List<Computer> findAllByProcessorContaining(String processor);

}
