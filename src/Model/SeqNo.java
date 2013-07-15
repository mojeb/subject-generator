/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mojeb
 */
public class SeqNo {
    private int seq_id;
    private String seq_no;
    private String category;
    private String model;
    private String serial;
    private String po;
    private String riv;
    private String date_purchase;
    private Person emp;
    private int year;
    private int seq;
    
    public SeqNo() {
        this.seq_id = 0;
        this.seq_no = "";
        this.category = "";
        this.model = "";
        this.serial = "";
        this.po = "";
        this.riv = "";
        this.date_purchase = "";
        this.emp = new Person();
        this.year = 0;
        this.seq = 0;
    }

    public SeqNo(int seq_id, String seq_no, String category, String model, String serial, 
            String po, String riv, String date_purchase, Person emp, int year,
            int seq) {
        this.seq_id = seq_id;
        this.seq_no = seq_no;
        this.category = category;
        this.model = model;
        this.serial = serial;
        this.po = po;
        this.riv = riv;
        this.date_purchase = date_purchase;
        this.emp = emp;
        this.year = year;
        this.seq = seq;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(String date_purchase) {
        this.date_purchase = date_purchase;
    }

    public Person getEmp() {
        return emp;
    }

    public void setEmp(Person emp) {
        this.emp = emp;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getRiv() {
        return riv;
    }

    public void setRiv(String riv) {
        this.riv = riv;
    }

    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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
    
    public int getSeq_id() {
        return seq_id;
    }

    public void setSeq_id(int seq_id) {
        this.seq_id = seq_id;
    }
}
