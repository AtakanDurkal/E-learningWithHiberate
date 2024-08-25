package com.example.hibernateJpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Lecture extends BaseEntity{

    @Column
    private String name;


    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

     @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="resource_id")
    private Resource resource;

}
