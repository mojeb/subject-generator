/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.OBS_interface;
import Model.Department;
import Model.OBS;
import Model.OBS_employee;
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
public class OBS_impl implements OBS_interface {
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public OBS_impl(){
        this(null);
    }

    public OBS_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.obs_tbl VALUES(?,NOW(),?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, obs.getControl_no());
            pStatement.setDate(2, new java.sql.Date(obs.getDate_travel().getYear(), 
                    obs.getDate_travel().getMonth(), obs.getDate_travel().getDate()));
            pStatement.setString(3, obs.getDistination());
            pStatement.setInt(4, obs.getYear());
            pStatement.setInt(5, obs.getSeq());
            pStatement.setString(6, obs.getRemarks());

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
    public void updateOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.obs_tbl SET "
                + "date_travel=?, distination=?, remarks=? "
                + "WHERE control_no = '"+obs.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setDate(1, new java.sql.Date(obs.getDate_travel().getYear(), 
                    obs.getDate_travel().getMonth(), obs.getDate_travel().getDate()));
            pStatement.setString(2, obs.getDistination());
            pStatement.setString(3, obs.getRemarks());

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
    public void deleteOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.obs_tbl "
                + "WHERE control_no = '"+obs.getControl_no()+"'";
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
    public OBS getOBS_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.obs_tbl "
                + "WHERE control_no = '"+cntrl_no+"'";
        ResultSet resultSet = null;
        OBS obs = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                obs = new OBS();
                obs.setControl_no(resultSet.getString("control_no"));
                obs.setDate_app(resultSet.getDate("date_app"));
                obs.setDistination(resultSet.getString("distination"));
                obs.setDate_travel(resultSet.getDate("date_travel"));
                obs.setYear(resultSet.getInt("year"));
                obs.setSeq(resultSet.getInt("seq"));
                obs.setRemarks(resultSet.getString("remarks"));
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return obs;
    }

    @Override
    public ArrayList<OBS> getOBS_employee_id(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.obs_employee_tbl "
                + "INNER JOIN sub_gen.obs_tbl ON obs_tbl.control_no = obs_employee_tbl.control_no "
                + "INNER JOIN itmsirks.employee_tbl ON obs_employee_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE obs_employee_tbl.emp_id = '"+emp_id+"'";
        ResultSet resultSet = null;
        ArrayList<OBS> obsList = new ArrayList<OBS>();

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
                
                OBS obs = new OBS();
                obs.setControl_no(resultSet.getString("control_no"));
                obs.setDate_app(resultSet.getDate("date_app"));
                obs.setDistination(resultSet.getString("distination"));
                obs.setDate_travel(resultSet.getDate("date_travel"));
                obs.setYear(resultSet.getInt("year"));
                obs.setSeq(resultSet.getInt("seq"));
                obs.setRemarks(resultSet.getString("remarks"));
                
                obsList.add(obs);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return obsList;
    }

    @Override
    public ArrayList<OBS> getOBS_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.obs_tbl "
                + "WHERE date_app BETWEEN '"+date1+"' AND '"+date2+"' ";
        ResultSet resultSet = null;
        ArrayList<OBS> obsList = new ArrayList<OBS>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                OBS obs = new OBS();
                obs.setControl_no(resultSet.getString("control_no"));
                obs.setDate_app(resultSet.getDate("date_app"));
                obs.setDistination(resultSet.getString("distination"));
                obs.setDate_travel(resultSet.getDate("date_travel"));
                obs.setYear(resultSet.getInt("year"));
                obs.setSeq(resultSet.getInt("seq"));
                obs.setRemarks(resultSet.getString("remarks"));
                
                obsList.add(obs);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return obsList;
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.obs_tbl "
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
    public void addOBS_employee(OBS_employee emp) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.obs_employee_tbl VALUES(?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, emp.getControl_no());
            pStatement.setString(2, emp.getP().getEmp_id());

            if (pStatement.executeUpdate() > 0) {
                response = SUCCESS;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, pStatement, connection);
        }
    }

//    @Override
//    public void editOBS_employee(OBS_employee emp) throws ErrorException, FileNotFoundException, IOException {
//        response = FAILED;
//        Connection connection = null;
//        PreparedStatement pStatement = null;
//        String sql = "UPDATE sub_gen.obs_employee_tbl SET emp_id=? "
//                + "WHERE control_no = '"+obs.getControl_no()+"'";
//        ResultSet resultSet = null;
//
//        try {
//
//            if (manager == null) {
//                manager = Database_impl.getInstance();
//
//            }
//
//            connection = manager.getConnection();
//            pStatement = connection.prepareStatement(sql);
//
//            pStatement.setDate(1, new java.sql.Date(obs.getDate_travel().getYear(), 
//                    obs.getDate_travel().getMonth(), obs.getDate_travel().getDate()));
//            pStatement.setString(2, obs.getDistination());
//            pStatement.setString(3, obs.getRemarks());
//
//            if (pStatement.executeUpdate() > 0) {
//                response = SUCCESS;
//            }
//
//        } catch (SQLException ex) {
//        } catch (Exception ex) {
//        } finally {
//            DataDispatcher.dispatch(resultSet, pStatement, connection);
//        }
//    }

    @Override
    public void deleteOBS_employee(OBS_employee emp) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.obs_employee_tbl "
                + "WHERE control_no = '"+emp.getControl_no()+"'";
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
    public ArrayList<OBS_employee> getOBS_employee(String control_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.obs_employee_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON obs_employee_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE obs_employee_tbl.control_no = '"+control_no+"'";
        ResultSet resultSet = null;
        ArrayList<OBS_employee> obsList = new ArrayList<OBS_employee>();

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
                
                OBS_employee obs = new OBS_employee();
                obs.setControl_no(resultSet.getString("control_no"));
                obs.setP(p);
                
                obsList.add(obs);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return obsList;
    }
    
}
