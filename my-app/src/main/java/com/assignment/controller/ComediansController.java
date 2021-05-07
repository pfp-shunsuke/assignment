package com.assignment.controller;

import com.assignment.entity.Comedian;
import com.assignment.service.ComedianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comedians")
public class ComediansController {

    @Autowired
    ComedianService comedianService;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void sampleDelete(@PathVariable Integer id) {

    }

    @GetMapping("/talents")
    List<Comedian> getAll() {
        return comedianService.searchAll();
    }

    @GetMapping("/talents/{id}")
    Comedian getById(@PathVariable String id) {
        return comedianService.searchById(id);
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}