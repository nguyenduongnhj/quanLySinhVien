/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author bot
 */
public class DBConnector {
    public static Connection getConnect() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connStr = "jdbc:sqlserver://localhost:4443;databaseName=QUANLYSINHVIEN;user=sa;password=123456789;";
        Connection conn = DriverManager.getConnection(connStr);
        return conn;
    }
}
