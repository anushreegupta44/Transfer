package com.bank.account.repository;

import com.bank.account.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository {

    public AccountRepository() {
    }

    public Account getAccountByUUID(Integer accountNumber) {
        Account account = null;
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement("SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?");
            statement.setInt(1, accountNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                account = new Account();
                account.setAccountNumber(rs.getInt("account_number"));
                account.setBalance(rs.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void saveTransfer(Account from, Account to) {
        Connection con = null;
        con = Database.getConnection();
        String updateAccount = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_NUMBER = ?";

        PreparedStatement fromAccStmt = null;
        PreparedStatement toAccStmt = null;
        try {
            con.setAutoCommit(false);

            fromAccStmt = con.prepareStatement(updateAccount);
            fromAccStmt.setBigDecimal(1, from.getBalance());
            fromAccStmt.setInt(2, from.getAccountNumber());
            fromAccStmt.executeUpdate();

            toAccStmt = con.prepareStatement(updateAccount);
            toAccStmt.setBigDecimal(1, to.getBalance());
            toAccStmt.setInt(2, to.getAccountNumber());
            toAccStmt.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (fromAccStmt != null && toAccStmt != null) {
                    fromAccStmt.close();
                    toAccStmt.close();
                }
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
