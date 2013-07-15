/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author mojeb
 */
public class Subject {
    
    private int subject_id;
    private Date date;
    private int count;
    private String subj;
    private String department;

    public Subject() {
        this.subject_id = -1;
        this.date = null;
        this.count = 0;
        this.subj = "";
        this.department = "";
    }
    
    public Subject(int subject_id, Date date, int count, String subj, 
            String department) {
        this.subject_id = subject_id;
        this.date = date;
        this.count = count;
        this.subj = subj;
        this.department = department;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }
    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
