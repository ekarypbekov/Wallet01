package com.wallet.dao;

import com.wallet.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserDao {

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try(Connection connection = DatabaseConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users ")){

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedDate(resultSet.getDate("created_date"));
                users.add( user);

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

//    public User getUserById(Long userId){
//        return users.get(userId);
//    }
//
//    public User createUser(User user){
//        users.put(user.getId(), user);
//        return users.get(user.getId());
//    }
//
//    public User updateUser(User user){
//        users.put(user.getId(), user);
//        return users.get(user.getId());
//    }
//
//    public void deleteUserById(Long userId){
//        users.remove(userId);
//    }


}
