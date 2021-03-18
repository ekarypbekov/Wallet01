package com.wallet.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolWithDB {
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        try {
            Class.forName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/walletDB");
            dataSource.setUsername("postgres");
            dataSource.setPassword("5454");
            dataSource.setInitialSize(20);    //  начальное подключение к БД при запуске проекта
            //dataSource.setMaxTotal(40);       //  одновременно максимальное подключение к БД
            dataSource.setMaxOpenPreparedStatements(100);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
