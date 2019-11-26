package de.hunjy.utils;

import java.sql.*;

/*
    Create by hunjy on 20.11.2019
    @auther: hunjy
    @date: 20.11.2019
    @time: 23:41
    @projekt: CaseOpening
*/
public class MySQL {

    private Connection connection;

    public MySQL(String user, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + user + "?autoReconnect=true", user, password);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doUpdate(String update) {

        try {
            PreparedStatement ps = connection.prepareStatement(update);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ResultSet getResult(String query) {

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void createTables() {
        doUpdate("CREATE TABLE IF NOT EXISTS Coins(UUID varchar(64) DEFAULT NULL, Coins int(16) DEFAULT NULL)");
    }

    public Connection getConnection() {
        return connection;
    }
}
