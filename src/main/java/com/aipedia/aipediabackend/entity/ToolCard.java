package com.aipedia.aipediabackend.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



  
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ToolCard {


    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    
   
    @Length(min = 5, message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
    private String title;

    
  

    @Temporal(TemporalType.TIMESTAMP)
    @Column( nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @NotNull
    private String description;

    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> hashTag= new HashSet<>();

    @OneToMany( cascade = CascadeType.REMOVE)
    private Set<User> likedUsers;



    
    @Column(unique = false, length = 10000000)
    private byte[] img;





    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> tag= new HashSet<>();



    @ManyToOne(cascade = CascadeType.MERGE )
    
    @NotNull
   @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "toolcard", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Collection<Comment> comments;




}
