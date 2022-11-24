package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    //    private final FacultyRepository facultyRepository;
    private final AvatarService avatarService;

    public StudentService(StudentRepository studentRepository, AvatarService avatarService, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.avatarService = avatarService;
//        this.facultyRepository = facultyRepository;
    }

    public Student getStudentById(Long id) {
        logger.info("Вызван метод getStudentById");
        return studentRepository.findById(id).get();
    }

    public Student addStudent(Student student) {
        logger.info("Вызван метод addStudent");
        student.setId(0L);
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        logger.info("Вызван метод editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Вызван метод deleteStudent");
        avatarService.deleteAvatarByStudentId(id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudentsByAge(Long age) {
        logger.info("Вызван метод getAllStudentsByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(Long min, Long max) {
        logger.info("Вызван метод findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findAllStudents() {
        logger.info("Вызван метод findAllStudents");
        return studentRepository.findAll();
    }

    public Faculty getStudentsFacultyByStudentsId(Long id) {
        logger.info("Вызван метод getStudentsFacultyByStudentsId");
        return studentRepository.findById(id).get().getFaculty();
    }

    public Long getStudentsCount() {
        logger.info("Вызван метод getStudentsCount");
        return studentRepository.getStudentsCount();
    }

    public Long getStudentsAgeAvg() {
        logger.info("Вызван метод getStudentsAgeAvg");
        return studentRepository.getStudentsAgeAvg();
    }

    public Collection<Student> getLastFiveStudents() {
        logger.info("Вызван метод getLastFiveStudents");
        return studentRepository.getLastFiveStudents();
    }

    public List<String> getStudentsStartingWithA() {
        logger.info("Вызван метод getStudentsStartingWithA");
        return studentRepository.findAll().stream()
                .parallel()
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .toList();
    }

    public Double getAverageStudentsAge() {
        logger.info("Вызван метод getAverageStudentsAge");
        return studentRepository.findAll().stream()
                .parallel()
                .mapToLong(Student::getAge)
                .average()
                .orElseThrow();
    }
}