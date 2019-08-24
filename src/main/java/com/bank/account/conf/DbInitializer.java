package com.bank.account.conf;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.*;

@WebListener
public class DbInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:bank;DB_CLOSE_DELAY=-1", "sa", "");
            Statement stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE ACCOUNT(account_number int primary key, balance numeric)");
            stat.close();

            stat = conn.createStatement();
            stat.executeUpdate("INSERT INTO ACCOUNT(ACCOUNT_NUMBER, BALANCE) VALUES (1, 200.0)");
            stat.close();

            stat = conn.createStatement();
            stat.executeUpdate("INSERT INTO ACCOUNT(ACCOUNT_NUMBER, BALANCE) VALUES (2, 300.0)");
            stat.close();

            stat = conn.createStatement();
            stat.executeUpdate("INSERT INTO ACCOUNT(ACCOUNT_NUMBER, BALANCE) VALUES (3, 100.0)");
            stat.close();

            stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("select * from ACCOUNT");
            while (rs.next()) {
                System.out.println("acount_num"+rs.getBigDecimal("account_number"));
                System.out.println("bal"+rs.getBigDecimal("balance"));
            }
            conn.close();

        } catch (SQLException e) {

        }
    }

}



