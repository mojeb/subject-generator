/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv_impl;

import DAO_impl.Library_impl;
import DAO_interface.Library_interface;
import Model.SystemClass;
import Model.SystemUser;
import Serv_interface.Library_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Library_serv_impl implements Library_serv_interface {

    private Library_interface lib;

    public Library_serv_impl (){
        lib = new Library_impl();
    }
    
    @Override
    public int getResponse() {
        return lib.getResponse();
    }

    @Override
    public void addSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException {
        lib.addSystem(sys);
    }

    @Override
    public void updateSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException {
        lib.updateSystem(sys);
    }

    @Override
    public void deleteSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException {
        lib.deleteSystem(sys);
    }

    @Override
    public SystemClass getSystem_code(String code) throws ErrorException, FileNotFoundException, IOException {
        return lib.getSystem_code(code);
    }

    @Override
    public ArrayList<SystemClass> getSystem_all() throws ErrorException, FileNotFoundException, IOException {
        return lib.getSystem_all();
    }

    @Override
    public void addSystemUser(SystemUser user) throws ErrorException, FileNotFoundException, IOException {
        lib.addSystemUser(user);
    }

    @Override
    public void deleteSystemUser(SystemUser user) throws ErrorException, FileNotFoundException, IOException {
        lib.deleteSystemUser(user);
    }

    @Override
    public SystemUser getSystemUser_id(int id) throws ErrorException, FileNotFoundException, IOException {
        return lib.getSystemUser_id(id);
    }

    @Override
    public ArrayList<SystemUser> getSystemUser_all() throws ErrorException, FileNotFoundException, IOException {
        return lib.getSystemUser_all();
    }

    @Override
    public SystemUser getSystemUser_dept(String dept) throws ErrorException, FileNotFoundException, IOException {
        return lib.getSystemUser_dept(dept);
    }
    
}
