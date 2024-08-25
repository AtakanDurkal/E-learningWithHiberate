package com.example.hibernateJpa.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Author.findByLastNameStartingWithKoc",
        query = "SELECT a FROM Author a WHERE a.lastName LIKE 'koc%'"
)
public class Author  extends BaseEntity{


    @Column()
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    private int age;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;


}
