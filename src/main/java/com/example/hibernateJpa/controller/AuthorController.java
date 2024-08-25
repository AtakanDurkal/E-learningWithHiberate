package com.example.hibernateJpa.controller;

import com.example.hibernateJpa.entities.Author;
import com.example.hibernateJpa.models.AuthorDTO;
import com.example.hibernateJpa.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e-learning")
public class AuthorController {

    private  final AuthorService authorService ;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/createAuthor")
    @ResponseBody
    public AuthorDTO createAuthor (@RequestBody AuthorDTO authorDTO){
        return  this.authorService.createAuthor(authorDTO);
    }

    @GetMapping("/getAuthorById")
    @ResponseBody
    public ResponseEntity<?> getAuthorById(@RequestParam int id){
        Optional<AuthorDTO> authorDTO= this.authorService.getAuthorById(id);
        if(authorDTO.isPresent()){
            return ResponseEntity.ok(authorDTO.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with ID " + id + " not found!");
        }

    }

    @GetMapping("getAllAuthors")
    @ResponseBody
    public ResponseEntity<?> getAllAuthors(){
        Optional<List<AuthorDTO>> authors = this.authorService.getAllAuthors();

        if (authors.isPresent()) {
            return ResponseEntity.ok(authors.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/getAuthorByName")
    public ResponseEntity<?> getAuthorByName(@RequestParam String name){
        Optional<List<AuthorDTO>> authorDTO = this.authorService.getAuthorByName(name);
        if(authorDTO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No author found with name '" + name + "'.");
        }else if(authorDTO.get().size()== 1) {
            return ResponseEntity.ok(authorDTO.get());
        }else{
            return ResponseEntity.ok("There are " +  authorDTO.get().size()+ " authors with the name '"+ name + "'.");
        }
    }
    @PutMapping("updateAuthor")
    @ResponseBody
    public ResponseEntity<?> updateAuthor(@RequestParam int id ,@RequestBody AuthorDTO authorDTO){
        Optional<AuthorDTO> updatedAuthor = authorService.updateAuthor(id, authorDTO);

        if (updatedAuthor.isPresent()) {
            return ResponseEntity.ok(updatedAuthor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Author with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/deleteAuthorById/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable int id){
        boolean isDeleted = this.authorService.deleteAuthorById(id);
        if(isDeleted) {
            return ResponseEntity.ok("Author with ID " + id + " deleted!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with ID " + id + " not found!");
        }

    }


}
