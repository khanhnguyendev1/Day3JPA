package com.ivt.spring.jpa.demo1.repository;

import com.ivt.spring.jpa.demo1.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
}
