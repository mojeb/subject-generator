/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mojeb
 */
public class SO {
    
    private String so_no;
    private String description;
    private int year;
    private int seq;
    private String subject;
    
    public SO() {
        this.so_no = "";
        this.description = "";
        this.year = 0;
        this.seq = 0;
        this.subject = "";
    }

    public SO(String so_no, String description, int year, int seq, String subject) {
        this.so_no = so_no;
        this.description = description;
        this.year = year;
        this.seq = seq;
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSo_no() {
        return so_no;
    }

    public void setSo_no(String so_no) {
        this.so_no = so_no;
    }
    
    public String getSubjet() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
