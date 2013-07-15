/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.OBS_impl;
import DAO_impl.SO_impl;
import DAO_interface.OBS_interface;
import DAO_interface.SO_interface;
import Model.SO;
import Serv_interface.SO_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class SO_serv_impl implements SO_serv_interface {
    
    private SO_interface so_var;

    public SO_serv_impl (){
        so_var = new SO_impl();
    }

    @Override
    public int getResponse() {
        return so_var.getResponse();
    }

    @Override
    public void addSO(SO so) throws ErrorException, FileNotFoundException, IOException {
        so_var.addSO(so);
    }

    @Override
    public void editSO(SO so) throws ErrorException, FileNotFoundException, IOException {
        so_var.editSO(so);
    }

    @Override
    public void deleteSO(SO so) throws ErrorException, FileNotFoundException, IOException {
        so_var.deleteSO(so);
    }

    @Override
    public SO getSO_no_subject(String so_no, String subject) throws ErrorException, FileNotFoundException, IOException {
        return so_var.getSO_no_subject(so_no, subject);
    }

    @Override
    public ArrayList<SO> getSO_year_subject(int year, String subject) throws ErrorException, FileNotFoundException, IOException {
        return so_var.getSO_year_subject(year, subject);
    }

    @Override
    public int getLastSeq(int year, String subjet) throws ErrorException, FileNotFoundException, IOException {
        return so_var.getLastSeq(year, subjet);
    }
    
}
