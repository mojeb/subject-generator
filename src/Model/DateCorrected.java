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
public class DateCorrected {
    
    private String control_no;
    private String in_am;
    private String out_am;
    private String in_pm;
    private String out_pm;
    private Date date;

    public DateCorrected() {
        this.control_no = "";
        this.in_am = "";
        this.out_am = "";
        this.in_pm = "";
        this.out_pm = "";
        this.date = null;
    }
    
    public DateCorrected(String control_no, String in_am, String out_am, 
            String in_pm, String out_pm, Date date) {
        this.control_no = control_no;
        this.in_am = in_am;
        this.out_am = out_am;
        this.in_pm = in_pm;
        this.out_pm = out_pm;
        this.date = date;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public String getIn_am() {
        return in_am;
    }

    public void setIn_am(String in_am) {
        this.in_am = in_am;
    }

    public String getIn_pm() {
        return in_pm;
    }

    public void setIn_pm(String in_pm) {
        this.in_pm = in_pm;
    }

    public String getOut_am() {
        return out_am;
    }

    public void setOut_am(String out_am) {
        this.out_am = out_am;
    }

    public String getOut_pm() {
        return out_pm;
    }

    public void setOut_pm(String out_pm) {
        this.out_pm = out_pm;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
