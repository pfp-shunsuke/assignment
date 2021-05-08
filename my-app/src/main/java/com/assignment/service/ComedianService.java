package com.assignment.service;

import com.assignment.entity.Comedian;
import com.assignment.repository.ComedianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ComedianService {

    @Autowired
    ComedianRepository comedianRepository;

    public Comedian searchById(int id) {
        return comedianRepository.searchById(id).orElse(new Comedian());
    }

    public List<Comedian> searchAll() {
        return comedianRepository.searchAll();
    }

    public void create(List<String> names) {
        // streamを使いたい
        names.stream().filter(Objects::nonNull)
                .forEach(comedianRepository::create);
    }

    boolean updateById(Comedian comedian) {
        return comedianRepository.updateById(comedian);
    }

    public void deleteById(int id) {
        comedianRepository.deleteById(id);
    }

}
