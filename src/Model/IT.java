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
public class IT {
    
    private String control_no;
    private Date date_app;
    private Person employee;
    private Date travel_date_from;
    private Date travel_date_to;
    private String distination;
    private String remarks;
    private int year;
    private int seq;
    
    public IT() {
        this.control_no = "";
        this.date_app = null;
        this.employee = new Person();
        this.travel_date_from = null;
        this.travel_date_to = null;
        this.distination = "";
        this.remarks = "";
        this.year = -1;
        this.seq = -1;
    }

    public IT(String control_no, Date date_app, Person employee, 
            Date travel_date_from, Date travel_date_to, String distination, 
            String remarks, int year, int seq) {
        this.control_no = control_no;
        this.date_app = date_app;
        this.employee = employee;
        this.travel_date_from = travel_date_from;
        this.travel_date_to = travel_date_to;
        this.distination = distination;
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

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
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

    public Date getTravel_date_from() {
        return travel_date_from;
    }

    public void setTravel_date_from(Date travel_date_from) {
        this.travel_date_from = travel_date_from;
    }

    public Date getTravel_date_to() {
        return travel_date_to;
    }

    public void setTravel_date_to(Date travel_date_to) {
        this.travel_date_to = travel_date_to;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
