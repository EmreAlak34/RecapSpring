package org.example.recapspring.service;

import org.example.recapspring.model.ToDo;
import org.example.recapspring.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    // Alle ToDos zurückgeben
    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    // Ein neues ToDo erstellen
    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    // ToDo nach ID abrufen
    public ToDo getToDoById(Long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        return optionalToDo.orElseThrow(() -> new RuntimeException("ToDo not found"));
    }

    // ToDo aktualisieren
    public ToDo updateToDo(Long id, ToDo toDo) {
        if (toDoRepository.existsById(id)) {
            toDo.setId(id);
            return toDoRepository.save(toDo);
        }
        throw new RuntimeException("ToDo not found");
    }

    // ToDo löschen
    public void deleteToDo(Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
        } else {
            throw new RuntimeException("ToDo not found");
        }
    }
}
