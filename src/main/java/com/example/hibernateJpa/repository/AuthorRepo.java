package com.example.hibernateJpa.repository;


import com.example.hibernateJpa.entities.Author;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {

    List<Author> findByFirstName(String name);
    public Author deleteByFirstName(String name);

    @Query("Select a from Author a where a.age>=:age")
    List<Author> findByAge(int age);

    @Transactional()
    @Query("SELECT a FROM Author a WHERE a.lastName LIKE :prefix%")
    List<Author> findAuthorsByLastNameStartingWith(@Param("prefix") String prefix);
}
