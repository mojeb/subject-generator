/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Rommel
 */
public class RIV {
    
    private String control_no;
    private Date date;
    private Person emp;
    private String description;
    private String remarks;
    private int year;
    private int seq;
    
    public RIV() {
        this.control_no = "";
        this.date = null;
        this.emp = new Person();
        this.description = "";
        this.remarks = "";
        this.year = 0;
        this.seq = 0;
    }

    public RIV(String control_no, Date date, Person emp, String description, 
            String remarks, int year, int seq) {
        this.control_no = control_no;
        this.date = date;
        this.emp = emp;
        this.description = description;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getEmp() {
        return emp;
    }

    public void setEmp(Person emp) {
        this.emp = emp;
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
