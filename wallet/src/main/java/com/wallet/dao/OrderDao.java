package com.wallet.dao;

import com.wallet.model.Category;
import com.wallet.model.Order;
import com.wallet.model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OrderDao {

    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();

        try(Connection connection = ConnectionPoolWithDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders")){

            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setCategory(Category.builder().build());
                order.setAmount(resultSet.getLong("amount"));
                order.setDescription(resultSet.getString("description"));
                order.setWallet(Wallet.builder().build());
                order.setExpense(resultSet.getBoolean("is_expense"));
                order.setCreatedDate(resultSet.getDate("created_date"));
                orders.add(order);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return orders;
    }

    public Order createOrder(Order order){
        String sql = "insert into orders(category_id, amount, description, wallet_id, is_expense, created_date) values(?,?,?,?,?,?)";

        try (Connection connection = ConnectionPoolWithDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setObject(1, order.getCategory());
            preparedStatement.setLong(2, order.getAmount());
            preparedStatement.setString(3, order.getDescription());
            preparedStatement.setObject(4, order.getWallet());
            preparedStatement.setBoolean(5, order.isExpense());
            preparedStatement.setDate(6, (java.sql.Date) order.getCreatedDate());
            preparedStatement.execute();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }return null;
    }

    public List<Order> getAllOrdersByCategoryId(Category category){
        List<Order> orders = new ArrayList<>();
        String sql = "select * from orders " +
                "where category_id = ?";
        Order order = null;

        try (Connection connection = ConnectionPoolWithDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setObject(1, category.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Order order1 = new Order();
                order1.setId(resultSet.getLong("id"));
                order1.setCategory(Category.builder().build());
                order1.setAmount(resultSet.getLong("amount"));
                order1.setDescription(resultSet.getString("description"));
                order1.setWallet(Wallet.builder().build());
                order1.setExpense(resultSet.getBoolean("is_expense"));
                order1.setCreatedDate(resultSet.getDate("created_date"));
                orders.add(order1);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }return orders;
    }

    public void deleteOrderById(Long order){
        String sql = "delete from orders " +
                "where id = ?";

        try (Connection connection = ConnectionPoolWithDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, order);
            preparedStatement.executeQuery();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
