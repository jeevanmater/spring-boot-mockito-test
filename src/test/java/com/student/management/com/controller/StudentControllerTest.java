package com.student.management.com.controller;

import com.student.management.com.entity.Student;
import com.student.management.com.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StudentControllerTest {
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllStudents(){
        when(studentService.getAllStudents()).thenReturn(
             List.of(
                     new Student(1L,"Jeevan","jeevan@gmail"),
                     new Student(2L,"Santhosh","santhosh@gmail.com")
             )
        );
        List<Student> allStudents = studentController.getAllStudents();
        assertEquals(2,allStudents.size());

    }
    @Test
    void testAddStudent(){
        Student student = Student.builder()
                .name("Jeevan")
                .email("jeevan@gmail.com")
                .build();
        when(studentService.addStudent(student)).thenReturn(student);
       ResponseEntity<Student> savedStudent = studentController.addStudent(student);
       assertEquals(HttpStatus.OK,savedStudent.getStatusCode());
       assertEquals("Jeevan",savedStudent.getBody().getName());
    }
}
