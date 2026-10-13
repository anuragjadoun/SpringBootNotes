package in.strikes.SpringJDBC.repository;

import in.strikes.SpringJDBC.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStudent(Student student){
        String sql = """
                Insert into student(name,email,age) values(?,?,?)
                """;

        int result = jdbcTemplate.update(sql,student.getName(),student.getEmail(),student.getAge());

        if(result == 1){
            System.out.println("Create operation is successfull");
        }else{
            System.out.println("Create operation is failed");
        }
    }

    public void updateStudent(Student student,Long id){
        String sql= """
                UPDATE student set 
                name = ?,
                email = ?,
                age = ?
                where id = ?
                """;

        int result = jdbcTemplate.update(sql,student.getName(),student.getEmail(),student.getAge(),id);

        if(result == 1){
            System.out.println("Update operation sucessfull");
        }else{
            System.out.println("Update operation failed");
        }
    }

    public void deleteStudent(Long id){
        String sql = """
                DELETE from student where id = ?
                """;

        int result = jdbcTemplate.update(sql,id);

        if(result == 1){
            System.out.println("Delete operation is successfull");
        }else{
            System.out.println("Delete operation is failed");
        }
    }

    public List<Student> getAllStudents(){
        String sql = """
                Select id,name,email,age from student
                """;

        List<Student> studentList = jdbcTemplate.query(sql,new StudentMapper());

        return studentList;
    }

}
