/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_interface;

import Model.IT;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface IT_serv_interface {
    
    public int getResponse();
    public void addIT(IT it) throws ErrorException, FileNotFoundException, IOException;
    public void updateIT(IT it) throws ErrorException, FileNotFoundException, IOException;
    public void deleteIT(IT it) throws ErrorException, FileNotFoundException, IOException;
    public IT getIT_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<IT> getIT_applicant(String emp_id) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<IT> getIT_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException;
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException;
}
