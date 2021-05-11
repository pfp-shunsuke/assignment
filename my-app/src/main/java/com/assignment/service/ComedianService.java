package com.assignment.service;

import com.assignment.entity.Comedian;
import com.assignment.entity.MyException;
import com.assignment.repository.ComedianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ComedianService {

    final ComedianRepository comedianRepository;

    public Comedian searchById(int id) {
        return comedianRepository.searchById(id);
    }

    public List<Comedian> searchAll() {
        return comedianRepository.searchAll();
    }

    public void create(List<String> names) {
        // streamを使いたい
        names.stream().filter(Objects::nonNull)
                .forEach(comedianRepository::create);
    }

    public void updateById(Comedian comedian) {
        boolean ret = comedianRepository.updateById(comedian);

        if (!ret) {
            throw new MyException("更新は行われませんでした");
        }

    }

    public void deleteById(int id) {
        comedianRepository.deleteById(id);
    }

}
