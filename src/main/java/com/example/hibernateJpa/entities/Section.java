package com.example.hibernateJpa.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Section extends BaseEntity {



    private String name;

    private int sectionOrder;
  @ManyToOne
  @JoinColumn(name = "course_id")
    private Course course;


  @OneToMany(mappedBy = "section")
    private List<Lecture> lectures;


}
