/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.RIV_interface;
import Model.Department;
import Model.Person;
import Model.RIV;
import Tool.DataDispatcher;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Rommel
 */
public class RIV_impl implements RIV_interface {
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public RIV_impl(){
        this(null);
    }

    public RIV_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addRIV(RIV riv) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.riv_tbl VALUES(?,NOW(),?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, riv.getControl_no());
            pStatement.setString(2, riv.getEmp().getEmp_id());
            pStatement.setString(3, riv.getDescription());
            pStatement.setString(4, riv.getRemarks());
            pStatement.setInt(5, riv.getYear());
            pStatement.setInt(6, riv.getSeq());

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
    public void editRIV(RIV riv) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.riv_tbl SET "
                + "emp_id=?, description=?, remarks=? "
                + "WHERE control_no = '"+riv.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, riv.getEmp().getEmp_id());
            pStatement.setString(2, riv.getDescription());
            pStatement.setString(3, riv.getRemarks());

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
    public void deleteRIV(RIV riv) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.riv_tbl "
                + "WHERE control_no = '"+riv.getControl_no()+"'";
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
    public RIV getRIV_control_no(String control_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.riv_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON riv_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE control_no = '"+control_no+"'";
        ResultSet resultSet = null;
        RIV riv = null;

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
                
                riv = new RIV();
                riv.setControl_no(resultSet.getString("control_no"));
                riv.setDate(resultSet.getDate("date"));
                riv.setEmp(p);
                riv.setDescription(resultSet.getString("description"));
                riv.setRemarks(resultSet.getString("remarks"));
                riv.setYear(resultSet.getInt("year"));
                riv.setSeq(resultSet.getInt("seq"));
            }
            
        }catch (SQLException ex) {
        }catch(FileNotFoundException ex){
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return riv;
    }

    @Override
    public ArrayList<RIV> getRIV_month(Date date, int year) throws ErrorException {
        String date1 = date.getYear()+"-"+date.getMonth()+"-"+1;
        String date2 = date.getYear()+"-"+date.getMonth()+"-"+31;
        
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.riv_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON riv_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE date BETWEEN  '"+date1+"' AND '"+date2+"' "
                + "AND year = "+year;
        ResultSet resultSet = null;
        ArrayList<RIV> rivList = new ArrayList<RIV>();

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
                
                RIV riv = new RIV();
                riv.setControl_no(resultSet.getString("control_no"));
                riv.setDate(resultSet.getDate("date"));
                riv.setEmp(p);
                riv.setDescription(resultSet.getString("description"));
                riv.setRemarks(resultSet.getString("remarks"));
                riv.setYear(resultSet.getInt("year"));
                riv.setSeq(resultSet.getInt("seq"));
                
                rivList.add(riv);
            }
            
        }catch (SQLException ex) {
        }catch(FileNotFoundException ex){
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return rivList;
    }

    @Override
    public int getLastSeq(int year) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.riv_tbl "
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
        }catch(FileNotFoundException ex){
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return last_seq;
    }

    @Override
    public ArrayList<RIV> getRIV_emp_id(String emp_id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.riv_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON riv_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE riv_tbl.emp_id = '"+emp_id+"'";
        ResultSet resultSet = null;
        ArrayList<RIV> rivList = new ArrayList<RIV>();

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
                
                RIV riv = new RIV();
                riv.setControl_no(resultSet.getString("control_no"));
                riv.setDate(resultSet.getDate("date"));
                riv.setEmp(p);
                riv.setDescription(resultSet.getString("description"));
                riv.setRemarks(resultSet.getString("remarks"));
                riv.setYear(resultSet.getInt("year"));
                riv.setSeq(resultSet.getInt("seq"));
                
                rivList.add(riv);
            }
            
        }catch (SQLException ex) {
        }catch(FileNotFoundException ex){
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return rivList;
    }
    
}
