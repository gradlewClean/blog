package com.backbone.blog.controllers;

import com.backbone.blog.models.ArticleEntity;
import com.backbone.blog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private ArticleRepository repository;

    @GetMapping("/blog")
    public String blogPage(Model model) {
        Iterable<ArticleEntity> articles = repository.findAll();

        model.addAttribute("title", "Blog");
        model.addAttribute("articles", articles);
        return "blog";
    }

    @GetMapping("/blog/new")
    public String blogNewPage(Model model) {
        model.addAttribute("title", "New Article");
        return "blog-new";
    }

    @PostMapping("/blog/new")
    public String createNewArticle(
            @RequestParam String title,
            @RequestParam String annonce,
            @RequestParam String full_text,
            Model model)
    {

        ArticleEntity article = new ArticleEntity(title, annonce, full_text);
        repository.save(article);

        return "redirect:/blog";
    }
}
