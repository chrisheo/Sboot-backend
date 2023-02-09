package com.devian.springboot.service.posts;

import com.devian.springboot.domain.posts.PostsRepository;
import com.devian.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.transaction.annotation.Transactional;

public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}