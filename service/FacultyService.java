package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long idFaculty) {
        return facultyRepository.getById(idFaculty);
    }



    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long idFaculty) {
        facultyRepository.deleteById(idFaculty);
    }

    public Collection<Faculty> allFaculty() {
        return facultyRepository.findAll();
    }
    public Faculty colorFilterFaculty(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        return facultyRepository.findAllByColorOrNameIgnoreCase(color, name);
    }
}
