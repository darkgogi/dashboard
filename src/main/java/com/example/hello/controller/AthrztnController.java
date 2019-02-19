package com.example.hello.controller;

import com.example.hello.model.Athrztn;
import com.example.hello.model.Orgn;
import com.example.hello.repository.AthrztnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/athrztns")
public class AthrztnController {

    @Autowired
    private AthrztnRepository repository;

    @GetMapping(path="/all")
    public Iterable<Athrztn> getAll() {
        return repository.findAll();
    }

    @GetMapping(path="/{id}")
    public Athrztn getItem(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @PostMapping(path="")
    public String addItem(@RequestParam String name, @RequestParam String dsc) {
        Athrztn item = new Athrztn();
        item.setAthrztnNm(name);
        item.setAthrztnDsc(dsc);
        item.setFirstRgstTm(LocalDateTime.now());

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
