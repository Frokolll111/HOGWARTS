package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student readStudent(long idStudent) {
        return studentRepository.getById(idStudent);
    }



    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long idStudent) {
        studentRepository.deleteById(idStudent);
    }

    public Collection<Student> allStudent() {
        return studentRepository.findAll();
    }

    public Collection ageFilterStudent(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }
}
