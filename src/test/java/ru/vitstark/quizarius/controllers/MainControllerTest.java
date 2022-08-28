package ru.vitstark.quizarius.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void testMainPage() throws Exception {
        mvc.perform(get("/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(content().string(
                        containsString("Quizarius")));
    }
}
