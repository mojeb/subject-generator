/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.Library_interface;
import Model.Department;
import Model.SystemClass;
import Model.SystemUser;
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
public class Library_impl implements Library_interface {

    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Library_impl(){
        this(null);
    }

    public Library_impl(Database manager){
        this.manager = manager;
    }
    
    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.system_tbl VALUES(?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, 0);
            pStatement.setString(2, sys.getSystem_code());
            pStatement.setString(3, sys.getSystem_name());

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
    public void updateSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.system_tbl SET system_code=?, system_name=? "
                + "WHERE system_id = "+sys.getSystem_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, sys.getSystem_code());
            pStatement.setString(2, sys.getSystem_name());

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
    public void deleteSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.system_tbl "
                + "WHERE system_id = "+sys.getSystem_id();
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
    public SystemClass getSystem_code(String code) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.system_tbl "
                + "WHERE system_code = '"+code+"'";
        ResultSet resultSet = null;
        SystemClass sys = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                sys = new SystemClass();
                sys.setSystem_id(resultSet.getInt("system_id"));
                sys.setSystem_code(resultSet.getString("system_code"));
                sys.setSystem_name(resultSet.getString("system_name"));
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return sys;
    }

    @Override
    public ArrayList<SystemClass> getSystem_all() throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.system_tbl";
        ResultSet resultSet = null;
        ArrayList<SystemClass> sysList = new ArrayList<SystemClass>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                SystemClass sys = new SystemClass();
                sys.setSystem_id(resultSet.getInt("system_id"));
                sys.setSystem_code(resultSet.getString("system_code"));
                sys.setSystem_name(resultSet.getString("system_name"));
                
                sysList.add(sys);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return sysList;
    }

    @Override
    public void addSystemUser(SystemUser user) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.system_user VALUES(?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, 0);
            pStatement.setInt(2, user.getDept().getDept_id());

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
    public void deleteSystemUser(SystemUser user) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.system_user "
                + "WHERE system_user_id = "+user.getSystem_user_id();
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
    public SystemUser getSystemUser_id(int id) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.system_user "
                + "INNER JOIN itmsirks.department_tbl ON system_user.dept_id = department_tbl.dept_id "
                + "WHERE system_user_id = "+id;
        ResultSet resultSet = null;
        SystemUser user = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                user = new SystemUser();
                user.setSystem_user_id(resultSet.getInt("system_user_id"));
                user.setDept(d);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return user;
    }

    @Override
    public ArrayList<SystemUser> getSystemUser_all() throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.system_user "
                + "INNER JOIN itmsirks.department_tbl ON system_user.dept_id = department_tbl.dept_id "
                + "ORDER BY dept_name";
        ResultSet resultSet = null;
        ArrayList<SystemUser> userList = new ArrayList<SystemUser>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                SystemUser user = new SystemUser();
                user.setSystem_user_id(resultSet.getInt("system_user_id"));
                user.setDept(d);
                
                userList.add(user);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return userList;
    }

    @Override
    public SystemUser getSystemUser_dept(String dept) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.system_user "
                + "INNER JOIN itmsirks.department_tbl ON system_user.dept_id = department_tbl.dept_id "
                + "WHERE dept_code = '"+dept+"'";
        ResultSet resultSet = null;
        SystemUser user = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                user = new SystemUser();
                user.setSystem_user_id(resultSet.getInt("system_user_id"));
                user.setDept(d);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return user;
    }
    
}
