package com.DesarrolloWEB_crud.demopostgres.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DesarrolloWEB_crud.demopostgres.entity.Person;
import com.DesarrolloWEB_crud.demopostgres.services.PersonService;

@RestController
@RequestMapping("/api/v1/Persons")
@CrossOrigin(origins = "http://localhost:8080")
public class PersonController {

    @Autowired
    private PersonService personSevice;

    @GetMapping(value="/Persons")
    public ResponseEntity<Object> get(){
        Map<String, Object> map= new HashMap<>();
        try {
            List<Person> list = personSevice.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/Persons/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Person data = personSevice.findById(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/Persons")
    public ResponseEntity<Object> create(@RequestBody Person person) {
        Map<String, Object> map = new HashMap<>();
        try {
            Person res = personSevice.save(person);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/Persons/{id}")
    public ResponseEntity<Object> update(@RequestBody Person person, @PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Person currentPerson = personSevice.findById(id);
            currentPerson.setNombre(person.getNombre());
            currentPerson.setId(person.getId());
            currentPerson.setDepartamento(person.getDepartamento());
            Person res = personSevice.save(currentPerson);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/Persons/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Person currentPerson = personSevice.findById(id);
            personSevice.delete(currentPerson);
            map.put("deleted", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
