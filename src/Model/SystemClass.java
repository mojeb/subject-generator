/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mojeb
 */
public class SystemClass {
    private int system_id;
    private String system_code;
    private String system_name;

    public SystemClass() {
        this.system_id = -1;
        this.system_code = "";
        this.system_name = "";
    }
    
    public SystemClass(int system_id, String system_code, String system_name) {
        this.system_id = system_id;
        this.system_code = system_code;
        this.system_name = system_name;
    }

    public int getSystem_id() {
        return system_id;
    }

    public void setSystem_id(int system_id) {
        this.system_id = system_id;
    }
    
    public String getSystem_code() {
        return system_code;
    }

    public void setSystem_code(String system_code) {
        this.system_code = system_code;
    }

    public String getSystem_name() {
        return system_name;
    }

    public void setSystem_name(String system_name) {
        this.system_name = system_name;
    }
}
