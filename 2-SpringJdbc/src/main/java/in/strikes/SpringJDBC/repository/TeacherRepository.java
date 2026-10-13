package in.strikes.SpringJDBC.repository;

import in.strikes.SpringJDBC.model.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {

    private JdbcTemplate jdbcTemplate;

    public TeacherRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTeacher(Teacher teacher){

        String sql= """
                Insert into teacher(name,email,age) values (?,?,?)
                """;

        int result = jdbcTemplate.update(sql,teacher.getName(),teacher.getEmail(),teacher.getAge());

        if(result == 1){
            System.out.println("Insert operation is successful");
        }else{
            System.out.println("Insert operation is failed");
        }
    }

    public void updateTeacher(Teacher teacher, Long id){

        String sql= """
                Update teacher set name=?, email=?, age=? where id = ?
                """;

        int result = jdbcTemplate.update(sql,teacher.getName(),teacher.getEmail(),teacher.getAge(),id);

        if(result == 1){
            System.out.println("Update operation is successful");
        }else{
            System.out.println("Update operation is failed");
        }
    }

    public void deleteTeacher(Long id){
         String sql= """
                 Delete from teacher where id = ?
                 """;

         int result = jdbcTemplate.update(sql,id);

        if(result == 1){
            System.out.println("Delete operation is successful");
        }else{
            System.out.println("Delete operation is failed");
        }

    }

    public List<Teacher> getAllTeachers(){

        String sql= """
                Select id,name,email,age from teacher
                """;

        List<Teacher> teacherList = jdbcTemplate.query(sql,new TeacherMapper());
        return teacherList;

    }

    public Teacher getTeacherById(Long id){

        String sql = """
                Select id, name,email,age from teacher where id = ?
                """;

        Teacher teacher = jdbcTemplate.queryForObject(sql,new TeacherMapper(), id);

        return teacher;
    }

}
