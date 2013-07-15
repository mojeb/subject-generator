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
public class PO {
    
    private String control_no;
    private Date date;
    private String supplier;
    private RIV riv;
    private double total_amount;
    private String remarks;
    private int year;
    private int seq;
    
    public PO() {
        this.control_no = "";
        this.date = null;
        this.supplier = "";
        this.riv = new RIV();
        this.total_amount = 0.00;
        this.remarks = "";
        this.year = 0;
        this.seq = 0;
    }

    public PO(String control_no, Date date, String supplier, RIV riv, 
            double total_amount, String remarks, int year, int seq) {
        this.control_no = control_no;
        this.date = date;
        this.supplier = supplier;
        this.riv = riv;
        this.total_amount = total_amount;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public RIV getRiv() {
        return riv;
    }

    public void setRiv(RIV riv) {
        this.riv = riv;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
