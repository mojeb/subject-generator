/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.NA_interface;
import Model.DaysAbsent;
import Model.Department;
import Model.NA;
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
public class NA_impl implements NA_interface {
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public NA_impl(){
        this(null);
    }

    public NA_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addNA(NA na) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.na_tbl VALUES(?,NOW(),?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, na.getControl_no());
            pStatement.setString(2, na.getEmp().getEmp_id());
            pStatement.setString(3, na.getNature());
            pStatement.setString(4, na.getRemarks());
            pStatement.setInt(5, na.getYear());
            pStatement.setInt(6, na.getSeq());

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
    public void updateNA(NA na) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.na_tbl SET emp=?, "
                + "nature=?, remarks=? "
                + "WHERE control_no = '"+na.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, na.getEmp().getEmp_id());
            pStatement.setString(2, na.getNature());
            pStatement.setString(3, na.getRemarks());

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
    public void deleteNA(NA na) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.na_tbl "
                + "WHERE control_no = '"+na.getControl_no()+"'";
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
    public NA getNA_control_no(String control_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.na_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON na_tbl.emp = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE control_no = '"+control_no+"'";
        ResultSet resultSet = null;
        NA na = null;

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
                
                na = new NA();
                na.setControl_no(resultSet.getString("control_no"));
                na.setDate_app(resultSet.getDate("date_app"));
                na.setEmp(p);
                na.setNature(resultSet.getString("nature"));
                na.setRemarks(resultSet.getString("remarks"));
                na.setYear(resultSet.getInt("year"));
                na.setSeq(resultSet.getInt("seq"));
            }
            
        }catch (SQLException ex) {
        }finally{
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return na;
    }

    @Override
    public ArrayList<NA> getNA_emp(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.na_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON na_tbl.emp = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE emp = '"+emp_id+"'";
        ResultSet resultSet = null;
        ArrayList<NA> naList = new ArrayList<NA>();

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
                
                NA na = new NA();
                na.setControl_no(resultSet.getString("control_no"));
                na.setDate_app(resultSet.getDate("date_app"));
                na.setEmp(p);
                na.setNature(resultSet.getString("nature"));
                na.setRemarks(resultSet.getString("remarks"));
                na.setYear(resultSet.getInt("year"));
                na.setSeq(resultSet.getInt("seq"));
                
                naList.add(na);
            }
            
        }catch (SQLException ex) {
        }finally{
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return naList;
    }

    @Override
    public ArrayList<NA> getNA_dates(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.na_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON na_tbl.emp = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE date_app BETWEEN '"+date1+"' AND '"+date2+"'";
        ResultSet resultSet = null;
        ArrayList<NA> naList = new ArrayList<NA>();

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
                
                NA na = new NA();
                na.setControl_no(resultSet.getString("control_no"));
                na.setDate_app(resultSet.getDate("date_app"));
                na.setEmp(p);
                na.setNature(resultSet.getString("nature"));
                na.setRemarks(resultSet.getString("remarks"));
                na.setYear(resultSet.getInt("year"));
                na.setSeq(resultSet.getInt("seq"));
                
                naList.add(na);
            }
            
        }catch (SQLException ex) {
        }finally{
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return naList;
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.na_tbl "
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

    @Override
    public void addDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.days_absent_tbl VALUES(?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, d.getControl_no());
            pStatement.setString(2, d.getStr_date());

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
    public void updateDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.days_absent_tbl SET str_date=? "
                + "WHERE control_no = '"+d.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, d.getStr_date());

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
    public void deleteDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.days_absent_tbl "
                + "WHERE control_no = '"+d.getControl_no()+"'";
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
    public ArrayList<DaysAbsent> getDaysAbsent_control_no(String control_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.days_absent_tbl "
                + "WHERE control_no = '"+control_no+"'";
        ResultSet resultSet = null;
        ArrayList<DaysAbsent> dList = new ArrayList<DaysAbsent>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                DaysAbsent d = new DaysAbsent();
                d.setControl_no(resultSet.getString("control_no"));
                d.setStr_date(resultSet.getString("str_date"));
                
                dList.add(d);
            }
            
        }catch (SQLException ex) {
        }finally{
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dList;
    }
    
}
