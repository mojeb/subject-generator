/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.IT_impl;
import DAO_interface.IT_interface;
import Model.IT;
import Serv_interface.IT_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class IT_serv_impl implements IT_serv_interface {

    private IT_interface it_var;

    public IT_serv_impl (){
        it_var = new IT_impl();
    }
    
    @Override
    public int getResponse() {
        return it_var.getResponse();
    }

    @Override
    public void addIT(IT it) throws ErrorException, FileNotFoundException, IOException {
        it_var.addIT(it);
    }

    @Override
    public void updateIT(IT it) throws ErrorException, FileNotFoundException, IOException {
        it_var.updateIT(it);
    }

    @Override
    public void deleteIT(IT it) throws ErrorException, FileNotFoundException, IOException {
        it_var.deleteIT(it);
    }

    @Override
    public IT getIT_control_no(String cntrl_no) throws ErrorException, FileNotFoundException, IOException {
        return it_var.getIT_control_no(cntrl_no);
    }

    @Override
    public ArrayList<IT> getIT_applicant(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        return it_var.getIT_applicant(emp_id);
    }

    @Override
    public ArrayList<IT> getIT_date_app(String date1, String date2) throws ErrorException, FileNotFoundException, IOException {
        return it_var.getIT_date_app(date1, date2);
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        return it_var.getLastSeq(year);
    }
    
}
