package com.wallet.dao;

import com.wallet.model.User;
import com.wallet.model.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class WalletDao {
    public Wallet createWallet(Wallet wallet){
        String sql = "insert into wallets(name, user_id, is_active, created_date) values(?,?,?,?)";

        try (Connection connection = ConnectionPoolWithDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, wallet.getName());
            preparedStatement.setObject(2, wallet.getUser());
            preparedStatement.setBoolean(3, wallet.isActive());
            preparedStatement.setDate(4, (java.sql.Date) wallet.getCreatedDate());
            preparedStatement.execute();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }return null;
    }

    public List<Wallet> getAllWallets(){
        List<Wallet> wallets = new ArrayList<>();

        try(Connection connection = ConnectionPoolWithDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from wallets")){

            while (resultSet.next()){
                Wallet wallet = new Wallet();
                wallet.setId(resultSet.getLong("id"));
                wallet.setName(resultSet.getString("name"));
                wallet.setUser(wallet.getUser());
                wallet.setActive(resultSet.getBoolean("is_active"));
                wallet.setCreatedDate(resultSet.getDate("created_date"));
                wallets.add(wallet);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }return wallets;
    }

    public Wallet getWalletById(Long walletId){
        String sql = "select * from wallets where id=?";
        try (Connection connection = ConnectionPoolWithDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, walletId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Wallet wallet = new Wallet();
                wallet.setId(resultSet.getLong("id"));
                wallet.setName(resultSet.getString("name"));
                wallet.setUser(wallet.getUser());
                wallet.setActive(resultSet.getBoolean("is_active"));
                wallet.setCreatedDate(resultSet.getDate("created_date"));
                return wallet;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }return null;
    }
}
