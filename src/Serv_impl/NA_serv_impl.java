/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.NA_impl;
import DAO_interface.NA_interface;
import Model.DaysAbsent;
import Model.NA;
import Serv_interface.NA_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class NA_serv_impl implements NA_serv_interface {
    
    private NA_interface na_var;

    public NA_serv_impl (){
        na_var = new NA_impl();
    }

    @Override
    public int getResponse() {
        return na_var.getResponse();
    }

    @Override
    public void addNA(NA na) throws ErrorException, FileNotFoundException, IOException {
        na_var.addNA(na);
    }

    @Override
    public void updateNA(NA na) throws ErrorException, FileNotFoundException, IOException {
        na_var.updateNA(na);
    }

    @Override
    public void deleteNA(NA na) throws ErrorException, FileNotFoundException, IOException {
        na_var.deleteNA(na);
    }

    @Override
    public NA getNA_control_no(String control_no) throws ErrorException, FileNotFoundException, IOException {
        return na_var.getNA_control_no(control_no);
    }

    @Override
    public ArrayList<NA> getNA_emp(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        return na_var.getNA_emp(emp_id);
    }

    @Override
    public ArrayList<NA> getNA_dates(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        return na_var.getNA_dates(date1, date2);
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        return na_var.getLastSeq(year);
    }

    @Override
    public void addDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException {
        na_var.addDaysAbsent(d);
    }

    @Override
    public void updateDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException {
        na_var.updateDaysAbsent(d);
    }

    @Override
    public void deleteDaysAbsent(DaysAbsent d) throws ErrorException, FileNotFoundException, IOException {
        na_var.deleteDaysAbsent(d);
    }

    @Override
    public ArrayList<DaysAbsent> getDaysAbsent_control_no(String control_no) throws ErrorException, FileNotFoundException, IOException {
        return na_var.getDaysAbsent_control_no(control_no);
    }
    
}
