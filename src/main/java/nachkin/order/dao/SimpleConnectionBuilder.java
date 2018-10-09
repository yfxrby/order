package nachkin.order.dao;

import nachkin.order.config.GlobalConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionBuilder implements ConnectionBuilder {

    public SimpleConnectionBuilder() {
        try {
            Class.forName(GlobalConfig.getProperty("db.driver.class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = GlobalConfig.getProperty("db.url");
        String login = GlobalConfig.getProperty("db.login");
        String password = GlobalConfig.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }

}
