/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Model.PO;
import Tool.ErrorException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Rommel
 */
public interface PO_interface {
    
    public int getResponse();
    public void addPO(PO po) throws ErrorException;
    public void editPO(PO po) throws ErrorException;
    public void deletePO(PO po) throws ErrorException;
    public PO getPO_control_no(String control_no) throws ErrorException;
    public ArrayList<PO> getPO_month(Date date, int year) throws ErrorException;
    public int getLastSeq(int year) throws ErrorException;
}
