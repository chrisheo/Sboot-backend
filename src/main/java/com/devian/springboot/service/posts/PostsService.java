package com.devian.springboot.service.posts;

import com.devian.springboot.domain.posts.Posts;
import com.devian.springboot.domain.posts.PostsRepository;
import com.devian.springboot.web.dto.PostsResponseDto;
import com.devian.springboot.web.dto.PostsSaveRequestDto;
import com.devian.springboot.web.dto.PostsUpdateRequestDto;
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

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
}