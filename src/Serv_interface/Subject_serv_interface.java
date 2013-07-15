/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_interface;

import Model.Subject;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mojeb
 */
public interface Subject_serv_interface {
    
    public int getResponce();
    public void addSubject(Subject sub) throws ErrorException, FileNotFoundException, IOException;
    public void deleteSubject(Subject sub) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<Subject> getSubjectByDate(Date date, String systemUser) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<Subject> getSubjectByRange(Date date1, Date date2, String user) throws ErrorException, FileNotFoundException, IOException;
    public Subject getLast(String subject) throws ErrorException, FileNotFoundException, IOException;
}
