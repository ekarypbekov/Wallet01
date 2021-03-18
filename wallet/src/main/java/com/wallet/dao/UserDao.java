package com.wallet.dao;

import com.wallet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserDao {

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPoolWithDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users")){

            while (resultSet.next()){
                User user = new User();
                //user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedDate(resultSet.getDate("created_date"));
                users.add(user);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public  User getUserById(Long userId){
        String sql = "select * from users where id=?";
        try (Connection connection = ConnectionPoolWithDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedDate(resultSet.getDate("created_date"));
                return user;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User createUser(User user){
        String sql = "insert into users(name, password, created_date) values(?,?,?)";

        try(Connection connection = ConnectionPoolWithDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, (java.sql.Date) user.getCreatedDate());
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        //  Long lastId = getLastUserId
        //  user = getUserById(lastId)
        return null;
    }

    public User updateUser(User user){
        String sql = "update users " +
                "set name = ? where id = ?";

        try (Connection connection = ConnectionPoolWithDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void deleteUserById(Long user){
        String sql = "delete from users " +
                "where id = ?";

        try(Connection connection = ConnectionPoolWithDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, user);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }


}
