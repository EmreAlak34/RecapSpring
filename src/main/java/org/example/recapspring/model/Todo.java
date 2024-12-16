package org.example.recapspring.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Todo {

    @Id
    private Long id;
    private String title;
    private boolean completed;
}