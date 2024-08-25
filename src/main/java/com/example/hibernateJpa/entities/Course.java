package com.example.hibernateJpa.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @Column
    private String title;

    private String description;

    @ManyToMany

    @JoinTable(name="authors_courses",
            joinColumns = {
            @JoinColumn(name="course_id")},
            inverseJoinColumns = {
            @JoinColumn(name="author_id")
            }
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}
