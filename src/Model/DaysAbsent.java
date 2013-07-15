/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mojeb
 */
public class DaysAbsent {
    
    private String control_no;
    private String str_date;
    
    public DaysAbsent() {
        this.control_no = "";
        this.str_date = "";
    }

    public DaysAbsent(String control_no, String str_date) {
        this.control_no = control_no;
        this.str_date = str_date;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public String getStr_date() {
        return str_date;
    }

    public void setStr_date(String str_date) {
        this.str_date = str_date;
    }
}
