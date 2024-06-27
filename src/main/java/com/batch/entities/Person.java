package com.batch.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Persons")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter
    private Long id;
    @Column(name = "name")
    @Getter @Setter
    private String name;
    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;
    @Column(name = "age")
    @Getter @Setter
    private Integer age;
    @Column(name = "created_at")
    @Getter @Setter
    private String createdAt;
}
