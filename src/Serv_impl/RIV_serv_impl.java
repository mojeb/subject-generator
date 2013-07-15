/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.RIV_impl;
import DAO_interface.RIV_interface;
import Model.RIV;
import Serv_interface.RIV_serv_interface;
import Tool.ErrorException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Rommel
 */
public class RIV_serv_impl implements RIV_serv_interface {
    
    private RIV_interface riv_var;

    public RIV_serv_impl (){
        riv_var = new RIV_impl();
    }

    @Override
    public int getResponse() {
        return riv_var.getResponse();
    }

    @Override
    public void addRIV(RIV riv) throws ErrorException {
        riv_var.addRIV(riv);
    }

    @Override
    public void editRIV(RIV riv) throws ErrorException {
        riv_var.editRIV(riv);
    }

    @Override
    public void deleteRIV(RIV riv) throws ErrorException {
        riv_var.deleteRIV(riv);
    }

    @Override
    public RIV getRIV_control_no(String control_no) throws ErrorException {
        return riv_var.getRIV_control_no(control_no);
    }

    @Override
    public ArrayList<RIV> getRIV_month(Date date, int year) throws ErrorException {
        return riv_var.getRIV_month(date, year);
    }

    @Override
    public int getLastSeq(int year) throws ErrorException {
        return riv_var.getLastSeq(year);
    }

    @Override
    public ArrayList<RIV> getRIV_emp_id(String emp_id) throws ErrorException {
        return riv_var.getRIV_emp_id(emp_id);
    }
    
}
