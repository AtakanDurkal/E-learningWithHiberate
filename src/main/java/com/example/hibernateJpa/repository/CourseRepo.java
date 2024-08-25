package com.example.hibernateJpa.repository;

import com.example.hibernateJpa.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
