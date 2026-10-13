package in.strikes.SpringJDBC.repository;

import in.strikes.SpringJDBC.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {

        Teacher teacher = new Teacher();

        teacher.setId(rs.getLong("id"));
        teacher.setName(rs.getString("name"));
        teacher.setEmail(rs.getString("email"));
        teacher.setAge(rs.getInt("age"));

        return teacher;
    }
}
