package com.example.hibernateJpa.service;

import com.example.hibernateJpa.entities.Author;
import com.example.hibernateJpa.entities.Course;
import com.example.hibernateJpa.mappers.AuthorMapper;
import com.example.hibernateJpa.models.AuthorDTO;
import com.example.hibernateJpa.repository.AuthorRepo;
import com.example.hibernateJpa.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepo authorRepo;
    private final CourseRepo courseRepo;


    public AuthorService(AuthorMapper authorMapper, AuthorRepo authorRepo, CourseRepo courseRepo) {
        this.authorMapper = authorMapper;
        this.authorRepo = authorRepo;
        this.courseRepo = courseRepo;
    }

    // Create a new author with associated courses
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);

        // Fetch and set courses by their IDs, create if not exists
        if (authorDTO.courses() != null) {
            List<Course> courses = authorDTO.courses().stream()
                    .map(courseDTO -> {
                        return courseRepo.findById(courseDTO.id()).orElseGet(() -> {
                            Course newCourse = new Course();
                            newCourse.setId(courseDTO.id());
                            newCourse.setTitle(courseDTO.title());
                            return courseRepo.save(newCourse);
                        });
                    })
                    .collect(Collectors.toList());
            author.setCourses(courses);
        }

        Author savedAuthor = authorRepo.save(author);
        return authorMapper.toDTO(savedAuthor);
    }

    // Get an author by ID
    public Optional<AuthorDTO> getAuthorById(int id) {
        Optional<Author> author = authorRepo.findById(id);
        return author.map(authorMapper::toDTO);
    }

    // Get authors by first name
    public Optional<List<AuthorDTO>> getAuthorByName(String name) {
        List<Author> authors = authorRepo.findByFirstName(name);
        if (authors.isEmpty()) {
            return Optional.empty();
        }
        List<AuthorDTO> authorDTOs = authorMapper.toAuthorDTOList(authors);
        return Optional.of(authorDTOs);
    }

    // Get all authors
    public Optional<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorRepo.findAll();
        if (authors.isEmpty()) {
            return Optional.empty();
        }
        List<AuthorDTO> authorDTOs = authorMapper.toAuthorDTOList(authors);
        return Optional.of(authorDTOs);
    }

    // Update an existing author
    public Optional<AuthorDTO> updateAuthor(int id, AuthorDTO authorDTO) {
        Optional<Author> optionalAuthor = authorRepo.findById(id);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setFirstName(authorDTO.firstName());
            author.setLastName(authorDTO.lastName());
            author.setEmail(authorDTO.email());
            author.setAge(authorDTO.age());

            // Update the courses
            if (authorDTO.courses() != null) {
                List<Course> courses = authorDTO.courses().stream()
                        .map(courseDTO -> courseRepo.findById(courseDTO.id())
                                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + courseDTO.id())))
                        .collect(Collectors.toList());
                author.setCourses(courses);
            }

            authorRepo.save(author);
            return Optional.of(authorMapper.toDTO(author));
        } else {
            return Optional.empty();
        }
    }

    // Delete an author by ID
    public boolean deleteAuthorById(int id) {
        if (authorRepo.existsById(id)) {
            authorRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Author> getAuthorsWithLastNameStartingWithKoc() {
        return this.authorRepo.findAuthorsByLastNameStartingWith("koc");
    }
}
