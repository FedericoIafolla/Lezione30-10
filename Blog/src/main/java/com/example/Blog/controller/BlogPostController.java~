package com.example.Blog.controller;

import com.example.Blog.dto.BlogPostRequest;
import com.example.Blog.model.BlogPost;
import com.example.Blog.model.Author;
import com.example.Blog.repository.BlogPostRepository;
import com.example.Blog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return blogPostRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPostRequest blogPostRequest) {
        Author author = authorRepository.findById(blogPostRequest.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        BlogPost blogPost = new BlogPost();
        blogPost.setTitolo(blogPostRequest.getTitolo());
        blogPost.setContenuto(blogPostRequest.getContenuto());
        blogPost.setTempoDiLettura(blogPostRequest.getTempoDiLettura());
        blogPost.setAuthor(author);

        BlogPost savedPost = blogPostRepository.save(blogPost);
        return ResponseEntity.ok(savedPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPostRequest blogPostRequest) {
        return blogPostRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setTitolo(blogPostRequest.getTitolo());
                    existingPost.setContenuto(blogPostRequest.getContenuto());
                    existingPost.setTempoDiLettura(blogPostRequest.getTempoDiLettura());

                    Author author = authorRepository.findById(blogPostRequest.getAuthorId())
                            .orElseThrow(() -> new EntityNotFoundException("Author not found"));
                    existingPost.setAuthor(author);

                    return ResponseEntity.ok(blogPostRepository.save(existingPost));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(existingPost -> {
                    blogPostRepository.delete(existingPost);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
