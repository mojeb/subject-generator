/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mojeb
 */
public class Right {
    
    private String emp_id;
    private String right;
    private String status;
    
    public Right() {
        this.emp_id = "";
        this.right = "";
        this.status = "";
    }

    public Right(String account_id, String right, String status) {
        this.emp_id = account_id;
        this.right = right;
        this.status = status;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String account_id) {
        this.emp_id = account_id;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
