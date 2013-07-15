/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.*;
import Serv_impl.DTC_serv_impl;
import Serv_impl.OBS_serv_impl;
import Serv_impl.Person_serv_impl;
import Serv_interface.DTC_serv_interface;
import Serv_interface.OBS_serv_interface;
import Serv_interface.Person_serv_interface;
import Tool.ErrorException;
import Tool.Methods;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Rommel
 */
public class PrintData_short extends JPanel implements Printable {
    
    private DTC dtc = null;
    private OBS obs = null;
    private RIV riv = null;
    private PO po = null;
    
    private Date date;
    private String emp_id;
    
    private Methods m = new Methods();
    private DTC_serv_interface dServ;
    private OBS_serv_interface oServ;
    private Person_serv_interface pServ;
    
    public int ySize;
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        dServ = new DTC_serv_impl();
        oServ = new OBS_serv_impl();
        pServ = new Person_serv_impl();
        
        try{
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            if(dtc != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Daily Time Correction", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("Employee ID:", 20, 80);
                g.drawString("Employee Name:", 20, 90);
                g.drawString("Position:", 20, 100);
                g.drawString("Office:", 20, 110);
                g.drawString("Date:", 20, 120);
                font = new Font(Font.SANS_SERIF, Font.BOLD, 9);
                g.setFont(font);
                Person p = pServ.getPersonID(emp_id);
                g.drawString(p.getEmp_id(), 120, 80);
                g.drawString(p.getCompleteName(), 120, 90);
                g.drawString(p.getEmp_position(), 120, 100);
                g.drawString(p.getEmp_department().getDept_code(), 120, 110);
                g.drawString(date.getYear()+1900+"-"+(date.getMonth()+1), 120, 120);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 130);
                g.drawString("Date", 20, 140);
                g.drawString("Time In (AM)", 120, 140);
                g.drawString("Time Out (AM)", 220, 140);
                g.drawString("Time In (PM)", 320, 140);
                g.drawString("Time Out (PM)", 420, 140);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 150);
                int x = 20;
                int y = 160;
                
                ArrayList<DateCorrected> dcList = new ArrayList<DateCorrected>();
                dcList = dServ.getDateCorrected_emp_date(emp_id, date);
                
                for(DateCorrected dc : dcList){
                    g.drawString(dc.getDate().toString(), x, y);
                    x = x + 100;
                    g.drawString(dc.getIn_am(), x, y);
                    x = x + 100;
                    g.drawString(dc.getOut_am(), x, y);
                    x = x + 100;
                    g.drawString(dc.getIn_pm(), x, y);
                    x = x + 100;
                    g.drawString(dc.getOut_pm(), x, y);
                    x = 20;
                    y = y + 10;
                }
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }else if(obs != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Official Business Slip", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("Control No.:", 20, 70);
                g.drawString("Application Date:", 20, 80);
                g.drawString("Distination:", 20, 90);
                g.drawString("Travel Date:", 20, 100);
                font = new Font(Font.SANS_SERIF, Font.BOLD, 9);
                g.setFont(font);
                g.drawString(obs.getControl_no(), 120, 70);
                g.drawString(obs.getDate_app().toString(), 120, 80);
                g.drawString(obs.getDistination(), 120, 90);
                g.drawString(obs.getDate_travel().toString(), 120, 100);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 110);
                g.drawString("ID Number", 20, 120);
                g.drawString("Complete Name", 120, 120);
                g.drawString("Position/Office", 320, 120);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 130);
                int x = 20;
                int y = 140;
                ArrayList<OBS_employee> obsList = new ArrayList<OBS_employee>();
                obsList = oServ.getOBS_employee(obs.getControl_no());
                
                for(OBS_employee ob : obsList){
                    g.drawString(ob.getP().getEmp_id(), x, y);
                    x = x + 100;
                    g.drawString(ob.getP().getCompleteName(), x, y);
                    x = x + 200;
                    g.drawString(ob.getP().getEmp_position()+", "
                            +ob.getP().getEmp_department().getDept_code(), x, y);
                    x = 20;
                    y = y + 10;
                }
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }else if(riv != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Requisition Voucher (RIV)", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 70);
                g.drawString("Control No.", 20, 80);
                g.drawString("Date Received", 80, 80);
                g.drawString("Requesting Office", 150, 80);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 90);
                g.drawString(riv.getControl_no(), 20, 100);
                g.drawString(riv.getDate().toString(), 80, 100);
                g.drawString(riv.getEmp().getEmp_department().getDept_name(), 150, 100);
                
                font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                g.setFont(font);
                g.drawString("Requesting Employee", 20, 130);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 140);
                g.drawString("Employee ID", 20, 150);
                g.drawString("Employee Name", 120, 150);
                g.drawString("Position", 320, 150);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 160);
                g.drawString(riv.getEmp().getEmp_id(), 20, 170);
                g.drawString(riv.getEmp().getCompleteName(), 120, 170);
                g.drawString(riv.getEmp().getEmp_position(), 320, 170);
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 210);
                font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                g.setFont(font);
                g.drawString("RIV Description:", 20, 220);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 230);
                g.drawString(riv.getDescription(), 20, 240);
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }else if(po != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Purchase Order (PO)", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 70);
                g.drawString("Control No.", 20, 80);
                g.drawString("Date Prepared", 80, 80);
                g.drawString("Supplier", 180, 80);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 90);
                g.drawString(po.getControl_no(), 20, 100);
                g.drawString(po.getDate().toString(), 80, 100);
                g.drawString(po.getSupplier(), 180, 100);
                
                font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                g.setFont(font);
                g.drawString("Riquisition Voucher (RIV)", 20, 130);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 140);
                g.drawString("RIV No.", 20, 150);
                g.drawString("Description", 80, 150);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 160);
                g.drawString(po.getRiv().getControl_no(), 20, 170);
                g.drawString(po.getRiv().getDescription(), 80, 170);
                g.drawString("Total Amount:     "+po.getTotal_amount(), 20, 200);
                
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }
            
        }catch(ErrorException ex){
        }catch(FileNotFoundException ex){
        }catch(IOException ex){}
        
        this.setSize (550, 350);
    }

    ArrayList<Graphics2D> graph = new ArrayList<Graphics2D>();
    int[] pageBreak;
    @Override
    public int print(Graphics gx, PageFormat pf, int page) throws PrinterException {
        Graphics2D g = (Graphics2D) gx;
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHieght = metrics.getHeight();
        if(pageBreak == null){
            int linesPerPage = (int)((pf.getImageableHeight()-150)/lineHieght);
            int numBreaks = (25-1)/linesPerPage;
            pageBreak = new int[numBreaks];
            for(int b=0; b<numBreaks; b++)
                pageBreak[b] = (b-1)*linesPerPage;
        }
        if(page > pageBreak.length)
            return NO_SUCH_PAGE;
        
        g.translate(pf.getImageableX(), pf.getImageableY());
        //Data Information here
        try{
            font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            if(dtc != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Daily Time Correction", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("Employee ID:", 20, 80);
                g.drawString("Employee Name:", 20, 90);
                g.drawString("Position:", 20, 100);
                g.drawString("Office:", 20, 110);
                g.drawString("Date:", 20, 120);
                font = new Font(Font.SANS_SERIF, Font.BOLD, 9);
                g.setFont(font);
                Person p = pServ.getPersonID(emp_id);
                g.drawString(p.getEmp_id(), 120, 80);
                g.drawString(p.getCompleteName(), 120, 90);
                g.drawString(p.getEmp_position(), 120, 100);
                g.drawString(p.getEmp_department().getDept_code(), 120, 110);
                g.drawString(date.getYear()+1900+"-"+(date.getMonth()+1), 120, 120);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 130);
                g.drawString("Date", 20, 140);
                g.drawString("Time In (AM)", 120, 140);
                g.drawString("Time Out (AM)", 220, 140);
                g.drawString("Time In (PM)", 320, 140);
                g.drawString("Time Out (PM)", 420, 140);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 150);
                int x = 20;
                int y = 160;
                
                ArrayList<DateCorrected> dcList = new ArrayList<DateCorrected>();
                dcList = dServ.getDateCorrected_emp_date(emp_id, date);
                
                for(DateCorrected dc : dcList){
                    g.drawString(dc.getDate().toString(), x, y);
                    x = x + 100;
                    g.drawString(dc.getIn_am(), x, y);
                    x = x + 100;
                    g.drawString(dc.getOut_am(), x, y);
                    x = x + 100;
                    g.drawString(dc.getIn_pm(), x, y);
                    x = x + 100;
                    g.drawString(dc.getOut_pm(), x, y);
                    x = 20;
                    y = y + 10;
                }
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }else if(obs != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Official Business Slip", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("Control No.:", 20, 70);
                g.drawString("Application Date:", 20, 80);
                g.drawString("Distination:", 20, 90);
                g.drawString("Travel Date:", 20, 100);
                font = new Font(Font.SANS_SERIF, Font.BOLD, 9);
                g.setFont(font);
                g.drawString(obs.getControl_no(), 120, 70);
                g.drawString(obs.getDate_app().toString(), 120, 80);
                g.drawString(obs.getDistination(), 120, 90);
                g.drawString(obs.getDate_travel().toString(), 120, 100);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 110);
                g.drawString("ID Number", 20, 120);
                g.drawString("Complete Name", 120, 120);
                g.drawString("Position/Office", 320, 120);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 130);
                int x = 20;
                int y = 140;
                ArrayList<OBS_employee> obsList = new ArrayList<OBS_employee>();
                obsList = oServ.getOBS_employee(obs.getControl_no());
                
                for(OBS_employee ob : obsList){
                    g.drawString(ob.getP().getEmp_id(), x, y);
                    x = x + 100;
                    g.drawString(ob.getP().getCompleteName(), x, y);
                    x = x + 200;
                    g.drawString(ob.getP().getEmp_position()+", "
                            +ob.getP().getEmp_department().getDept_code(), x, y);
                    x = 20;
                    y = y + 10;
                }
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }else if(riv != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Requisition Voucher (RIV)", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 70);
                g.drawString("Control No.", 20, 80);
                g.drawString("Date Received", 80, 80);
                g.drawString("Requesting Office", 150, 80);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 90);
                g.drawString(riv.getControl_no(), 20, 100);
                g.drawString(riv.getDate().toString(), 80, 100);
                g.drawString(riv.getEmp().getEmp_department().getDept_name(), 150, 100);
                
                font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                g.setFont(font);
                g.drawString("Requesting Employee", 20, 130);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 140);
                g.drawString("Employee ID", 20, 150);
                g.drawString("Employee Name", 120, 150);
                g.drawString("Position", 320, 150);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 160);
                g.drawString(riv.getEmp().getEmp_id(), 20, 170);
                g.drawString(riv.getEmp().getCompleteName(), 120, 170);
                g.drawString(riv.getEmp().getEmp_position(), 320, 170);
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 210);
                font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                g.setFont(font);
                g.drawString("RIV Description:", 20, 220);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 230);
                g.drawString(riv.getDescription(), 20, 240);
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }else if(po != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
                g.setFont(font);
                g.drawString("Purchase Order (PO)", 20, 30);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 70);
                g.drawString("Control No.", 20, 80);
                g.drawString("Date Prepared", 80, 80);
                g.drawString("Supplier", 180, 80);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 90);
                g.drawString(po.getControl_no(), 20, 100);
                g.drawString(po.getDate().toString(), 80, 100);
                g.drawString(po.getSupplier(), 180, 100);
                
                font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                g.setFont(font);
                g.drawString("Riquisition Voucher (RIV)", 20, 130);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
                g.setFont(font);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 140);
                g.drawString("RIV No.", 20, 150);
                g.drawString("Description", 80, 150);
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 160);
                g.drawString(po.getRiv().getControl_no(), 20, 170);
                g.drawString(po.getRiv().getDescription(), 80, 170);
                g.drawString("Total Amount:     "+po.getTotal_amount(), 20, 200);
                
                
                g.drawString("------------------------------------------------------"
                        + "---------------------------------------------------------"
                        + "-----------------------------------------------------", 20, 300);
                g.drawString("Date Printed:"+m.getDate_date_app(), 20, 310);
            }
        }catch(ErrorException ex){
        }catch(FileNotFoundException ex){
        }catch(IOException ex){}
        graph.add(g);
        return PAGE_EXISTS;
    }
    
    public void showPrintDialog(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if(job.printDialog() == true){
            try{
                job.print();
            }catch(PrinterException ex){}
        }
    }
    
    public void setDTC(DTC dtc){
        this.dtc = dtc;
    }
    
    public void setOBS(OBS obs){
        this.obs = obs;
    }
    
    public void setRIV(RIV riv){
        this.riv = riv;
    }
    
    public void setPO(PO po){
        this.po = po;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public void setEmp_id(String emp_id){
        this.emp_id = emp_id;
    }
    
}
