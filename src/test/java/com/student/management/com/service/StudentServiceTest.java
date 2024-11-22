package com.student.management.com.service;

import com.student.management.com.entity.Student;
import com.student.management.com.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllStudents(){
        when(studentRepository.findAll()).thenReturn(
                List.of(
                        new Student(1L,"Jeevan","jeevan@gmail.com"),
                new Student(2L,"Santhosh","santhosh@gmail.com"))
        );
        assertEquals(2,studentService.getAllStudents().size());
    }
    @Test
    public void testSaveStudent(){
        Student student = Student.builder()
                .name("Jeevan")
                .email("jeevan@gmail.com")
                .build();

        when(studentRepository.save(student)).thenReturn(student);
       Student savedStudent = studentService.addStudent(student);
       assertNotNull(savedStudent);
       assertEquals("Jeevan",savedStudent.getName());
    }
    @Test
    public void testStudentById(){
        Student student = Student.builder()
                .id(1L)
                .name("Jeevan")
                .email("jeevan@gmail.com")
                .build();

       when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
      Student optionalStudent = studentService.getStudentById(1L);
      assertNotNull(optionalStudent);
      assertEquals("Jeevan",optionalStudent.getName());
    }

    @Test
      void testDeleteStudent(){
        doNothing().when(studentRepository).deleteById(1L);
        studentService.deleteStudent(1L);
        verify(studentRepository,times(1)).deleteById(1L);
      }
}

