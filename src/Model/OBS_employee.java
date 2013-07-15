/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Rommel
 */
public class OBS_employee {
    
    private String control_no;
    private Person p;

    public OBS_employee() {
        this.control_no = "";
        this.p = new Person();
    }
    
    public OBS_employee(String control_no, Person p) {
        this.control_no = control_no;
        this.p = p;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
}
