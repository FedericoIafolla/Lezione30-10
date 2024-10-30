package com.example.Blog.controller;

import com.example.Blog.model.BlogPost;
import com.example.Blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        return blogPostRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setCategoria(blogPost.getCategoria());
                    existingPost.setTitolo(blogPost.getTitolo());
                    existingPost.setCover(blogPost.getCover());
                    existingPost.setContenuto(blogPost.getContenuto());
                    existingPost.setTempoDiLettura(blogPost.getTempoDiLettura());
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
