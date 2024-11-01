package com.example.Blog.controller;

import com.example.Blog.model.Author;
import com.example.Blog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setNome(author.getNome());
                    existingAuthor.setCognome(author.getCognome());
                    existingAuthor.setEmail(author.getEmail());
                    existingAuthor.setDataDiNascita(author.getDataDiNascita());
                    existingAuthor.setAvatar(author.getAvatar());
                    return ResponseEntity.ok(authorRepository.save(existingAuthor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    authorRepository.delete(existingAuthor);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
