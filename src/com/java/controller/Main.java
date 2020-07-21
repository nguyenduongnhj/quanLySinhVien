/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.controller;

import com.java.giaodien.login;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bot
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        DBConnector.getConnect();
        StudentManager sm = new StudentManager();
        List<Student> list = new ArrayList<>();
        list = sm.getListStudent();
//        System.out.println(list.get(3).isGioiTinh());
//        Date date = new Date(2000, 05, 25);
//        sm.addNewStudent("ten", "ct012", "lop 3", date, true, "que quan");
        new login().setVisible(true);
    }
    
}
