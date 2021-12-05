package com.example.realestate.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    private User author;
    @Column(nullable = false)
    private LocalDateTime date;

    public News() {
    }

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


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
