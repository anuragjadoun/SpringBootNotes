package in.strikes.SpringJDBC.service;

import in.strikes.SpringJDBC.model.Teacher;
import in.strikes.SpringJDBC.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public void createTeacher(Teacher teacher){
        teacherRepository.createTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher,Long id){
        teacherRepository.updateTeacher(teacher,id);
    }

    public void deleteTeacher(Long id){
        teacherRepository.deleteTeacher(id);
    }

    public List<Teacher> getAllTeachers(){
       return teacherRepository.getAllTeachers();
    }

    public Teacher getTeacherById(Long id){
        return teacherRepository.getTeacherById(id);
    }

}
