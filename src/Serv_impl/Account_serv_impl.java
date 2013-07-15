/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.Account_impl;
import DAO_interface.Account_interface;
import Model.Account;
import Model.Right;
import Serv_interface.Account_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Account_serv_impl implements Account_serv_interface {
    
    private Account_interface acc_var;

    public Account_serv_impl (){
        acc_var = new Account_impl();
    }

    @Override
    public int getResponse() {
        return acc_var.getResponse();
    }

    @Override
    public void addAccount(Account acc) throws ErrorException, FileNotFoundException, IOException {
        acc_var.addAccount(acc);
    }

    @Override
    public void editAccount(Account acc) throws ErrorException, FileNotFoundException, IOException {
        acc_var.editAccount(acc);
    }

    @Override
    public void deleteAccount(Account acc) throws ErrorException, FileNotFoundException, IOException {
        acc_var.deleteAccount(acc);
    }

    @Override
    public ArrayList<Account> getAccount_right(String right) throws ErrorException, FileNotFoundException, IOException {
        return acc_var.getAccount_right(right);
    }

    @Override
    public void addRight(Right r) throws ErrorException, FileNotFoundException, IOException {
        acc_var.addRight(r);
    }

    @Override
    public void deleteRight(Right r) throws ErrorException, FileNotFoundException, IOException {
        acc_var.deleteRight(r);
    }

    @Override
    public Right getRight(String emp_id, String right) throws ErrorException, FileNotFoundException, IOException {
        return acc_var.getRight(emp_id, right);
    }

    @Override
    public Account getAccount_username_pass(String username, String password) throws ErrorException, FileNotFoundException, IOException {
        return acc_var.getAccount_username_pass(username, password);
    }

    @Override
    public Account getAccount_username_right(String username, String right) throws ErrorException, FileNotFoundException, IOException {
        return acc_var.getAccount_username_right(username, right);
    }

    @Override
    public Account getAccount_emp_id(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        return acc_var.getAccount_emp_id(emp_id);
    }

    @Override
    public void editRight(Right r) throws ErrorException, FileNotFoundException, IOException {
        acc_var.editRight(r);
    }

    @Override
    public ArrayList<Right> getRight_emp_id(String emp_id) throws ErrorException, FileNotFoundException, IOException {
        return acc_var.getRight_emp_id(emp_id);
    }
    
}
