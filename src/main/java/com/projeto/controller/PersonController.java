package com.projeto.controller;

import com.projeto.data.model.Person;
import com.projeto.data.vo.PersonVO;
import com.projeto.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping
    public List<PersonVO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person){
        return service.create(person);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person){
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
