/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.Seq_impl;
import DAO_interface.Seq_interface;
import Model.SeqNo;
import Serv_interface.Seq_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Seq_serv_impl implements Seq_serv_interface {
    
    private Seq_interface seq_var;

    public Seq_serv_impl (){
        seq_var = new Seq_impl();
    }

    @Override
    public int getResponse() {
        return seq_var.getResponse();
    }

    @Override
    public void addSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException {
        seq_var.addSeq(seq);
    }

    @Override
    public void editSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException {
        seq_var.editSeq(seq);
    }

    @Override
    public void deleteSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException {
        seq_var.deleteSeq(seq);
    }

    @Override
    public SeqNo getSeq_seq_id(int seq_id) throws ErrorException, FileNotFoundException, IOException {
        return seq_var.getSeq_seq_id(seq_id);
    }

    @Override
    public ArrayList<SeqNo> getSeq_no(String seq_no) throws ErrorException, FileNotFoundException, IOException {
        return seq_var.getSeq_no(seq_no);
    }

    @Override
    public ArrayList<SeqNo> getSeq_emp(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        return seq_var.getSeq_emp(emp_id);
    }

    @Override
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException {
        return seq_var.getLastSeq(year);
    }
}
