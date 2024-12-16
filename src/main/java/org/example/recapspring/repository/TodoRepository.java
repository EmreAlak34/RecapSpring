package org.example.recapspring.repository;

import org.example.recapspring.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<ToDo, Long> {

}
