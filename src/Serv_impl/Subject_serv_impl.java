/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.Subject_impl;
import DAO_interface.Subject_interface;
import Model.Subject;
import Serv_interface.Subject_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mojeb
 */
public class Subject_serv_impl implements Subject_serv_interface {

    private Subject_interface acc;

    public Subject_serv_impl (){
        acc = new Subject_impl();
    }

    @Override
    public int getResponce() {
        return acc.getResponce();
    }

    @Override
    public void addSubject(Subject sub) throws ErrorException, FileNotFoundException, IOException {
        acc.addSubject(sub);
    }

    @Override
    public void deleteSubject(Subject sub) throws ErrorException, FileNotFoundException, IOException {
        acc.deleteSubject(sub);
    }

    @Override
    public ArrayList<Subject> getSubjectByDate(Date date, String systemUser) throws ErrorException, FileNotFoundException, IOException {
        return acc.getSubjectByDate(date, systemUser);
    }

    @Override
    public ArrayList<Subject> getSubjectByRange(Date date1, Date date2, String user) throws ErrorException, FileNotFoundException, IOException {
        return acc.getSubjectByRange(date1, date2, user);
    }

    @Override
    public Subject getLast(String subject) throws ErrorException, FileNotFoundException, IOException {
        return acc.getLast(subject);
    }
    
}
