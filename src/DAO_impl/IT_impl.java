/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.IT_interface;
import Model.Department;
import Model.IT;
import Model.Person;
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
public class IT_impl implements IT_interface {

    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public IT_impl(){
        this(null);
    }

    public IT_impl(Database manager){
        this.manager = manager;
    }
    
    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addIT(IT it) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.it_tbl VALUES(?,NOW(),?,?,?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, it.getControl_no());
            pStatement.setString(2, it.getEmployee().getEmp_id());
            pStatement.setDate(3, new java.sql.Date(it.getTravel_date_from().getYear(), 
                    it.getTravel_date_from().getMonth(), it.getTravel_date_from().getDate()));
            pStatement.setDate(4, new java.sql.Date(it.getTravel_date_to().getYear(), 
                    it.getTravel_date_to().getMonth(), it.getTravel_date_to().getDate()));
            pStatement.setString(5, it.getDistination());
            pStatement.setString(6, it.getRemarks());
            pStatement.setInt(7, it.getYear());
            pStatement.setInt(8, it.getSeq());

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
    public void updateIT(IT it) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.it_tbl SET emp_id=?, "
                + "travel_date_from=?, travel_date_to=?, distination=?, "
                + "remarks=? "
                + "WHERE control_no = '"+it.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, it.getEmployee().getEmp_id());
            pStatement.setDate(2, new java.sql.Date(it.getTravel_date_from().getYear(), 
                    it.getTravel_date_from().getMonth(), it.getTravel_date_from().getDate()));
            pStatement.setDate(3, new java.sql.Date(it.getTravel_date_to().getYear(), 
                    it.getTravel_date_to().getMonth(), it.getTravel_date_to().getDate()));
            pStatement.setString(4, it.getDistination());
            pStatement.setString(5, it.getRemarks());

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
    public void deleteIT(IT it) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.it_tbl "
                + "WHERE control_no = '"+it.getControl_no()+"'";
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
    public IT getIT_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.it_tbl o "
                + "INNER JOIN itmsirks.employee_tbl e ON o.emp_id = e.emp_id "
                + "INNER JOIN itmsirks.department_tbl d ON e.emp_department = d.dept_id "
                + "WHERE o.control_no = '"+cntrl_no+"'";
        ResultSet resultSet = null;
        IT it = null;

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
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
                
                it = new IT();
                it.setControl_no(resultSet.getString("control_no"));
                it.setDate_app(resultSet.getDate("date_app"));
                it.setEmployee(p);
                it.setDistination(resultSet.getString("distination"));
                it.setTravel_date_from(resultSet.getDate("travel_date_from"));
                it.setTravel_date_to(resultSet.getDate("travel_date_to"));
                it.setYear(resultSet.getInt("year"));
                it.setSeq(resultSet.getInt("seq"));
                it.setRemarks(resultSet.getString("remarks"));
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return it;
    }

    @Override
    public ArrayList<IT> getIT_applicant(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.it_tbl o "
                + "INNER JOIN itmsirks.employee_tbl e ON o.emp_id = e.emp_id "
                + "INNER JOIN itmsirks.department_tbl d ON e.emp_department = d.dept_id "
                + "WHERE o.emp_id = '"+emp_id+"' "
                + "ORDER BY o.control_no";
        ResultSet resultSet = null;
        ArrayList<IT> itList = new ArrayList<IT>();

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
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
                
                IT it = new IT();
                it.setControl_no(resultSet.getString("control_no"));
                it.setDate_app(resultSet.getDate("date_app"));
                it.setEmployee(p);
                it.setDistination(resultSet.getString("distination"));
                it.setTravel_date_from(resultSet.getDate("travel_date_from"));
                it.setTravel_date_to(resultSet.getDate("travel_date_to"));
                it.setYear(resultSet.getInt("year"));
                it.setSeq(resultSet.getInt("seq"));
                it.setRemarks(resultSet.getString("remarks"));
                
                itList.add(it);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return itList;
    }

    @Override
    public ArrayList<IT> getIT_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.it_tbl o "
                + "INNER JOIN itmsirks.employee_tbl e ON o.emp_id = e.emp_id "
                + "INNER JOIN itmsirks.department_tbl d ON e.emp_department = d.dept_id "
                + "WHERE o.date_app BETWEEN '"+date1+"' AND '"+date2+"' "
                + "ORDER BY o.control_no";
        ResultSet resultSet = null;
        ArrayList<IT> itList = new ArrayList<IT>();

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
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
                
                IT it = new IT();
                it.setControl_no(resultSet.getString("control_no"));
                it.setDate_app(resultSet.getDate("date_app"));
                it.setEmployee(p);
                it.setDistination(resultSet.getString("distination"));
                it.setTravel_date_from(resultSet.getDate("travel_date_from"));
                it.setTravel_date_to(resultSet.getDate("travel_date_to"));
                it.setYear(resultSet.getInt("year"));
                it.setSeq(resultSet.getInt("seq"));
                it.setRemarks(resultSet.getString("remarks"));
                
                itList.add(it);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return itList;
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.it_tbl "
                + "WHERE year = "+year+" "
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
}
