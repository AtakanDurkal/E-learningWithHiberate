package com.example.hibernateJpa.models;

import com.example.hibernateJpa.entities.Course;

import java.util.List;

public record AuthorDTO(String firstName, String lastName, String email,int age, List<CourseDTO> courses) {
}
