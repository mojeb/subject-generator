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
public class OBS {
    
    private String control_no;
    private Date date_app;
    private String distination;
    private Date date_travel;
    private int year;
    private int seq;
    private String remarks;

    public OBS() {
        this.control_no = "";
        this.date_app = null;
        this.distination = "";
        this.date_travel = null;
        this.remarks = "";
    }
    
    public OBS(String control_no, Date date_app, 
            String distination, Date date_travel, String remarks) {
        this.control_no = control_no;
        this.date_app = date_app;
        this.distination = distination;
        this.date_travel = date_travel;
        this.remarks = remarks;
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

    public Date getDate_travel() {
        return date_travel;
    }

    public void setDate_travel(Date date_travel) {
        this.date_travel = date_travel;
    }

    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
