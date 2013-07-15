/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.Seq_interface;
import Model.Department;
import Model.Person;
import Model.SeqNo;
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
public class Seq_impl implements Seq_interface {
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Seq_impl(){
        this(null);
    }

    public Seq_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.seq_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, 0);
            pStatement.setString(2, seq.getSeq_no());
            pStatement.setString(3, seq.getCategory());
            pStatement.setString(4, seq.getModel());
            pStatement.setString(5, seq.getSerial());
            pStatement.setString(6, seq.getPo());
            pStatement.setString(7, seq.getRiv());
            pStatement.setString(8, seq.getDate_purchase());
            pStatement.setString(9, seq.getEmp().getEmp_id());
            pStatement.setInt(10, seq.getYear());
            pStatement.setInt(11, seq.getSeq());

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
    public void editSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.seq_tbl SET category=?, "
                + "model=?, serial=?, po=?, riv=?, date_purchase=?, emp=? "
                + "WHERE seq_id = "+seq.getSeq_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, seq.getCategory());
            pStatement.setString(2, seq.getModel());
            pStatement.setString(3, seq.getSerial());
            pStatement.setString(4, seq.getPo());
            pStatement.setString(5, seq.getRiv());
            pStatement.setString(6, seq.getDate_purchase());
            pStatement.setString(7, seq.getEmp().getEmp_id());

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
    public void deleteSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.seq_tbl "
                + "WHERE seq_no = '"+seq.getSeq_no()+"'";
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
    public ArrayList<SeqNo> getSeq_no(String seq_no) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.seq_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON seq_tbl.emp = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE seq_no = '"+seq_no+"'";
        ResultSet resultSet = null;
        ArrayList<SeqNo> seqList = new ArrayList<SeqNo>();

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
                
                SeqNo seq = new SeqNo();
                seq.setSeq_id(resultSet.getInt("seq_id"));
                seq.setSeq_no(resultSet.getString("seq_no"));
                seq.setCategory(resultSet.getString("category"));
                seq.setModel(resultSet.getString("model"));
                seq.setSerial(resultSet.getString("serial"));
                seq.setPo(resultSet.getString("po"));
                seq.setRiv(resultSet.getString("riv"));
                seq.setDate_purchase(resultSet.getString("date_purchase"));
                seq.setEmp(p);
                seq.setYear(resultSet.getInt("year"));
                seq.setSeq(resultSet.getInt("seq"));
                
                seqList.add(seq);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return seqList;
    }

    @Override
    public ArrayList<SeqNo> getSeq_emp(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.seq_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON seq_tbl.emp = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE seq_tbl.emp = '"+emp_id+"'";
        ResultSet resultSet = null;
        ArrayList<SeqNo> seqList = new ArrayList<SeqNo>();

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
                
                SeqNo seq = new SeqNo();
                seq.setSeq_id(resultSet.getInt("seq_id"));
                seq.setSeq_no(resultSet.getString("seq_no"));
                seq.setCategory(resultSet.getString("category"));
                seq.setModel(resultSet.getString("model"));
                seq.setSerial(resultSet.getString("serial"));
                seq.setPo(resultSet.getString("po"));
                seq.setRiv(resultSet.getString("riv"));
                seq.setDate_purchase(resultSet.getString("date_purchase"));
                seq.setEmp(p);
                seq.setYear(resultSet.getInt("year"));
                seq.setSeq(resultSet.getInt("seq"));
                
                seqList.add(seq);
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return seqList;
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.seq_tbl "
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
    public SeqNo getSeq_seq_id(int seq_id) throws ErrorException, FileNotFoundException, IOException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.seq_tbl "
                + "INNER JOIN itmsirks.employee_tbl ON seq_tbl.emp = employee_tbl.emp_id "
                + "INNER JOIN itmsirks.department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE seq_id = "+seq_id;
        ResultSet resultSet = null;
        SeqNo seq = null;

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
                
                seq = new SeqNo();
                seq.setSeq_id(resultSet.getInt("seq_id"));
                seq.setSeq_no(resultSet.getString("seq_no"));
                seq.setCategory(resultSet.getString("category"));
                seq.setModel(resultSet.getString("model"));
                seq.setSerial(resultSet.getString("serial"));
                seq.setPo(resultSet.getString("po"));
                seq.setRiv(resultSet.getString("riv"));
                seq.setDate_purchase(resultSet.getString("date_purchase"));
                seq.setEmp(p);
                seq.setYear(resultSet.getInt("year"));
                seq.setSeq(resultSet.getInt("seq"));
            }
            
        }catch (SQLException ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return seq;
    }
    
}
