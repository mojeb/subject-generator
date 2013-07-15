/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.PO_impl;
import DAO_interface.PO_interface;
import Model.PO;
import Serv_interface.PO_serv_interface;
import Tool.ErrorException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Rommel
 */
public class PO_serv_impl implements PO_serv_interface {
    
    private PO_interface po_var;

    public PO_serv_impl (){
        po_var = new PO_impl();
    }

    @Override
    public int getResponse() {
        return po_var.getResponse();
    }

    @Override
    public void addPO(PO po) throws ErrorException {
        po_var.addPO(po);
    }

    @Override
    public void editPO(PO po) throws ErrorException {
        po_var.editPO(po);
    }

    @Override
    public void deletePO(PO po) throws ErrorException {
        po_var.deletePO(po);
    }

    @Override
    public PO getPO_control_no(String control_no) throws ErrorException {
        return po_var.getPO_control_no(control_no);
    }

    @Override
    public ArrayList<PO> getPO_month(Date date, int year) throws ErrorException {
        return po_var.getPO_month(date, year);
    }

    @Override
    public int getLastSeq(int year) throws ErrorException {
        return po_var.getLastSeq(year);
    }
    
}
