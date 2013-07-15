/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Model.SeqNo;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface Seq_interface {
    
    public int getResponse();
    public void addSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException;
    public void editSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException;
    public void deleteSeq(SeqNo seq) throws ErrorException, FileNotFoundException, IOException;
    public SeqNo getSeq_seq_id(int seq_id) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<SeqNo> getSeq_no(String seq_no) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<SeqNo> getSeq_emp(String emp_id) throws ErrorException, FileNotFoundException, IOException;
    public int getLastSeq(int year) throws ErrorException, FileNotFoundException, IOException;
}
