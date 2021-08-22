package com.blog.dao;

import com.blog.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);

    //按分页方式查询
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
