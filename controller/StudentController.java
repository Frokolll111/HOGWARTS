package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping(consumes = {"application/xml", "application/json"})
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    @GetMapping("{idStudent}")
    public ResponseEntity<Student> readStudent(@PathVariable long idStudent) {
        Student student = studentService.readStudent(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{idStudent}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long idStudent) {
        studentService.deleteStudent(idStudent);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> allStudent() {
        return ResponseEntity.ok(studentService.allStudent());
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> ageFilterStudent(@PathVariable int age) {
        return ResponseEntity.ok(studentService.ageFilterStudent(age));
    }

    @GetMapping("findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }
}
