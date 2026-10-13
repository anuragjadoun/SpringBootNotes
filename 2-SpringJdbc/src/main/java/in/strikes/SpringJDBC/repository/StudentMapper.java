package in.strikes.SpringJDBC.repository;

import in.strikes.SpringJDBC.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();

        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setAge(rs.getInt("age"));

        //Here rowNum for tracking and debugging purpose and it always start with 0
        System.out.println("Row number : "+rowNum );

        return  student;
    }
}
