package com.assignment.service;

import com.assignment.domain.Comedian;
import com.assignment.repository.ComedianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 芸人API  Service
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ComedianService {

    final ComedianRepository comedianRepository;

    /**
     * 芸人 取得（key ＝ id）
     *
     * @param id ID
     * @return 芸人情報
     */
    public Comedian searchById(int id) {
        return comedianRepository.searchById(id);
    }

    /**
     * 芸人 全取得
     *
     * @return 芸人情報リスト
     */
    public List<Comedian> searchAll() {
        return comedianRepository.searchAll();
    }

    /**
     *
     * 芸人 登録
     * @param names 芸人リスト
     */
    public void create(List<String> names) {
        // streamを使いたい
        names.stream().filter(Objects::nonNull)
                .forEach(comedianRepository::create);
    }

    /**
     * 芸人名 更新（key ＝ id）
     *
     * @param comedian 芸人情報
     */
    public void updateById(Comedian comedian) {
        comedianRepository.updateById(comedian);
    }

    /**
     * 芸人 削除（key ＝ id）
     *
     * @param id ID
     */
    public void deleteById(int id) {
        comedianRepository.deleteById(id);
    }

}
