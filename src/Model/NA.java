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
public class NA {
    
    private String control_no;
    private Date date_app;
    private Person emp;
    private String nature;
    private String remarks;
    private int year;
    private int seq;
    
    public NA() {
        this.control_no = "";
        this.date_app = null;
        this.emp = new Person();
        this.nature = "";
        this.remarks = "";
        this.year = 0;
        this.seq = 0;
    }

    public NA(String control_no, Date date_app, Person emp, String nature, 
            String remarks, int year, int seq) {
        this.control_no = control_no;
        this.date_app = date_app;
        this.emp = emp;
        this.nature = nature;
        this.remarks = remarks;
        this.year = year;
        this.seq = seq;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public Date getDate_app() {
        return date_app;
    }

    public void setDate_app(Date date_app) {
        this.date_app = date_app;
    }

    public Person getEmp() {
        return emp;
    }

    public void setEmp(Person emp) {
        this.emp = emp;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
