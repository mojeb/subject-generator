/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.DTC_interface;
import DAO_interface.Database;
import Model.DTC;
import Model.DateCorrected;
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
 * @author mojeb
 */
public class DTC_impl implements DTC_interface {
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public DTC_impl(){
        this(null);
    }

    public DTC_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.dtc_tbl VALUES(?,NOW(),?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, dtc.getControl_no());
            pStatement.setString(2, dtc.getEmployee().getEmp_id());
            pStatement.setString(3, dtc.getReason());
            pStatement.setString(4, dtc.getRemarks());
            pStatement.setInt(5, dtc.getYear());
            pStatement.setInt(6, dtc.getSeq());

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
    public void updateDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.dtc_tbl SET emp_id=?, "
                + "reason=?, remarks=? "
                + "WHERE control_no = '"+dtc.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, dtc.getEmployee().getEmp_id());
            pStatement.setString(2, dtc.getReason());
            pStatement.setString(3, dtc.getRemarks());

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
    public void deleteDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.dtc_tbl "
                + "WHERE control_no = '"+dtc.getControl_no()+"'";
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
    public DTC getDTC_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.dtc_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON dtc_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE control_no = '"+cntrl_no+"'";
        ResultSet resultSet = null;
        DTC dtc = null;

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
                
                dtc = new DTC();
                dtc.setControl_no(resultSet.getString("control_no"));
                dtc.setDate_app(resultSet.getDate("date_app"));
                dtc.setEmployee(p);
                dtc.setYear(resultSet.getInt("year"));
                dtc.setSeq(resultSet.getInt("seq"));
                dtc.setReason(resultSet.getString("reason"));
                dtc.setRemarks(resultSet.getString("remarks"));
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dtc;
    }

    @Override
    public ArrayList<DTC> getDTC_employee(String id) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.dtc_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON dtc_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE employee_tbl.emp_id = '"+id+"'";
        ResultSet resultSet = null;
        ArrayList<DTC> dtcList = new ArrayList<DTC>();

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
                
                DTC dtc = new DTC();
                dtc.setControl_no(resultSet.getString("control_no"));
                dtc.setDate_app(resultSet.getDate("date_app"));
                dtc.setEmployee(p);
                dtc.setYear(resultSet.getInt("year"));
                dtc.setSeq(resultSet.getInt("seq"));
                dtc.setReason(resultSet.getString("reason"));
                dtc.setRemarks(resultSet.getString("remarks"));
                
                dtcList.add(dtc);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dtcList;
    }

    @Override
    public ArrayList<DTC> getDTC_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.dtc_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON dtc_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE date_app BETWEEN '"+date1+"' AND '"+date2+"' ";
        ResultSet resultSet = null;
        ArrayList<DTC> dtcList = new ArrayList<DTC>();

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
                
                DTC dtc = new DTC();
                dtc.setControl_no(resultSet.getString("control_no"));
                dtc.setDate_app(resultSet.getDate("date_app"));
                dtc.setEmployee(p);
                dtc.setYear(resultSet.getInt("year"));
                dtc.setSeq(resultSet.getInt("seq"));
                dtc.setReason(resultSet.getString("reason"));
                dtc.setRemarks(resultSet.getString("remarks"));
                
                dtcList.add(dtc);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dtcList;
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.dtc_tbl "
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
    public void addDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.date_corrected VALUES(?,?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, date_corr.getControl_no());
            pStatement.setString(2, date_corr.getIn_am());
            pStatement.setString(3, date_corr.getOut_am());
            pStatement.setString(4, date_corr.getIn_pm());
            pStatement.setString(5, date_corr.getOut_pm());
            pStatement.setDate(6, new java.sql.Date(date_corr.getDate().getYear(), 
                    date_corr.getDate().getMonth(), date_corr.getDate().getDate()));

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
    public void updateDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.date_corrected SET in_am=?, "
                + "out_am=?, in_pm=?, out_pm=?, date=? "
                + "WHERE control_no = '"+date_corr.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, date_corr.getIn_am());
            pStatement.setString(2, date_corr.getOut_am());
            pStatement.setString(3, date_corr.getIn_pm());
            pStatement.setString(4, date_corr.getOut_pm());
            pStatement.setDate(5, new java.sql.Date(date_corr.getDate().getYear(), 
                    date_corr.getDate().getMonth(), date_corr.getDate().getDate()));

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
    public void deleteDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.date_corrected "
                + "WHERE control_no = '"+date_corr.getControl_no()+"'";
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
    public ArrayList<DateCorrected> getDateCorrected_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.date_corrected "
                + "WHERE control_no = '"+cntrl_no+"'";
        ResultSet resultSet = null;
        ArrayList<DateCorrected> dateCorrectedList = new ArrayList<DateCorrected>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                DateCorrected date_corr = new DateCorrected();
                date_corr.setControl_no(resultSet.getString("control_no"));
                date_corr.setIn_am(resultSet.getString("in_am"));
                date_corr.setOut_am(resultSet.getString("out_am"));
                date_corr.setIn_pm(resultSet.getString("in_pm"));
                date_corr.setOut_pm(resultSet.getString("out_pm"));
                date_corr.setDate(resultSet.getDate("date"));
                
                
                dateCorrectedList.add(date_corr);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dateCorrectedList;
    }

    @Override
    public ArrayList<DateCorrected> getDateCorrected_emp_date(String emp_id, Date date) throws ErrorException, FileNotFoundException, IOException {
        String date1 = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+1;
        String date2 = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+31;
        
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.date_corrected "
                + "INNER JOIN sub_gen.dtc_tbl ON date_corrected.control_no = dtc_tbl.control_no "
                + "INNER JOIN itmsirks.employee_tbl ON dtc_tbl.emp_id = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE employee_tbl.emp_id = '"+emp_id+"' "
                + "AND date_app BETWEEN '"+date1+"' AND '"+date2+"'";
        ResultSet resultSet = null;
        ArrayList<DateCorrected> dateCorrectedList = new ArrayList<DateCorrected>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                DateCorrected date_corr = new DateCorrected();
                date_corr.setControl_no(resultSet.getString("control_no"));
                date_corr.setIn_am(resultSet.getString("in_am"));
                date_corr.setOut_am(resultSet.getString("out_am"));
                date_corr.setIn_pm(resultSet.getString("in_pm"));
                date_corr.setOut_pm(resultSet.getString("out_pm"));
                date_corr.setDate(resultSet.getDate("date"));
                
                
                dateCorrectedList.add(date_corr);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return dateCorrectedList;
    }
    
}
