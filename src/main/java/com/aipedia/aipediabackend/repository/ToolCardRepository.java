package com.aipedia.aipediabackend.repository;


import com.aipedia.aipediabackend.entity.ToolCard;
import com.aipedia.aipediabackend.entity.User;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ToolCardRepository extends JpaRepository<ToolCard, String> {
    Page<ToolCard> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

    Page<ToolCard> findAllByOrderByCreateDateDesc(Pageable pageable);



 
}

