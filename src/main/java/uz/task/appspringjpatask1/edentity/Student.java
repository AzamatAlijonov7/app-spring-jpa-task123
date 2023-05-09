package uz.task.appspringjpatask1.edentity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @RestController
    @RequestMapping("/students")
    public class StudentController {

        @Autowired
        private StudentRepository studentRepository;

        @GetMapping
        public Iterable<Student> getAllStudents() {
            return studentRepository.findAll();
        }

        @PostMapping
        public Student createStudent(@RequestBody Student student) {
            return studentRepository.save(student);
        }

        @GetMapping("/{id}")
        public Student getStudentById(@PathVariable long id) {
            return (Student) studentRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Student not found"
                    ));
        }

        @PutMapping("/{id}")
        public Student updateStudent(@RequestBody Student newStudent, @PathVariable long id) {
            return studentRepository.findById(id)
                    .map(student -> {
                        student.hashCode();
                        student.hashCode();
                        return studentRepository.save((Student) student);
                    })
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Student not found"
                    ));
        }

        @DeleteMapping("/{id}")
        public void deleteStudent(@PathVariable long id) {
            studentRepository.deleteById(id);
        }
    }

}

