package com.example.hibernateJpa.mappers;

import com.example.hibernateJpa.entities.Author;
import com.example.hibernateJpa.entities.Course;
import com.example.hibernateJpa.models.AuthorDTO;
import com.example.hibernateJpa.models.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "courses", source = "courses")
    Author toEntity(AuthorDTO authorDTO);

    @Mapping(target = "courses", source = "courses")
    AuthorDTO toDTO(Author author);

    // Convert a list of Author entities to a list of AuthorDTOs
    default List<AuthorDTO> toAuthorDTOList(List<Author> authors) {
        return authors.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<Course> mapCourses(List<CourseDTO> courses) {
        return courses.stream().map(courseDTO -> Course.builder()
                .id(courseDTO.id())
                .title(courseDTO.title())
                .description(courseDTO.description())
                .build()).collect(Collectors.toList());
    }

    default List<CourseDTO> mapCoursesDTO(List<Course> courses) {
        return   courses.stream().map(course -> new CourseDTO(course.getId(), course.getTitle(),course.getDescription())).collect(Collectors.toList());
    }
}
