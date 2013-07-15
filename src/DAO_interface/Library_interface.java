/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Model.SystemClass;
import Model.SystemUser;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface Library_interface {
    
    public int getResponse();
    //system library
    public void addSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException;
    public void updateSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException;
    public void deleteSystem(SystemClass sys) throws ErrorException, FileNotFoundException, IOException;
    public SystemClass getSystem_code(String code) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<SystemClass> getSystem_all() throws ErrorException, FileNotFoundException, IOException;
    
    //user library
    public void addSystemUser(SystemUser user) throws ErrorException, FileNotFoundException, IOException;
    public void deleteSystemUser(SystemUser user) throws ErrorException, FileNotFoundException, IOException;
    public SystemUser getSystemUser_id(int id) throws ErrorException, FileNotFoundException, IOException;
    public SystemUser getSystemUser_dept(String dept) throws ErrorException, FileNotFoundException, IOException;
    public ArrayList<SystemUser> getSystemUser_all() throws ErrorException, FileNotFoundException, IOException;
}
