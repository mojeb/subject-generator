/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_interface;

import Model.OBS;
import Model.OBS_employee;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface OBS_serv_interface {
    
    public int getResponse();
    public void addOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException;
    public void updateOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException;
    public void deleteOBS(OBS obs) throws ErrorException, FileNotFoundException, IOException;
    public OBS getOBS_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<OBS> getOBS_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<OBS> getOBS_employee_id(String emp_id) throws ErrorException, FileNotFoundException, IOException;
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException;
    
    public void addOBS_employee(OBS_employee emp) throws ErrorException, FileNotFoundException, IOException;
    public void deleteOBS_employee(OBS_employee emp) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<OBS_employee> getOBS_employee(String control_no) throws ErrorException, FileNotFoundException, IOException;
}
