/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.Subject_interface;
import Model.Subject;
import Tool.DataDispatcher;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mojeb
 */
public class Subject_impl implements Subject_interface{
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Subject_impl(){
        this(null);
    }

    public Subject_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponce() {
        return response;
    }

    @Override
    public void addSubject(Subject sub) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.subject VALUES(?,NOW(),?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, 0);
            pStatement.setInt(2, sub.getCount());
            pStatement.setString(3, sub.getSubj());
            pStatement.setString(4, sub.getDepartment());

            if (pStatement.executeUpdate() > 0) {
                response = SUCCESS;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, pStatement, connection);
        }
    }

    @Override
    public void deleteSubject(Subject sub) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.subject "
                + "WHERE subject_id = "+sub.getSubject_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            if (pStatement.executeUpdate() > 0) {
                response = SUCCESS;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, pStatement, connection);
        }
    }
    
    @Override
    public ArrayList<Subject> getSubjectByDate(Date date, String subjectUser) throws ErrorException, FileNotFoundException, IOException {
        String dateStr = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate();
        
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.subject "
                + "WHERE date = '"+dateStr+"' "
                + "AND department = '"+ subjectUser+"'";
        ResultSet resultSet = null;
        ArrayList<Subject> subjectList = new ArrayList<Subject>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Subject sub = new Subject();
                sub.setSubject_id(resultSet.getInt("subject_id"));
                sub.setDate(resultSet.getDate("date"));
                sub.setCount(resultSet.getInt("count"));
                sub.setSubj(resultSet.getString("subject"));
                sub.setDepartment(resultSet.getString("department"));
                
                subjectList.add(sub);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return subjectList;
    }

    @Override
    public ArrayList<Subject> getSubjectByRange(Date date1, Date date2, String user) throws ErrorException, FileNotFoundException, IOException {
        
        String dateStrFrom = (date1.getYear()+1900)+"-"+(date1.getMonth()+1)+"-"+date1.getDate();
        String dateStrTo = (date2.getYear()+1900)+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
        
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.subject "
                + "WHERE date BETWEEN '"+dateStrFrom+"' AND '"+dateStrTo+"' "
                + "AND department = '" + user+"'";
        ResultSet resultSet = null;
        ArrayList<Subject> subjectList = new ArrayList<Subject>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Subject sub = new Subject();
                sub.setSubject_id(resultSet.getInt("subject_id"));
                sub.setDate(resultSet.getDate("date"));
                sub.setCount(resultSet.getInt("count"));
                sub.setSubj(resultSet.getString("subject"));
                sub.setDepartment(resultSet.getString("department"));
                
                subjectList.add(sub);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return subjectList;
    }

    @Override
    public Subject getLast(String department) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.subject "
                + "WHERE DATE(date) = DATE(NOW()) "
                + "AND department = '"+department+"' "
                + "ORDER BY count";
        ResultSet resultSet = null;
        Subject sub = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                if(resultSet.isLast()){
                    sub = new Subject();
                    sub.setSubject_id(resultSet.getInt("subject_id"));
                    sub.setDate(resultSet.getDate("date"));
                    sub.setCount(resultSet.getInt("count"));
                    sub.setSubj(resultSet.getString("subject"));
                    sub.setDepartment(resultSet.getString("department"));
                }
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return sub;
    }
    
}
