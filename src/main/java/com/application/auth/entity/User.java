package com.application.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@Data
public class User  {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate = ZonedDateTime.now();

    @Column(name = "lastmodified_date")
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

}
