/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.Department_interface;
import Model.Department;
import Model.Person;
import Tool.DataDispatcher;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public class Department_impl implements Department_interface{

    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Department_impl(){
        this(null);
    }

    public Department_impl(Database manager){
        this.manager = manager;
    }
    
    @Override
    public int getResponce() {
        return response;
    }

    @Override
    public void addDepartment(Department d) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO itmsirks.department_tbl VALUES(?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, 0);
            pStatement.setString(2, d.getDept_code());
            pStatement.setString(3, d.getDept_name());
            pStatement.setString(4, d.getPerson().getEmp_id());

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
    public void updateDepartment(Department d) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE itmsirks.department_tbl "
                + "SET dept_code=?, dept_name=?, dept_head=? "
                + "WHERE dept_id = "+d.getDept_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, d.getDept_code());
            pStatement.setString(2, d.getDept_name());
            pStatement.setString(3, d.getPerson().getEmp_id());

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
    public void deleteDepartment(Department d) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM itmsirks.department_tbl "
                + "WHERE dept_id = "+d.getDept_id();
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
    public ArrayList<Department> getAllDepartment() throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM itmsirks.department_tbl "
                + "ORDER BY dept_code";
        ResultSet resultSet = null;
        ArrayList<Department> dList = new ArrayList<Department>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Person p = new Person();
                p.setEmp_id(resultSet.getString("dept_head"));
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                d.setPerson(p);
                
                dList.add(d);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dList;
    }

    @Override
    public ArrayList<Department> getDepartmentCode(String code) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM itmsirks.department_tbl "
                + "WHERE dept_code = '"+code+"'";
        ResultSet resultSet = null;
        ArrayList<Department> dList = new ArrayList<Department>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Person p = new Person();
                p.setEmp_id(resultSet.getString("dept_head"));
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                d.setPerson(p);
                
                dList.add(d);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dList;
    }

    @Override
    public ArrayList<Department> getDepartmentName(String name) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM itmsirks.department_tbl "
                + "WHERE dept_name = '"+name+"'";
        ResultSet resultSet = null;
        ArrayList<Department> dList = new ArrayList<Department>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Person p = new Person();
                p.setEmp_id(resultSet.getString("dept_head"));
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                d.setPerson(p);
                
                dList.add(d);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dList;
    }

    @Override
    public Department getDepartmentID(int id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM itmsirks.department_tbl "
                + "WHERE dept_id = "+id;
        ResultSet resultSet = null;
        Department d = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("dept_head"));
                
                d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                d.setPerson(p);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return d;
    }

    @Override
    public Department getDepartmentbyCode(String code) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM itmsirks.department_tbl "
                + "WHERE dept_code = '"+code+"'";
        ResultSet resultSet = null;
        Department d = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("dept_head"));
                
                d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                d.setPerson(p);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return d;
    }

    @Override
    public Department getDepartmentbyName(String name) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM itmsirks.department_tbl "
                + "WHERE dept_name = '"+name+"'";
        ResultSet resultSet = null;
        Department d = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("dept_head"));
                
                d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                d.setPerson(p);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return d;
    }
    
}
