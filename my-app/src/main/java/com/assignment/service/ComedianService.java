package com.assignment.service;

import com.assignment.entity.Comedian;
import com.assignment.repository.ComedianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComedianService {

    @Autowired
    ComedianRepository comedianRepository;

    public Comedian searchById(int id) {
        return comedianRepository.searchById(id).orElse(new Comedian());
    }

    public List<Comedian> searchAll() {
        return comedianRepository.searchAll();
    }

    void create(Comedian comedian) {
        comedianRepository.create(comedian);
    }

    boolean updateById(Comedian comedian) {
        return comedianRepository.updateById(comedian);
    }

    public void deleteById(int id) {
        comedianRepository.deleteById(id);
    }
}
