package com.example.realestate.service.impl;

import com.example.realestate.model.entity.*;
import com.example.realestate.model.entity.enums.UserRoleEnum;
import com.example.realestate.model.view.NewsView;
import com.example.realestate.repository.NewsRepository;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }




    @Override
    public void initializeNews() {
        if (newsRepository.count() == 0) {
            News news = new News();
            news.setAuthor(userRepository.findByUsername("rsolakov")
                    .orElse(null));
            news.setDescription("Not big description");
            news.setDate(LocalDateTime.now());
            news.setTitle("First new");

            News news1 = new News();
            news1.setAuthor(userRepository.findByUsername("rsolakov")
                    .orElse(null));
            news1.setDescription("Again not big description");
            news1.setDate(LocalDateTime.now());
            news1.setTitle("Second new");

            newsRepository.saveAll(List.of(news, news1));
        }

    }

    @Override
    public List<NewsView> getAllNews() {
        return newsRepository.
                findAll().
                stream().
                map(this::mapNews).
                collect(Collectors.toList());
    }



    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public NewsView findById(Long id, String currentUser) {
        NewsView newsView = this.newsRepository.
                findById(id).
                map(o -> mapNewsView(currentUser, o))
                .get();
        return newsView;
    }

    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<News> offerOpt = newsRepository.
                findById(id);
        Optional<User> caller = userRepository.
                findByUsername(userName);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            News news = offerOpt.get();

            return isAdmin(caller.get()) ||
                    news.getAuthor().getUsername().equals(userName);
        }
    }

    private boolean isAdmin(User user) {
        return user.
                getRoles().
                stream().
                map(UserRole::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    private NewsView mapNewsView(String currentUser, News news) {
        NewsView newsView = this.modelMapper.map(news, NewsView.class);
        newsView.setAuthor(news.getAuthor().getUsername());
        newsView.setDate(news.getDate());
        newsView.setDescription(news.getDescription());
        newsView.setTitle(news.getTitle());
        newsView.setCanDelete(isOwner(currentUser, news.getId()));
        return newsView;
    }

    private NewsView mapNews (News news) {
        NewsView view = this.modelMapper
                .map(news, NewsView.class);

        view.setTitle(news.getTitle());
        view.setDescription(news.getDescription());
        view.setAuthor(news.getAuthor().getUsername());
        view.setDate(news.getDate());

        return view;
    }
}
