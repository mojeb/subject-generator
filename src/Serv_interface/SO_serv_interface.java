/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_interface;

import Model.SO;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface SO_serv_interface {
    
    public int getResponse();
    public void addSO(SO so) throws ErrorException, FileNotFoundException, IOException;
    public void editSO(SO so) throws ErrorException, FileNotFoundException, IOException;
    public void deleteSO(SO so) throws ErrorException, FileNotFoundException, IOException;
    public SO getSO_no_subject(String so_no, String subject) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<SO> getSO_year_subject(int year, String subject) throws ErrorException, FileNotFoundException, IOException;
    public int getLastSeq(int year, String subjet) throws ErrorException, FileNotFoundException, IOException;
}
