package com.assignment.repository;

import com.assignment.entity.Comedian;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ComedianRepository {
    @Select("select id, name from comedians where id = #{id}")
    Comedian searchById(int id);

    @Select("select * from comedians")
    List<Comedian> searchAll();

    @Insert("insert into comedians (name) values (#{name})")
    void create(String name);

    @Update("update comedians set name = #{name} where id = #{id}")
    boolean updateById(Comedian comedian);

    @Delete("delete from comedians where id = #{id}")
    void deleteById(int id);
}
