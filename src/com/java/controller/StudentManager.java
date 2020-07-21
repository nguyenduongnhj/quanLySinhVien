/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bot
 */
public class StudentManager {
    Connection conn;
    
     public StudentManager() throws SQLException, ClassNotFoundException, Exception {
        DBConnector db = new DBConnector();
        this.conn = db.getConnect();
    }
    
    
    public List<Student> getListStudent() throws SQLException{
        List<Student> list = new ArrayList<>();
        String query = "select * from thong_tin";
        PreparedStatement pstmt = this.conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        Student s = null;
        while(rs.next()){
            s = new Student(rs.getString("ho_ten"), rs.getString("ma_sinh_vien"), rs.getString("lop"), rs.getDate("ngay_sinh"), rs.getBoolean("gioi_tinh"), rs.getString("que_quan"));
            list.add(s);
        }
        return list;
    }
    
    public List<MonHoc> getListDiem(String maSv) throws SQLException{
        List<MonHoc> list = new ArrayList<>();
        String query = "select * from diem_thi where ma_sinh_vien = '"+ maSv +"'";
        PreparedStatement pstmt = this.conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        MonHoc m = null;
        while(rs.next()){
            m = new MonHoc(rs.getString("ma_mon_hoc"), rs.getString("ma_sinh_vien"), rs.getFloat("diem"));
            list.add(m);
        }
        return list;
    }
    
    public boolean login(String userName, String pass) throws SQLException{
        String query = "select * from account where userName = '"+userName+"' and pass = '"+pass+"'";
        PreparedStatement pstmt = this.conn.prepareStatement(query);
 
        
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean addNewStudent(String ten, String maSv , String lop, Date ngaySinh, boolean gioiTinh, String queQuan) throws SQLException{
        String query = "select * from thong_tin where ma_sinh_vien = '"+maSv+"'";
        PreparedStatement pstmt = this.conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return false;
        }else {
            int gt;
            if(gioiTinh){
                gt = 1;
            }else {
                gt = 0;
            } 
            Date date = new Date();
            SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
            String stringDate= DateFor.format(ngaySinh);
            String query2 = "INSERT INTO QUANLYSINHVIEN.dbo.thong_tin (ma_sinh_vien, ho_ten, ngay_sinh, gioi_tinh, que_quan, lop)"
                    + " VALUES ('"+maSv+"', '"+ten+"', '"+stringDate+"', "+gt+", '"+queQuan+"', '"+lop+"');";
            PreparedStatement pstmt2 = this.conn.prepareStatement(query2);
            pstmt2.execute();
            return true;
        }
    }
 
    public boolean addNewMonHoc(String maMonHoc, String ten, String giaoVien) throws SQLException{
        String query = "select * from danh_sach_mon_hoc where ma_mon_hoc = '"+maMonHoc+"'";
        PreparedStatement pstmt = this.conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return false;
        } else {
            String query2 = "INSERT INTO QUANLYSINHVIEN.dbo.danh_sach_mon_hoc (ma_mon_hoc, ten_mon_hoc, giao_vien) VALUES ('"+maMonHoc+"', '"+ten+"', '"+giaoVien+"');";
            PreparedStatement pstmt2 = this.conn.prepareStatement(query2);
           
            pstmt2.execute();
            return true;
        }
    }
    
    public boolean deleteStudent (String maSv) throws SQLException{
        String query = "DELETE FROM thong_tin WHERE ma_sinh_vien ='"+maSv+"';";
        PreparedStatement pstmt = this.conn.prepareStatement(query);
        pstmt.execute();
        return true; 
    }
    
    public boolean nhapDiem(boolean check, float diem, String maMon, String maSv) throws SQLException{
        if(check){
            String query = "UPDATE QUANLYSINHVIEN.dbo.diem_thi SET diem = '" + diem + "' WHERE ma_mon_hoc = '"+maMon+"' and ma_sinh_vien='"+maSv+"';";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.execute();
            
        } else {
            String query2 = "INSERT INTO QUANLYSINHVIEN.dbo.diem_thi (ma_mon_hoc, ma_sinh_vien, diem) VALUES ('"+maMon+"', '"+maSv+"', '"+diem+"');";
            PreparedStatement pstmt2 = this.conn.prepareStatement(query2);
           
            pstmt2.execute();

        }
        return true;
    }
    
    public boolean updateStudent(String ten, String maSv , String lop, Date ngaySinh, boolean gioiTinh, String queQuan) throws SQLException{
        int gt;
        if(gioiTinh){
            gt = 1;
        } else {
            gt = 0;
        }
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate= DateFor.format(ngaySinh);
        System.out.println("abc "+stringDate);
        String query = "UPDATE QUANLYSINHVIEN.dbo.thong_tin SET ho_ten = '"+ten+"' , ngay_sinh = '"+stringDate+"' , gioi_tinh = '"+gt+"', que_quan = '"+queQuan+"', lop = '"+lop+"'  WHERE ma_sinh_vien='"+maSv+"';";
        PreparedStatement pstmt = this.conn.prepareStatement(query);
        pstmt.execute();
        return true;
    }
    

}
