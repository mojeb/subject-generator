/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_interface;

import Model.Account;
import Model.Right;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface Account_serv_interface {
    
    public int getResponse();
    public void addAccount(Account acc) throws ErrorException, FileNotFoundException, IOException;
    public void editAccount(Account acc) throws ErrorException, FileNotFoundException, IOException;
    public void deleteAccount(Account acc) throws ErrorException, FileNotFoundException, IOException;
    public Account getAccount_emp_id(String emp_id) throws ErrorException, FileNotFoundException, IOException;
    public Account getAccount_username_right(String username, String right) throws ErrorException, FileNotFoundException, IOException;
    public Account getAccount_username_pass(String username, String password) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<Account> getAccount_right(String right) throws ErrorException, FileNotFoundException, IOException;
    
    public void addRight(Right r) throws ErrorException, FileNotFoundException, IOException;
    public void editRight(Right r) throws ErrorException, FileNotFoundException, IOException;
    public void deleteRight(Right r) throws ErrorException, FileNotFoundException, IOException;
    public Right getRight(String emp_id, String right) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<Right> getRight_emp_id(String emp_id) throws ErrorException, FileNotFoundException, IOException;
}
