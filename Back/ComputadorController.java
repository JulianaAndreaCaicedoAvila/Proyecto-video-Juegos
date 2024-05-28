package com.DesarrolloWEB_crud.demopostgres.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DesarrolloWEB_crud.demopostgres.entity.Computador;
import com.DesarrolloWEB_crud.demopostgres.services.ComputadorService;

@RestController
@RequestMapping("/api/v1/computadores")
public class ComputadorController {

    @Autowired
    private ComputadorService computadorService;

    @GetMapping
    public ResponseEntity<Object> getAllComputadores() {
        try {
            List<Computador> computadores = computadorService.findAll();
            return new ResponseEntity<>(computadores, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/computadores/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Computador computador = computadorService.findById(id);
            return new ResponseEntity<>(computador, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<String, Object>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/computadores")
    public ResponseEntity<Object> createComputador(@RequestBody Computador computador) {
        try {
            Computador createdComputador = computadorService.save(computador);
            return new ResponseEntity<>(createdComputador, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value="/computadores/{id}")
    public ResponseEntity<Object> updateComputador(@PathVariable Long id, @RequestBody Computador computador) {
        Map<String, Object> map = new HashMap<>();

        try {
            Computador currentComputador = computadorService.findById(id);
            currentComputador.setEstado(computador.getEstado());
            // Setear otros atributos según lo necesites

            Computador updatedComputador = computadorService.save(currentComputador);
            return new ResponseEntity<>(updatedComputador, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value="/computadores/{id}")
    public ResponseEntity<Object> deleteComputador(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();

        try {
            Computador currentComputador = computadorService.findById(id);
            computadorService.delete(currentComputador);
            map.put("deleted", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
