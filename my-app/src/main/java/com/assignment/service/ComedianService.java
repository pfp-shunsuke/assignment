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

    public Comedian searchById(String id) {
        return comedianRepository.searchById(Integer.parseInt(id)).orElse(new Comedian());
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

    void deleteById(Comedian comedian) {
        comedianRepository.deleteById(comedian);
    }
}
