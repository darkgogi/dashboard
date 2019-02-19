package com.example.hello.controller;

import com.example.hello.model.Orgn;
import com.example.hello.repository.OrgnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path="/orgns")
public class OrgnController {

    @Autowired
    private OrgnRepository repository;

    @GetMapping(path="/all")
    public Iterable<Orgn> getAll() {
        return repository.findAll();
    }

    @GetMapping(path="/{id}")
    public Orgn getItem(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @PostMapping(path="")
    public String addItem(@RequestParam String id, @RequestParam String name, @RequestParam String dsc) {
        Orgn item = new Orgn();
        item.setOrgnId(id);
        item.setOrgnNm(name);
        item.setOrgnDsc(dsc);

        repository.save(item);
        return "OK";
    }

    @Transactional
    @DeleteMapping(path="/{id}")
    public String deleteItem(@PathVariable Integer id) {
        if ( repository.existsById(id) ) {
            repository.deleteById(id);
            return "OK";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found");
        }
    }
}
