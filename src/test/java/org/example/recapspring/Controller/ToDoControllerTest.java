package org.example.recapspring.controller;

import org.example.recapspring.model.ToDo;
import org.example.recapspring.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private ToDoController toDoController;

    private ToDo mockToDo;

    @BeforeEach
    public void setup() {
        mockToDo = new ToDo();
        mockToDo.setId(1L);
        mockToDo.setDescription("Test ToDo");
        mockToDo.setDone(false);
    }

    @Test
    public void testGetAllToDos() throws Exception {
        when(toDoService.getAllToDos()).thenReturn(List.of(mockToDo));

        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Test ToDo"));
    }

    @Test
    public void testCreateToDo() throws Exception {
        when(toDoService.createToDo(mockToDo)).thenReturn(mockToDo);

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"description\": \"Test ToDo\", \"done\": false }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("Test ToDo"));
    }

    @Test
    public void testGetToDoById() throws Exception {
        when(toDoService.getToDoById(1L)).thenReturn(mockToDo);

        mockMvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Test ToDo"));
    }

    @Test
    public void testUpdateToDo() throws Exception {
        when(toDoService.updateToDo(1L, mockToDo)).thenReturn(mockToDo);

        mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"description\": \"Updated ToDo\", \"done\": true }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated ToDo"));
    }

    @Test
    public void testDeleteToDo() throws Exception {
        mockMvc.perform(delete("/api/todo/1"))
                .andExpect(status().isNoContent());
    }
}
