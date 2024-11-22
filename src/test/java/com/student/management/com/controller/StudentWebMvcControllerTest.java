package com.student.management.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.management.com.entity.Student;
import com.student.management.com.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class StudentWebMvcControllerTest {

    private MockMvc mockMvc;
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;
    @Autowired
    private ObjectMapper objectMapper;
    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        student = new Student();
        student.setId(1L);
        student.setName("Jeevan");
        student.setEmail("jeevan@gmail.com");

    }

    @Test
    void getAllStudentsTest() throws Exception {
        when(studentService.addStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform((RequestBuilder) post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(1))
                .andExpect((ResultMatcher) jsonPath("$.name").value("John Doe"))
                .andExpect((ResultMatcher) jsonPath("$.email").value("john.doe@example.com"));

        verify(studentService, times(1)).addStudent(any(Student.class));
    }

}
