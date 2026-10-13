package in.strikes.SpringJDBC.service;

import in.strikes.SpringJDBC.model.Student;
import in.strikes.SpringJDBC.repository.StudentRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){
        studentRepository.createStudent(student);
    }

    public void updateStudent(Student student,Long id){
        studentRepository.updateStudent(student,id);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteStudent(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.getAllStudents();
    }
}
