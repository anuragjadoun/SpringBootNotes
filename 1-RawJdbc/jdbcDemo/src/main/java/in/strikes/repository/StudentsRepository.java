package in.strikes.repository;

import in.strikes.model.Student;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {

    String url="jdbc:mysql://localhost:3306/studentdb";
    String username="root";
    String password="anuragjadoun024@gmail";

//    public void createStudent(){
//        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//
//            System.out.println("Database connection is successful");
//
//            String sql = "Insert into student(name,email,age) values('Vinit','vinit@gmail','23')";
//            int result = statement.executeUpdate(sql);
//            if(result ==1){
//                System.out.println("Insert operation is successful");
//            }else{
//                System.out.println("Insert operation is failed");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//        }
//    }



//    public void createStudent(Student student){
//        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//
//            System.out.println("Database connection is successful");
//
//            String sql = """
//                      INSERT into student(name,email,age) values ('%s','%s','%d')
//                      """.formatted(student.getName(),student.getEmail(),student.getAge());
//
//            int result = statement.executeUpdate(sql);
//            if(result ==1){
//                System.out.println("Insert operation is successful");
//            }else{
//                System.out.println("Insert operation is failed");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//            e.printStackTrace();
//        }
//    }


    public void createStudent(Student student){
        String sql = """
                Insert into student(name,email,age) values(?,?,?)
                """;


        try(Connection connection = DriverManager.getConnection(url,username,password);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);){

            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setInt(3,student.getAge());

            int result = preparedStatement.executeUpdate();
            if(result ==1){
                System.out.println("Insert operation is successful");
            }else{
                System.out.println("Insert operation is failed");
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

//    public void updateStudent(){
//        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//            System.out.println("Database Connection is Successful");
//
//            String sql="Update student set age=25 where id=4";
//            int result = statement.executeUpdate(sql);
//            if(result == 1){
//                System.out.println("Update operation is successful");
//            }else{
//                System.out.println("Update operation is failed");
//            }
//        } catch (SQLException e) {
//            System.out.println("Database Connection Failed");
//            e.printStackTrace();
//        }
//    }


public void updateStudent(int age,Long id){
    try{
        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();
        System.out.println("Database Connection is Successful");

        String sql = """
                  UPDATE student set age = '%d' where id = '%d'
                  """.formatted(age,id);
        int result = statement.executeUpdate(sql);
        if(result == 1){
            System.out.println("Update operation is successful");
        }else{
            System.out.println("Update operation is failed");
        }
    } catch (SQLException e) {
        System.out.println("Database Connection Failed");
        e.printStackTrace();
    }
}


    public void deleteStudent(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            System.out.println("Database connection is successful");

            String sql = "Delete from student where id=4";
            int result = statement.executeUpdate(sql);
            if(result == 1){
                System.out.println("Delete operation is successful");
            }else{
                System.out.println("Delete operation failed");
            }

        } catch (SQLException e) {
            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }
    }

    public void getStudentById(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            String sql = "Select name,email,age from student where id = 1";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            Student student = mapRow(resultSet);

            System.out.println(student);
        }catch (SQLException e){
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    public void getAllStudents(){
        List<Student> studentsList = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            String sql = "Select id,name,email,age from student";

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Student student = mapRow(resultSet);
                studentsList.add(student);
            }

            for (Student student:studentsList){
                System.out.println(student);
            }
        }catch (SQLException e){
            System.out.println("Database connection is failed");
            e.printStackTrace();
        }
    }


    public void completeCrud(){
        List<Student> studentList = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            System.out.println("Database connection successful");

            String sql = "Select id,name,email,age from student";

            boolean result = statement.execute(sql);
            if(result){
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    Student student = mapRow(resultSet);
                    studentList.add(student);
                }

                for (Student student : studentList){
                    System.out.println(student);
                }
            }else {
                int res = statement.getUpdateCount();

                if(res == 1){
                    System.out.println("Operation is successful");
                }else{
                    System.out.println("Operation is failed");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }
}
