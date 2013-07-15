/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_interface;

import Model.RIV;
import Tool.ErrorException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Rommel
 */
public interface RIV_serv_interface {
    
    public int getResponse();
    public void addRIV(RIV riv) throws ErrorException;
    public void editRIV(RIV riv) throws ErrorException;
    public void deleteRIV(RIV riv) throws ErrorException;
    public RIV getRIV_control_no(String control_no) throws ErrorException;
    public ArrayList<RIV> getRIV_month(Date date, int year) throws ErrorException;
    public ArrayList<RIV> getRIV_emp_id(String emp_id) throws ErrorException;
    public int getLastSeq(int year) throws ErrorException;
}
