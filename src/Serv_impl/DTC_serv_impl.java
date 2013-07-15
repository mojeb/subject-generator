/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.DTC_impl;
import DAO_interface.DTC_interface;
import Model.DTC;
import Model.DateCorrected;
import Serv_interface.DTC_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class DTC_serv_impl implements DTC_serv_interface {

    private DTC_interface dtc_var;

    public DTC_serv_impl (){
        dtc_var = new DTC_impl();
    }
    
    @Override
    public int getResponse() {
        return dtc_var.getResponse();
    }

    @Override
    public void addDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException {
        dtc_var.addDTC(dtc);
    }

    @Override
    public void updateDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException {
        dtc_var.updateDTC(dtc);
    }

    @Override
    public void deleteDTC(DTC dtc) throws ErrorException, FileNotFoundException, IOException {
        dtc_var.deleteDTC(dtc);
    }

    @Override
    public DTC getDTC_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        return dtc_var.getDTC_control_no(cntrl_no);
    }

    @Override
    public ArrayList<DTC> getDTC_employee(String id) throws ErrorException, FileNotFoundException, IOException {
        return dtc_var.getDTC_employee(id);
    }

    @Override
    public ArrayList<DTC> getDTC_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        return dtc_var.getDTC_date_app(date1, date2);
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        return dtc_var.getLastSeq(year);
    }

    @Override
    public void addDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException {
        dtc_var.addDateCorrected(date_corr);
    }

    @Override
    public void updateDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException {
        dtc_var.updateDateCorrected(date_corr);
    }

    @Override
    public void deleteDateCorrected(DateCorrected date_corr) throws ErrorException, FileNotFoundException, IOException {
        dtc_var.deleteDateCorrected(date_corr);
    }

    @Override
    public ArrayList<DateCorrected> getDateCorrected_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        return dtc_var.getDateCorrected_control_no(cntrl_no);
    }

    @Override
    public ArrayList<DateCorrected> getDateCorrected_emp_date(String emp_id, Date date) throws ErrorException, FileNotFoundException, IOException {
        return dtc_var.getDateCorrected_emp_date(emp_id, date);
    }
    
}
