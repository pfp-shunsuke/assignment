package com.assignment.repository;

import com.assignment.domain.Comedian;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 芸人API  Repository
 */
@Mapper
public interface ComedianRepository {
    /**
     * 芸人 取得（key ＝ id）
     *
     * @param id ID
     * @return 芸人情報
     */
    @Select("select id, name from comedians where id = #{id}")
    Comedian searchById(int id);

    /**
     * 芸人 全取得
     *
     * @return 芸人情報リスト
     */
    @Select("select * from comedians")
    List<Comedian> searchAll();

    /**
     * 芸人 登録
     *
     * @param name 芸人名
     */
    @Insert("insert into comedians (name) values (#{name})")
    void create(String name);

    /**
     * 芸人名 更新（key ＝ id）
     *
     * @param comedian 芸人情報
     */
    @Update("update comedians set name = #{name} where id = #{id}")
    boolean updateById(Comedian comedian);

    /**
     * 芸人 削除（key ＝ id）
     *
     * @param id ID
     */
    @Delete("delete from comedians where id = #{id}")
    void deleteById(int id);
}
