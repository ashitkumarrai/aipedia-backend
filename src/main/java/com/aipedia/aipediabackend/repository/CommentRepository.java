package com.aipedia.aipediabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aipedia.aipediabackend.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
