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
public class Account {
    
    private Date date_created;
    private Person emp;
    private String username;
    private String password;
    
    public Account() {
        this.date_created = null;
        this.emp = new Person();
        this.username = "";
        this.password = "";
    }

    public Account(Date date_created, Person emp, String username, 
            String password) {
        this.date_created = date_created;
        this.emp = emp;
        this.username = username;
        this.password = password;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Person getEmp() {
        return emp;
    }

    public void setEmp(Person emp) {
        this.emp = emp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
