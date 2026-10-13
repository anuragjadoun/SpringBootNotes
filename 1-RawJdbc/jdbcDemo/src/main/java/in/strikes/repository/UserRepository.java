package in.strikes.repository;

import in.strikes.model.User;
import jdk.jshell.spi.ExecutionControl;

import java.sql.*;

public class UserRepository {

    String url="jdbc:mysql://localhost:3306/studentsdb";
    String username="root";
    String password = "YOUR_PASSWORD";

    public void createUser(User user){
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            String sql = """
                    Insert into user(name,email,age) values (?,?,?)
                    """;
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setInt(3,user.getAge());

            int result = preparedStatement.executeUpdate();

            if(result == 1){
                System.out.println("Create operation is successfull");
            }else{
                System.out.println("Create operation is failed");
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }


    public void updateUser(User user,Long id){
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            String sql = """
                    Update user set age = ? where id = ?
                    """;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,user.getAge());
            ps.setLong(2,id);

            int result = ps.executeUpdate();
            if(result == 1){
                System.out.println("Update operation is successfull");
            }else {
                System.out.println("Update operation is failed");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Database connection is failed");
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id){
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            String sql = """
                    Delete from user where id = ?
                    """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,id);
            int result = ps.executeUpdate();

            if(result == 1){
                System.out.println("Delete operation is successfull");
            }else{
                System.out.println("Delete operation is failed");
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Database Connection is failed");
        }
    }

    public void getUserById(Long id){

        String sql = """
                    Select id,name,email,age from user where id = ?
                    """;
        try(Connection connection = DriverManager.getConnection(url,username,password);


            PreparedStatement ps = connection.prepareStatement(sql);){

            ps.setLong(1,id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            User user = mapRow(resultSet);
            System.out.println(user);


        } catch (SQLException e) {
            System.out.println("Database connection is failed");
        }
    }

    public void completeCRUD(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            String sql = "select id, name,email,age from user where id=1";
            boolean result = statement.execute(sql);
            if(result == true){
                ResultSet resultSet = statement.getResultSet();

                resultSet.next();
                User user = mapRow(resultSet);
                System.out.println(user);

            }else{
                int rowAffected = statement.getUpdateCount();

                if(rowAffected == 1){
                    System.out.println("Operation is successfull");
                }else{
                    System.out.println("Operation is failed");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection is failed");
            e.printStackTrace();
        }
    }


    private User mapRow(ResultSet resultSet) throws SQLException{
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setAge(resultSet.getInt(4));

        return user;
    }

}
