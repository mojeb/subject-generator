/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Model.DaysAbsent;
import Model.NA;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface NA_interface {
    
    public int getResponse();
    public void addNA(NA na) throws ErrorException, FileNotFoundException, IOException;
    public void updateNA(NA na) throws ErrorException, FileNotFoundException, IOException;
    public void deleteNA(NA na) throws ErrorException, FileNotFoundException, IOException;
    public NA getNA_control_no(String control_no) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<NA> getNA_emp(String emp_id) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<NA> getNA_dates(String date1, String date2) throws ErrorException, FileNotFoundException, IOException;
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException;
    
    //days absent
    public void addDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException;
    public void updateDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException;
    public void deleteDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<DaysAbsent> getDaysAbsent_control_no(String control_no) throws ErrorException, FileNotFoundException, IOException;
}
