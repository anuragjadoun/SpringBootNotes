package in.strikes.SpringJDBC.controller;

import in.strikes.SpringJDBC.model.Student;
import in.strikes.SpringJDBC.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("DONE");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student,
                                                @PathVariable Long id){
        studentService.updateStudent(student,id);
        return ResponseEntity.status(HttpStatus.OK).body("DONE");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("DONE");
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentList =studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }


}
