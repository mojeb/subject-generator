/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.OBS_impl;
import DAO_interface.OBS_interface;
import Model.OBS;
import Model.OBS_employee;
import Serv_interface.OBS_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class OBS_serv_impl implements OBS_serv_interface {

    private OBS_interface obs_var;

    public OBS_serv_impl (){
        obs_var = new OBS_impl();
    }
    
    @Override
    public int getResponse() {
        return obs_var.getResponse();
    }

    @Override
    public void addOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException {
        obs_var.addOBS(obs);
    }

    @Override
    public void updateOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException {
        obs_var.updateOBS(obs);
    }

    @Override
    public void deleteOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException {
        obs_var.deleteOBS(obs);
    }

    @Override
    public OBS getOBS_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        return obs_var.getOBS_control_no(cntrl_no);
    }

//    @Override
//    public ArrayList<OBS> getOBS_applicant(String id) throws ErrorException, FileNotFoundException, IOException {
//        return obs_var.getOBS_applicant(id);
//    }

    @Override
    public ArrayList<OBS> getOBS_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        return obs_var.getOBS_date_app(date1, date2);
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        return obs_var.getLastSeq(year);
    }

    @Override
    public void addOBS_employee(OBS_employee emp) throws ErrorException, FileNotFoundException, IOException {
        obs_var.addOBS_employee(emp);
    }

    @Override
    public void deleteOBS_employee(OBS_employee emp) throws ErrorException, FileNotFoundException, IOException {
        obs_var.deleteOBS_employee(emp);
    }

    @Override
    public ArrayList<OBS_employee> getOBS_employee(String control_no) throws ErrorException, FileNotFoundException, IOException {
        return obs_var.getOBS_employee(control_no);
    }

    @Override
    public ArrayList<OBS> getOBS_employee_id(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        return obs_var.getOBS_employee_id(emp_id);
    }
    
}
