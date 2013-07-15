/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Model.DTC;
import Model.DateCorrected;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface DTC_interface {
    
    public int getResponse();
    public void addDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException;
    public void updateDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException;
    public void deleteDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException;
    public DTC getDTC_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<DTC> getDTC_employee(String id) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<DTC> getDTC_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException;
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException;
    
    public void addDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException;
    public void updateDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException;
    public void deleteDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<DateCorrected> getDateCorrected_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<DateCorrected> getDateCorrected_emp_date(String emp_id, Date date) throws ErrorException, FileNotFoundException, IOException;
}
