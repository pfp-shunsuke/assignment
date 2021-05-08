package com.assignment.controller;

import com.assignment.entity.Comedian;
import com.assignment.service.ComedianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comedians")
public class ComediansController {

    @Autowired
    ComedianService comedianService;

    @PutMapping
    ResponseEntity<Object> putById(@RequestBody Comedian comedian) {
        HttpStatus status = comedianService.updateById(comedian) ?
                HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR;
        System.out.println(status);
        return ResponseEntity.status(status).body(null);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable int id) {
        comedianService.deleteById(id);
    }

    @GetMapping("/talents")
    List<Comedian> getAll() {
        return comedianService.searchAll();
    }

    @GetMapping("/talents/{id}")
    Comedian getById(@PathVariable int id) {
        return comedianService.searchById(id);
    }

    /**
     * @param names 芸人リスト
     * @apiNote streamを使ってみたかったので、パラメーターはリスト
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody List<String> names) {
        comedianService.create(names);
    }

}