package in.strikes.repository;

import in.strikes.model.Teacher;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

    String url="jdbc:mysql://localhost:3306/studentdb";
    String username="root";
    String password="anuragjadoun024@gmail";


//    public void createTeacher(){
//
//        try{
//            Connection connectionImpl = DriverManager.getConnection(url,username,password);
//            Statement statement = connectionImpl.createStatement();
//
//            String sql = "Insert into teacher(name,email,age) values ('Pankaj','p@gmail.com',25)";
//
//            int result = statement.executeUpdate(sql);
//            if(result==1){
//                System.out.println("Create operation sucessfull");
//            }else{
//                System.out.println("Create operation failed");
//            }
//
//            connectionImpl.close();
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//        }
//
//    }



//    public void createTeacher(Teacher teacher){
//        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement= connection.createStatement();
//
//            String sql = """
//                    Insert into teacher(name,email,age) values ('%s','%s','%d')"""
//                    .formatted(teacher.getName(),teacher.getEmail(),teacher.getAge());
//
//            int result = statement.executeUpdate(sql);
//            if(result == 1){
//                System.out.println("Create Operation Successfull");
//            }else{
//                System.out.print("Create Operation failed");
//            }
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//            e.printStackTrace();
//        }
//    }


    public void createTeacher(Teacher teacher){
        try{
            Connection connection= DriverManager.getConnection(url,username,password);

            String sql = """
                    Insert into teacher(name,email,age) values(?,?,?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teacher.getName());
            preparedStatement.setString(2,teacher.getEmail());
            preparedStatement.setInt(3,teacher.getAge());

            int result = preparedStatement.executeUpdate();
            if(result==1){
                System.out.println("Insertion successfull");
            }else {
                System.out.println("Insertion failed");
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


//    public void updateTeacher(){
//
//        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//
//            String sql = "Update teacher set age = 23 where id = 2";
//
//            int result = statement.executeUpdate(sql);
//            if(result == 1){
//                System.out.println("Update operation successful");
//            }else{
//                System.out.println("Update operation failed");
//            }
//
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//            e.printStackTrace();
//        }
//    }


    public void updateTeacher(Teacher teacher, Long id){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String sql = """
                    Update teacher Set name = ?,
                    email = ?,
                    age = ?
                    where id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getEmail());
            preparedStatement.setInt(3,teacher.getAge());
            preparedStatement.setLong(4,id);

            int result = preparedStatement.executeUpdate();
            if(result == 1){
                System.out.println("Updation successfull");
            }else{
                System.out.println("Updation failed");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

//    public void deleteTeacher(){
//        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//
//            String sql = "Delete from teacher where id = 3";
//            int result = statement.executeUpdate(sql);
//            if(result==1){
//                System.out.println("Delete operation successfull");
//            }else{
//                System.out.println("Delete operation failed");
//            }
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//            e.printStackTrace();
//        }
//    }


    public void deleteTeacher(Long id){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String sql = """
                    Delete from teacher where id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            int result = preparedStatement.executeUpdate();

            if(result == 1){
                System.out.println("Delete operation successfull");
            }else{
                System.out.println("Delete operation failed");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

//    public void findTeacherById(){
//
//        try{
//           Connection connection = DriverManager.getConnection(url,username,password);
//           Statement statement = connection.createStatement();
//
//           String sql= "Select id,name,email,age from teacher where id = 1";
//           ResultSet resultSet = statement.executeQuery(sql);
//           resultSet.next();
//           Teacher teacher = mapRow(resultSet);
//           System.out.println(teacher);
//           System.out.println("Find operation successfull");
//
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//            e.printStackTrace();
//        }
//    }

    public void findTeacherById(Long id){
        String sql= """
                    Select id, name ,email,age from teacher where id = ?
                    """;
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){




            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Teacher teacher = mapRow(resultSet);
            System.out.println(teacher);


        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

    }


//    public void findAllTeacher(){
//
//        try{
//            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//
//            String sql = "Select id,name,email,age from teacher";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while(resultSet.next()){
//                Teacher teacher = mapRow(resultSet);
//                System.out.println(teacher);
//            }
//
//            connection.close();
//
//        } catch (SQLException e) {
//            System.out.println("Database connection failed");
//            e.printStackTrace();
//        }
//    }


    public void findAllTeacher(){

        List<Teacher> teachers = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            String sql = "Select id,name,email,age from teacher";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Teacher teacher = mapRow(resultSet);
                teachers.add(teacher);
                //System.out.println(teacher);
            }

//            for(Teacher teacher:teachers){
//                System.out.println(teacher);
//            }

            //By Lambda Expression
            teachers.forEach(teacher -> System.out.println(teacher));

            //By Method reference
            teachers.forEach(System.out::println);

            //By Stream Api
            teachers.stream().forEach(System.out::println);

            connection.close();

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


    public void completeCrud(){


         try{
             Connection connection = DriverManager.getConnection(url,username,password);
             Statement statement = connection.createStatement();

             String sql = "Select id,name,email,age from teacher where id=1";
             boolean result = statement.execute(sql);
             List<Teacher> teacherList = new ArrayList<>();
             if(result){
                 ResultSet resultSet =statement.getResultSet();
                 while(resultSet.next()){
                     Teacher teacher = mapRow(resultSet);
                     teacherList.add(teacher);
                 }

             }else {
                 int affectedRows  = statement.getUpdateCount();

                 if(affectedRows == 1){
                     System.out.println("Operation successfull");
                 }else {
                     System.out.println("Operation failed");
                 }
             }

             for (Teacher teacher : teacherList) {
                 System.out.println(teacher);
             }
         } catch (SQLException e) {
             System.out.println("Database connection failed");
             e.printStackTrace();
         }
    }

    private Teacher mapRow(ResultSet resultSet) throws SQLException {
       Teacher teacher = new Teacher();

       teacher.setId(resultSet.getLong("id"));
       teacher.setName(resultSet.getString("name"));
       teacher.setEmail(resultSet.getString("email"));
       teacher.setAge(resultSet.getInt("age"));

       return teacher;
    }

}
