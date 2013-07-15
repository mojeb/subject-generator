/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import Model.*;
import Serv_impl.*;
import Serv_interface.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author hatiefm
 */
public class Methods {
    
    private Subject_serv_interface subjectServ;
    private Department_serv_interface dServ;
    private Person_serv_interface pServ;
    private OBS_serv_interface oServ;
    private DTC_serv_interface dtServ;
    private IT_serv_interface itServ;
    private Library_serv_interface lServ;
    private NA_serv_interface naServ;
    private Account_serv_interface aServ;
    private Seq_serv_interface seqServ;
    private SO_serv_interface soServ;
    private RIV_serv_interface rivServ;
    private PO_serv_interface poServ;
    
    public static String subject;
    
    public void clearTable(DefaultTableModel table){
        int lastRow = table.getRowCount() - 1;

        for(int temp = lastRow ; temp > -1 ;temp--){
            table.removeRow(temp);

        }
    }
    
    public void displaySubject(DefaultTableModel model, ArrayList<Subject> list){
        clearTable(model);
        for (Subject sub : list) {
            model.addRow(new Object[]{sub.getDepartment()+stringDate((Date)sub.getDate(), 
                    sub.getCount()), sub.getSubj()});
        }
    }
    
    public String stringDate(Date date, int count){
        
        int month = date.getMonth()+1;
        String month_str = month+"";
        if(month < 10)
            month_str = "0"+month;
        
        int date2 = date.getDate();
        String date_str = date2+"";
        if(date2 < 10)
            date_str = "0"+date2;
        
        return "-"+(date.getYear()+1900)+""+month_str+""+date_str+"-"+count;
    }
    
    public String convertDateToString(Date date){
        
        int month = date.getMonth()+1;
        String month_str = "";
        if(month < 10)
            month_str = "0"+month;
        
        int date2 = date.getDate();
        String date_str = "";
        if(date2 < 10)
            date_str = "0"+date2;
        
        return (date.getYear()+1900)+"-"+month_str+"-"+date_str+"(yyyy-MM-dd)";
    }
    
    public String getDate(){
        Calendar now = new GregorianCalendar();
        
        int month = now.getTime().getMonth()+1;
        String month_str = month+"";
        if(month < 10)
            month_str = "0"+month;
        
        int date = now.getTime().getDate();
        String date_str = date+"";
        if(date < 10)
            date_str = "0"+date;
        
        return (now.getTime().getYear()+1900)+""+month_str+""+date_str;
    }
    
    public int getCurrentYear(){
        Calendar now = new GregorianCalendar();
        return now.getTime().getYear()+1900;
    }
    
    public String getDate_date_app(){
        Calendar now = new GregorianCalendar();
        
        int month = now.getTime().getMonth()+1;
        String month_str = month+"";
        if(month < 10)
            month_str = "0"+month;
        
        int date = now.getTime().getDate();
        String date_str = date+"";
        if(date < 10)
            date_str = "0"+date;
        
        return (now.getTime().getYear()+1900)+"-"+month_str+"-"+date_str;
    }
    
    public int getLastCount(String department) throws ErrorException, FileNotFoundException, IOException{
        subjectServ = new Subject_serv_impl();
        int lastCount = 1;
        try{
            Subject newSubject = subjectServ.getLast(department);
            if(newSubject != null)
                lastCount = newSubject.getCount() + 1;
        }catch(ErrorException ex){ }
        
        return lastCount;
    }
    
    public void loadUserLevel(JComboBox comboBox){
        comboBox.addItem("Select User Level");
        comboBox.addItem("Cashier");
        comboBox.addItem("Encoder");
        comboBox.addItem("GRP Uploader");
        comboBox.addItem("GSD");
        comboBox.addItem("Supervisor");
        comboBox.addItem("System Admin");
    }
    
    public String serverIP() throws FileNotFoundException, IOException{
        String s = "";
        
        File file = new File("serverIP.txt");
        FileInputStream fis;
        BufferedInputStream bis;
        DataInputStream dis;
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        while(dis.available() != 0){
            s = dis.readLine();
        }
        fis.close();
        bis.close();
        dis.close();
        return s;
    }
    
    public void loadDepartment(JComboBox combo){
        combo.removeAllItems();
        ArrayList<Department> dList = new ArrayList<Department>();
        dServ = new Department_serv_impl();
        try{
            dList = dServ.getAllDepartment();
        }catch(ErrorException ex){ }
        combo.addItem("Select Office");
        for(Department d : dList)
            combo.addItem(d.getDept_code());
    }
    
    public void loadYear(JComboBox combo){
        combo.removeAllItems();
        Calendar now = new GregorianCalendar();
        int year = now.getTime().getYear()+1900;
        
        combo.addItem("Year");
        for(int i=year; i>=2012; i--)
            combo.addItem(i);
    }
    
    public int get_last_day_month(String month){
        int last_day = -1;
        if(month.equalsIgnoreCase("January") || month.equalsIgnoreCase("March") ||
                month.equalsIgnoreCase("May") || month.equalsIgnoreCase("July") ||
                month.equalsIgnoreCase("August") || month.equalsIgnoreCase("October") ||
                month.equalsIgnoreCase("December")){
            last_day = 31;
        }else if(month.equalsIgnoreCase("February")){
            Calendar now = new GregorianCalendar();
            int year = now.getTime().getYear()+1900;
            
            if(year % 4 == 0)
                last_day = 29;
            else last_day = 28;
        }else{
            last_day = 30;
        }
        return last_day;
    }
    
    public int convert_month(String month){
        int new_month = -1;
        if(month.equalsIgnoreCase("January"))
            new_month = 1;
        else if(month.equalsIgnoreCase("February"))
            new_month = 2;
        else if(month.equalsIgnoreCase("March"))
            new_month = 3;
        else if(month.equalsIgnoreCase("April"))
            new_month = 4;
        else if(month.equalsIgnoreCase("May"))
            new_month = 5;
        else if(month.equalsIgnoreCase("June"))
            new_month = 6;
        else if(month.equalsIgnoreCase("July"))
            new_month = 7;
        else if(month.equalsIgnoreCase("August"))
            new_month = 8;
        else if(month.equalsIgnoreCase("September"))
            new_month = 9;
        else if(month.equalsIgnoreCase("October"))
            new_month = 10;
        else if(month.equalsIgnoreCase("November"))
            new_month = 11;
        else if(month.equalsIgnoreCase("December"))
            new_month = 12;
        return new_month;
    }
    
    public void displayPerson(DefaultTableModel model, ArrayList<Person> list){
        clearTable(model);
        for (Person p : list) {
            model.addRow(new Object[]{p.getEmp_id(), p.getCompleteName()});
        }
    }
    
    public void displayOBS(DefaultTableModel model, ArrayList<OBS> list){
        clearTable(model);
        for (OBS obs : list) {
            model.addRow(new Object[]{obs.getControl_no(), obs.getDate_app(), 
                obs.getDistination(), obs.getDate_travel()});
        }
    }
    
    public void displayIT(DefaultTableModel model, ArrayList<IT> list){
        clearTable(model);
        for (IT it : list) {
            model.addRow(new Object[]{it.getControl_no(), it.getEmployee().getCompleteName(),
            it.getDate_app(), it.getTravel_date_from()+" to "+it.getTravel_date_to(),
            it.getDistination()});
        }
    }
    
    public void displayDTC(DefaultTableModel model, ArrayList<DTC> list){
        clearTable(model);
        for (DTC dtc : list) {
            model.addRow(new Object[]{dtc.getControl_no(), dtc.getEmployee().getCompleteName(),
            dtc.getDate_app(), dtc.getReason()});
        }
    }
    
    public int checkDepartment(Department d){
        int value = 0;
        dServ = new Department_serv_impl();
        try{
            Department dept_code = dServ.getDepartmentbyCode(d.getDept_code());
            Department dept_name = dServ.getDepartmentbyName(d.getDept_name());
            
            if(dept_code != null || dept_name != null)
                value = 1;
        }catch(ErrorException ex){ }
        return value;
    }
    
    public void displayDepartment(DefaultTableModel model, ArrayList<Department> list){
        clearTable(model);
        pServ = new Person_serv_impl();
        try{
            for (Department d : list) {
                Person p = new Person();
                if(!d.getPerson().getEmp_id().isEmpty()){
                    p = pServ.getPersonID(d.getPerson().getEmp_id());
                }
                
                model.addRow(new Object[]{d.getDept_code(), d.getDept_name(),
                p.getCompleteName()});
            }
        }catch(ErrorException ex){}
    }
    
    public void displayProcessingSystem(DefaultTableModel model, ArrayList<SystemClass> list){
        clearTable(model);
        for (SystemClass sys : list) {
            model.addRow(new Object[]{sys.getSystem_code(), sys.getSystem_name()});
        }
    }
    
    public void displayDateCorrected(DefaultTableModel model, ArrayList<DateCorrected> list){
        clearTable(model);
        for (DateCorrected dc : list) {
            model.addRow(new Object[]{(dc.getDate().getMonth()+1)+"-"+dc.getDate().getDate()
                    +"-"+(dc.getDate().getYear()+1900), dc.getIn_am(), dc.getOut_am(),
                    dc.getIn_pm(), dc.getOut_pm()});
        }
    }
    
    public void displayNA(DefaultTableModel model, ArrayList<NA> list){
        clearTable(model);
        for (NA na : list) {
            model.addRow(new Object[]{na.getControl_no(), na.getEmp().getCompleteName(),
            na.getEmp().getEmp_department().getDept_code(), na.getDate_app().toString(),
            na.getNature()});
        }
    }
    
    public void displayRIV(DefaultTableModel model, ArrayList<RIV> list){
        clearTable(model);
        for (RIV riv : list) {
            model.addRow(new Object[]{riv.getControl_no(), riv.getDate().toString(),
            riv.getEmp().getCompleteName(), riv.getDescription()});
        }
    }
    
    public void displayPO(DefaultTableModel model, ArrayList<PO> list){
        clearTable(model);
        for (PO po : list) {
            model.addRow(new Object[]{po.getControl_no(), po.getDate().toString(),
            po.getSupplier(), po.getRiv().getControl_no(), po.getTotal_amount()});
        }
    }
    
    public String generate_control_no(String parameter){
        String ret = "";
        Calendar now = new GregorianCalendar();
        int year = now.getTime().getYear()+1900;
        
        oServ = new OBS_serv_impl();
        dtServ = new DTC_serv_impl();
        itServ = new IT_serv_impl();
        naServ = new NA_serv_impl();
        seqServ = new Seq_serv_impl();
        soServ = new SO_serv_impl();
        rivServ = new RIV_serv_impl();
        poServ = new PO_serv_impl();
        
        try{
            int next_seq = -1;
            if(parameter.equalsIgnoreCase("OBS"))
                next_seq = oServ.getLastSeq(year)+1;
            else if(parameter.equalsIgnoreCase("DTC"))
                next_seq = dtServ.getLastSeq(year)+1;
            else if(parameter.equalsIgnoreCase("IT"))
                next_seq = itServ.getLastSeq(year)+1;
            else if(parameter.equalsIgnoreCase("NA"))
                next_seq = naServ.getLastSeq(year)+1;
            else if(parameter.equalsIgnoreCase("SEQ"))
                next_seq = seqServ.getLastSeq(year)+1;
            else if(parameter.equalsIgnoreCase("SO"))
                next_seq = soServ.getLastSeq(year, subject)+1;
            else if(parameter.equalsIgnoreCase("RIV"))
                next_seq = rivServ.getLastSeq(year)+1;
            else if(parameter.equalsIgnoreCase("PO"))
                next_seq = poServ.getLastSeq(year)+1;
            
            if(parameter.equals("SEQ")){
                if(next_seq < 10)
                    ret = "00"+next_seq+"-"+year;
                else if(next_seq >= 10 && next_seq <100)
                    ret = "0"+next_seq+"-"+year;
                else ret = next_seq+"-"+year;
            }else if(parameter.equals("PO") || parameter.equals("JR")){
                if(next_seq < 10)
                    ret = (year-2000)+"-"+"00"+next_seq;
                else if(next_seq >= 10 && next_seq <100)
                    ret = (year-2000)+"-"+"0"+next_seq;
                else ret = (year-2000)+"-"+next_seq;
            }else{
                if(next_seq < 10)
                    ret = "00"+next_seq+"-"+(year-2000);
                else if(next_seq >= 10 && next_seq <100)
                    ret = "0"+next_seq+"-"+(year-2000);
                else ret = next_seq+"-"+(year-2000);
            }
        }catch(ErrorException ex){ }
        catch(FileNotFoundException ex){ }
        catch(IOException ex){ }
        
        return ret;
    }
    
    public String getSO(){
        String ret = "";
        soServ = new SO_serv_impl();
        try{
            int next_seq = soServ.getLastSeq(getCurrentYear(), subject)+1;
            
            if(next_seq < 10)
                ret = "00"+next_seq+", Series of "+getCurrentYear();
            else if(next_seq >= 10 && next_seq <100)
                ret = "0"+next_seq+", Series of "+getCurrentYear();
            else ret = next_seq+", Series of "+getCurrentYear();
            
        }catch(ErrorException ex){ }
        catch(FileNotFoundException ex){ }
        catch(IOException ex){ }
        
        return ret;
    }
    
    public int checkSystemDuplicate(String name){
        int val = 1;
        lServ = new Library_serv_impl();
        try{
            SystemClass sys = lServ.getSystem_code(name);
            if(sys == null)
                val = -1;
        }catch(ErrorException ex){ }
        catch(FileNotFoundException ex){ }
        catch(IOException ex){ }
        
        return val;
    }
    
    public int checkSystemUserDuplicate(String office){
        int val = 1;
        lServ = new Library_serv_impl();
        try{
            SystemUser user = lServ.getSystemUser_dept(office);
            if(user == null)
                val = -1;
        }catch(ErrorException ex){ }
        catch(FileNotFoundException ex){ }
        catch(IOException ex){ }
        
        return val;
    }
    
    public void displaySystemUser(JList list, final ArrayList<SystemUser> userList){
        list.removeAll();
        list.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return userList.size();
            }
            @Override
            public String getElementAt(int index) {
                return "   "+userList.get(index).getDept().getDept_code();
            }
        });
    }
    
    public void displayDaysAbsent(JList list, final ArrayList<DaysAbsent> dList){
        list.removeAll();
        list.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return dList.size();
            }
            @Override
            public String getElementAt(int index) {
                return "   "+dList.get(index).getStr_date();
            }
        });
    }
    
    public void displaySeqNo(JList list, final ArrayList<SeqNo> List){
        list.removeAll();
        list.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return List.size();
            }
            @Override
            public String getElementAt(int index) {
                return "   "+List.get(index).getSeq_no()+"    "+
                        List.get(index).getModel();
            }
        });
    }
    
    public void displayEmp_OBS(JList list, final ArrayList<OBS_employee> List){
        list.removeAll();
        list.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return List.size();
            }
            @Override
            public String getElementAt(int index) {
                return "   "+List.get(index).getP().getCompleteName();
            }
        });
    }
    
    public int check_employee_id(ArrayList<OBS_employee> list, String emp_id){
        int val = -1;
        for(OBS_employee emp : list){
            if(emp.getP().getEmp_id().equals(emp_id))
                val = 1;
        }
        return val;
    }
    
    public void loadSystemUser(JComboBox combo){
        combo.removeAllItems();
        ArrayList<SystemUser> userList = new ArrayList<SystemUser>();
        lServ = new Library_serv_impl();
        try{
            userList = lServ.getSystemUser_all();
            combo.addItem("Select System User");
            for(SystemUser user : userList){
                combo.addItem(user.getDept().getDept_code());
            }
        }catch(ErrorException ex){ }
        catch(FileNotFoundException ex){ }
        catch(IOException ex){ }
    }
    
    public void loadSystems(JComboBox combo){
        combo.removeAllItems();
        ArrayList<SystemClass> sysList = new ArrayList<SystemClass>();
        lServ = new Library_serv_impl();
        try{
            sysList = lServ.getSystem_all();
            combo.addItem("Select Processing System");
            for(SystemClass sys : sysList){
                combo.addItem(sys.getSystem_code());
            }
        }catch(ErrorException ex){ }
        catch(FileNotFoundException ex){ }
        catch(IOException ex){ }
    }
    
    public void displayRights(DefaultTreeModel model){
        aServ = new Account_serv_impl();
        ArrayList<Account> aList = new ArrayList<Account>();
        try{
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Account Rights");

            DefaultMutableTreeNode node_admin = new DefaultMutableTreeNode("Administrator");
            root.add(node_admin);
            //display accounts under admin...
            aList = aServ.getAccount_right("Administrator");
            for(Account acc : aList){
                DefaultMutableTreeNode node_admin_user = new DefaultMutableTreeNode(acc.getUsername());
                node_admin.add(node_admin_user);
            }

            DefaultMutableTreeNode node_hru = new DefaultMutableTreeNode("HRU Personnel");
            root.add(node_hru);
            //display accounts under hru...
            aList = aServ.getAccount_right("HRU Personnel");
            for(Account acc : aList){
                DefaultMutableTreeNode node_hru_user = new DefaultMutableTreeNode(acc.getUsername());
                node_hru.add(node_hru_user);
            }

            DefaultMutableTreeNode node_orvp = new DefaultMutableTreeNode("ORVP Personnel");
            root.add(node_orvp);
            //display accounts under orvp...
            aList = aServ.getAccount_right("ORVP Personnel");
            for(Account acc : aList){
                DefaultMutableTreeNode node_orvp_user = new DefaultMutableTreeNode(acc.getUsername());
                node_orvp.add(node_orvp_user);
            }

            DefaultMutableTreeNode node_gsu = new DefaultMutableTreeNode("GSU Personnel");
            root.add(node_gsu);
            //display accounts under gsu...
            aList = aServ.getAccount_right("GSU Personnel");
            for(Account acc : aList){
                DefaultMutableTreeNode node_gsu_user = new DefaultMutableTreeNode(acc.getUsername());
                node_gsu.add(node_gsu_user);
            }

            model.setRoot(root);
            
        }catch(ErrorException ex){
        }catch(FileNotFoundException ex){
        }catch(IOException ex){}
    }
}
