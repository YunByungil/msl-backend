package com.maswilaeng.domain.repository;

import com.maswilaeng.domain.entity.Post;
import com.maswilaeng.domain.entity.User;
import com.maswilaeng.dto.post.response.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByUserId(Long userId);

    List<Post> findByTitleContaining(String keyword);

    Optional<Post> findById(Long Id);

    Page<Post> findByUserId(@Param("userId") Long userId, PageRequest createdAt);

    @Modifying
    @Query("UPDATE Post p SET p.likeCount = p.likeCount + 1 WHERE p.id = :postId")
    void addLikeCount(@Param("postId") Long postId);

    @Modifying
    @Query("UPDATE Post p SET p.likeCount = p.likeCount - 1 WHERE p.id = :postId")
    void subLikeCount(@Param("postId") Long postId);

    @Modifying
    @Query("UPDATE Post p SET p.hateCount = p.hateCount + 1 WHERE p.id = :postId")
    void addHateCount(@Param("postId") Long postId);

    @Modifying
    @Query("UPDATE Post p SET p.hateCount = p.hateCount - 1 WHERE p.id = :postId")
    void subHateCount(@Param("postId") Long postId);
}
