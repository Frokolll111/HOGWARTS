import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    @Mock
    FacultyRepository facultyRepositoryMoc;
    FacultyService facultyService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        facultyService = new FacultyService(facultyRepositoryMoc);
    }

    @Test
    public void updateFacultyTest() {
        when(facultyRepositoryMoc.findById(1L)).thenReturn(Optional.of(new Faculty(1L, "grif", "red")));
        when(facultyRepositoryMoc.save(any())).then(invocation -> invocation.getArguments()[0]);
        var updateFaculty = facultyService.updateFaculty(new Faculty(1L,"name1","green"));
        assertThat(updateFaculty.getColor()).isEqualTo(22);

        var empty = facultyService.updateFaculty(new Faculty(10L, "empty", "green"));
        assertThat(empty).isNull();
    }

    @Test
    public void deleteFacultyTest() {
        facultyService.readFaculty(1);
        verify(facultyRepositoryMoc, times(1)).deleteById(1L);
    }

    @BeforeEach
    @Test
    public void createFacultyTest() {
        Faculty expected = new Faculty(1L, "name1", "orange");
        Faculty actualFaculty = new Faculty(0L, "name1", "orange");
        Assertions.assertEquals(expected,facultyService.createFaculty(actualFaculty));
    }
}
