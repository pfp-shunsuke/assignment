package com.assignment.controller;

import com.assignment.domain.Comedian;
import com.assignment.service.ComedianService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 芸人API Controller
 */
@RestController
@RequestMapping("/comedians")
@RequiredArgsConstructor
public class ComediansController {
    private final ComedianService comedianService;

    /**
     * 芸人名 更新（key ＝ id）
     * @param comedian 芸人情報
     * @return レスポンス（204）
     */
    @PutMapping
    ResponseEntity<Object> putById(@RequestBody Comedian comedian) {
        comedianService.updateById(comedian);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 芸人 削除（key ＝ id）
     * @param id ID
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable int id) {
        comedianService.deleteById(id);
    }

    /**
     * 芸人 取得（key ＝ id）
     * @param id ID
     * @return 芸人情報
     */
    @GetMapping("{id}")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Comedian getById(@PathVariable int id) {
        return comedianService.searchById(id);
    }

    /**
     * 芸人 全取得
     * @return 芸人情報リスト
     */
    @GetMapping
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<Comedian> getAll() {
        return comedianService.searchAll();
    }

    /**
     * 芸人 登録
     * @param names 芸人リスト
     * @apiNote streamを使ってみたかったので、パラメーターはリスト
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody List<String> names) {
        comedianService.create(names);
    }

}