package com.aipedia.aipediabackend.entity;


import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Comment {
  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


@Column( columnDefinition = "TEXT")
@NotBlank(message = "*Please write something")
private String body;

@Temporal(TemporalType.TIMESTAMP)
@Column(nullable = false, updatable = false)
@CreationTimestamp
private Date createDate;

@NotNull
@ManyToOne(cascade = CascadeType.MERGE )
private ToolCard toolcard;

@ManyToOne
@NotNull
private User user;

}
