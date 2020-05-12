package com.example.mymybatis.mapper;

import com.example.mymybatis.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Baozi
 */
@Repository
public interface UserMapper {
    User sel(int id);

    User findOne(int id);


    @Select("select * from user")
    List<User> selectAll();
}

