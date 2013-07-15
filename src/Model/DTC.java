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
public class DTC {
    
    private String control_no;
    private Date date_app;
    private Person employee;
    private String reason;
    private String remarks;
//    private DateCorrected date_corr;
    private int year;
    private int seq;

    public DTC() {
        this.control_no = "";
        this.date_app = null;
        this.employee = new Person();
        this.reason = "";
        this.remarks = "";
//        this.date_corr = new DateCorrected();
        this.year = -1;
        this.seq = -1;
    }
    
    public DTC(String control_no, Date date_app, Person employee, String reason, 
            String remarks,/* DateCorrected date_corr,*/ int year, int seq) {
        this.control_no = control_no;
        this.date_app = date_app;
        this.employee = employee;
        this.reason = reason;
        this.remarks = remarks;
//        this.date_corr = date_corr;
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

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
    }
    /*
    public DateCorrected getDateCorrected() {
        return date_corr;
    }

    public void setDateCorrected(DateCorrected date_corr) {
        this.date_corr = date_corr;
    }
    */
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
