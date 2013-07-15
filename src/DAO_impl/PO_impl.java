/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database;
import DAO_interface.PO_interface;
import Model.PO;
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
public class PO_impl implements PO_interface {
    
    private Database manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public PO_impl(){
        this(null);
    }
    
    public PO_impl(Database manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addPO(PO po) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO sub_gen.po_tbl VALUES(?,NOW(),?,?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, po.getControl_no());
            pStatement.setString(2, po.getSupplier());
            pStatement.setString(3, po.getRiv().getControl_no());
            pStatement.setDouble(4, po.getTotal_amount());
            pStatement.setString(5, po.getRemarks());
            pStatement.setInt(6, po.getYear());
            pStatement.setInt(7, po.getSeq());

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
    public void editPO(PO po) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE sub_gen.po_tbl SET "
                + "supplier=?, riv_no=?, total_amount=?, remarks=? "
                + "WHERE control_no = '"+po.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setString(1, po.getSupplier());
            pStatement.setString(2, po.getRiv().getControl_no());
            pStatement.setDouble(3, po.getTotal_amount());
            pStatement.setString(4, po.getRemarks());

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
    public void deletePO(PO po) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM sub_gen.po_tbl "
                + "WHERE control_no = '"+po.getControl_no()+"'";
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
    public PO getPO_control_no(String control_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.po_tbl "
                + "INNER JOIN sub_gen.riv_tbl ON po_tbl.riv_no = riv_tbl.control_no "
                + "WHERE po_tbl.control_no = '"+control_no+"'";
        ResultSet resultSet = null;
        PO po = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                RIV riv = new RIV();
                riv.setControl_no(resultSet.getString("riv_no"));
                riv.setDescription(resultSet.getString("description"));
                
                po = new PO();
                po.setControl_no(resultSet.getString("control_no"));
                po.setDate(resultSet.getDate("date"));
                po.setSupplier(resultSet.getString("supplier"));
                po.setRiv(riv);
                po.setTotal_amount(resultSet.getDouble("total_amount"));
                po.setRemarks(resultSet.getString("remarks"));
                po.setYear(resultSet.getInt("year"));
                po.setSeq(resultSet.getInt("seq"));
            }
            
        }catch (SQLException ex) {
        }catch(FileNotFoundException ex){
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return po;
    }

    @Override
    public ArrayList<PO> getPO_month(Date date, int year) throws ErrorException {
        String date1 = date.getYear()+"-"+date.getMonth()+"-"+1;
        String date2 = date.getYear()+"-"+date.getMonth()+"-"+31;
        
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM sub_gen.po_tbl "
                + "INNER JOIN sub_gen.riv_tbl ON po_tbl.riv_no = riv_tbl.control_no "
                + "WHERE po_tbl.date BETWEEN  '"+date1+"' AND '"+date2+"' "
                + "AND po_tbl.year = "+year;
        ResultSet resultSet = null;
        ArrayList<PO> poList = new ArrayList<PO>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                RIV riv = new RIV();
                riv.setControl_no(resultSet.getString("riv_no"));
                riv.setDescription(resultSet.getString("description"));
                
                PO po = new PO();
                po.setControl_no(resultSet.getString("control_no"));
                po.setDate(resultSet.getDate("date"));
                po.setSupplier(resultSet.getString("supplier"));
                po.setRiv(riv);
                po.setTotal_amount(resultSet.getDouble("total_amount"));
                po.setRemarks(resultSet.getString("remarks"));
                po.setYear(resultSet.getInt("year"));
                po.setSeq(resultSet.getInt("seq"));
                
                poList.add(po);
            }
            
        }catch (SQLException ex) {
        }catch(FileNotFoundException ex){
        }catch(IOException ex){
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return poList;
    }

    @Override
    public int getLastSeq(int year) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM sub_gen.po_tbl "
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
    
}
