package com.example.realestate.model.binding;

import com.example.realestate.model.entity.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class NewsAddBindingModel {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private User author;
    @NotNull
    private LocalDateTime date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
