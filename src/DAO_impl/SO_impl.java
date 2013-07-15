/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.SO_interface;
import Model.SO;
import Tool.DataDispatcher;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class SO_impl implements SO_interface {
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public SO_impl(){
        this(null);
    }

    public SO_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addSO(SO so) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.so_tbl VALUES(?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, so.getSo_no());
            pStatement.setString(2, so.getDescription());
            pStatement.setInt(3, so.getYear());
            pStatement.setInt(4, so.getSeq());
            pStatement.setString(5, so.getSubjet());

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
    public void editSO(SO so) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.so_tbl SET description=? "
                + "WHERE so_no = '"+so.getSo_no()+"' "
                + "AND subject = '"+so.getSubjet()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, so.getDescription());

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
    public void deleteSO(SO so) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.so_tbl "
                + "WHERE so_no = '"+so.getSo_no()+"' "
                + "AND subject = '"+so.getSubjet()+"'";
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
    public int getLastSeq(int year, String subject) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.so_tbl "
                + "WHERE year = "+year+" "
                + "AND subject = '"+subject+"' "
                + "ORDER BY seq";
        ResultSet resultSet = null;
        int last_seq = 0;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                if(resultSet.isLast()){
                    last_seq = resultSet.getInt("seq");
                }
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return last_seq;
    }

    @Override
    public SO getSO_no_subject(String so_no, String subject) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.so_tbl "
                + "WHERE so_no = '"+so_no+"' "
                + "AND subject = '"+subject+"'";
        ResultSet resultSet = null;
        SO so = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                so = new SO();
                so.setSo_no(resultSet.getString("so_no"));
                so.setDescription(resultSet.getString("description"));
                so.setYear(resultSet.getInt("year"));
                so.setSeq(resultSet.getInt("seq"));
                so.setSubject(resultSet.getString("subject"));
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return so;
    }

    @Override
    public ArrayList<SO> getSO_year_subject(int year, String subject) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.so_tbl "
                + "WHERE year = "+year+" "
                + "AND subject = '"+subject+"'";
        ResultSet resultSet = null;
        ArrayList<SO> soList = new ArrayList<SO>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                SO so = new SO();
                so.setSo_no(resultSet.getString("so_no"));
                so.setDescription(resultSet.getString("description"));
                so.setYear(resultSet.getInt("year"));
                so.setSeq(resultSet.getInt("seq"));
                so.setSubject(resultSet.getString("subject"));
                
                soList.add(so);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return soList;
    }
    
}
