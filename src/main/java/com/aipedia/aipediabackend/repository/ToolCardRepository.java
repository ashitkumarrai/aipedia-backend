package com.aipedia.aipediabackend.repository;


import com.aipedia.aipediabackend.entity.ToolCard;
import com.aipedia.aipediabackend.entity.User;

import antlr.Tool;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolCardRepository extends JpaRepository<ToolCard, String> {
    Page<ToolCard> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

    Page<ToolCard> findAllByOrderByCreateDateDesc(Pageable pageable);



    



 
}

