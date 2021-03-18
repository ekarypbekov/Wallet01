package com.wallet.dao;

import com.wallet.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public Category createCategory(Category category){
        String sql = "insert into categories(name, user_id, parent_category_id, is_active, created_date) values(?,?,?,?,?)";

        try (Connection connection = ConnectionPoolWithDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, category.getName());
            preparedStatement.setObject(2, category.getUser());
            preparedStatement.setLong(3, category.getId());
            preparedStatement.setBoolean(4, category.getIsActive());
            preparedStatement.setDate(5, (java.sql.Date) category.getCreatedDate());
            preparedStatement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }return null;
    }

    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();

        try (Connection connection = ConnectionPoolWithDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from categories")){

            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                category.setUser(category.getUser());
                category.setParentCategory(category.getParentCategory());
                category.setIsActive(resultSet.getBoolean("is_active"));
                category.setCreatedDate(resultSet.getDate("created_date"));
                categories.add(category);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }return categories;
    }

    public List<Category> getAllCategoryByParentCategoryId(Long categoryId){
        List<Category> categories = new ArrayList<>();
        String sql = "select * from categories where parent_category_id = ?";
        //Category category = null;

        try (Connection conn = ConnectionPoolWithDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, categoryId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Category category1 = new Category();
                category1.setId(resultSet.getLong("id"));
                category1.setName(resultSet.getString("name"));
                category1.setUser(category1.getUser());
                category1.setParentCategory(category1.getParentCategory());
                category1.setIsActive(resultSet.getBoolean("is_active"));
                category1.setCreatedDate(resultSet.getDate("create_date"));
                categories.add(category1);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }return categories;
    }
}









