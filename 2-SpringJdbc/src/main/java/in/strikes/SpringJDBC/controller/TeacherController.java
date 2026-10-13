package in.strikes.SpringJDBC.controller;

import in.strikes.SpringJDBC.model.Teacher;
import in.strikes.SpringJDBC.repository.TeacherRepository;
import in.strikes.SpringJDBC.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher){
        teacherService.createTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body("DONE");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTeacher(@RequestBody Teacher teacher,
                                                @PathVariable Long id){
        teacherService.updateTeacher(teacher,id);
        return ResponseEntity.status(HttpStatus.OK).body("DONE");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.OK).body("DONE");
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        List<Teacher> teachersList = teacherService.getAllTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(teachersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id){
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(teacher);
    }

}
