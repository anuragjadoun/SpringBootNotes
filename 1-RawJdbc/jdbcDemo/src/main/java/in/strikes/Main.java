package in.strikes;



import in.strikes.model.Student;
import in.strikes.model.Teacher;
import in.strikes.model.User;
import in.strikes.repository.StudentsRepository;
import in.strikes.repository.TeacherRepository;
import in.strikes.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        String url ="jdbc:mysql://localhost:3306/studentdb";
//        String username="root";
//        String password="anuragjadoun024@gmail";
//
//        try{
//            Connection connection =DriverManager.getConnection(url,username,password);
//            System.out.println("Database Connection successfull");
//        } catch (SQLException e) {
//            System.out.println("Database Connection Failed");
//        }


        UserRepository userRepository = new UserRepository();
        userRepository.getUserById(3L);
//        StudentsRepository studentsRepository = new StudentsRepository();
//        studentsRepository.createStudent(new Student("Arman","arman@gmail.com",22));



//        TeacherRepository teacherRepository = new TeacherRepository();
        //teacherRepository.completeCrud();
        //teacherRepository.createTeacher(new Teacher("Eshika","e@gmail.com" , 29));
        //teacherRepository.updateTeacher(new Teacher("Indu","i@gmail.com",24), 6L);

//        teacherRepository.findTeacherById(4L);

























        //UserRepository userRepository = new UserRepository();
        //userRepository.createUser(new User("Dhruv","dj@gmail.com",20));

        //userRepository.updateUser(new User("Rohit","rohit@gmail.com", 23),
               // 6L);

        //userRepository.deleteUser(8L);

       // userRepository.getUserById(7L);

        //userRepository.getUsers();









    }
}