package com.demo.appname.api.domain.demo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.demo.appname.api.controller.v1.demo.model.DemoVo;
import com.demo.appname.api.domain.demo.model.Demo;

@Mapper
@Repository
public interface DemoRepository {

    List<Demo> getUserList();

    @Insert("INSERT INTO tbl_demo(name) VALUES (#{name})")
    void insertUserByAnnotation(@Param("name") String name);

    @Select("SELECT * FROM tbl_demo WHERE id = #{id}")
    DemoVo findById(@Param("id") Long id);

    @Select("SELECT * FROM tbl_demo WHERE name in (#{name})")
    List<Demo> findByUserNames(@Param("name") String[] name);

    List<Demo> findByUserNamesWithQuery(String[] usernames);

    List<Demo> findByUserNamesListWithQuery(@Param("array") List<String> usernames);

    @Delete("DELETE FROM tbl_demo WHERE id = #{id}")
    void deleteById(@Param("id") Long id);

    @Update("UPDATE tbl_demo SET name = #{name} WHERE id = #{id}")
    void updateUserNameById(@Param("name") String name, @Param("id") Long id);

    void insertUsers(List<Demo> users);
}
