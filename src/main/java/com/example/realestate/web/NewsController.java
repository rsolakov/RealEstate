package com.example.realestate.web;

import com.example.realestate.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final ModelMapper modelMapper;

    public NewsController(NewsService newsService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/news/all")
    public String allNews(Model model) {
        model.addAttribute("news",
                newsService.getAllNews());
        return "news";
    }

    @GetMapping("/news/{id}/details")
    public String showNews(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("news", this.newsService.findById(id, principal.getName()));
        return "news-details";
    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/news/{id}")
    public String deleteNews(@PathVariable Long id,
                              Principal principal) {

        newsService.deleteNews(id);

        return "redirect:/news/all";
    }
}
