/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mojeb
 */
public class SystemUser {
    private int system_user_id;
    private Department dept;

    public SystemUser() {
        this.system_user_id = -1;
        this.dept = new Department();
    }
    
    public SystemUser(int system_user_id, Department dept) {
        this.system_user_id = system_user_id;
        this.dept = dept;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public int getSystem_user_id() {
        return system_user_id;
    }

    public void setSystem_user_id(int system_user_id) {
        this.system_user_id = system_user_id;
    }
}
