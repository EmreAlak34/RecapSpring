package org.example.recapspring.repository;

import org.example.recapspring.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// Die Schnittstelle für die Repository-Logik
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Du kannst hier benutzerdefinierte Methoden hinzufügen, falls nötig
}
