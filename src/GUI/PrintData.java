/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.*;
import Tool.Methods;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Rommel
 */
public class PrintData extends JPanel implements Printable {
    
    private ArrayList<OBS> obsList = null;
    private ArrayList<NA> naList = null;
    private ArrayList<DTC> dtcList = null;
    private ArrayList<IT> itList = null;
    private ArrayList<PO> poList = null;
    private ArrayList<RIV> rivList = null;
    
    private Methods m = new Methods();
    private String date;
    
    public int y;
    public int x;
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(obsList != null){
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Official Business Slip", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
            g.setFont(font);
            g.drawString("Official Business Slip List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Date of Aplication", 100, 90);
            g.drawString("Distination", 230, 90);
            g.drawString("Travel Date", 450, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            x = 20;
            y = 110;
            for(OBS obs : obsList){
                g.drawString(obs.getControl_no(), x, y);
                x = x+80;
                g.drawString(obs.getDate_app().toString(), x, y);
                x = x+130;
                g.drawString(obs.getDistination(), x, y);
                x = x+220;
                g.drawString(obs.getDate_travel().toString(), x, y);
                x = 20;
                y = y+10;
            }
        }else if(naList != null){
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Notice of Absence", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Notice of Absences List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Employee Name", 80, 90);
            g.drawString("Office", 300, 90);
            g.drawString("Nature", 380, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            x = 20;
            y = 110;
            for(NA na : naList){
                g.drawString(na.getControl_no(), x, y);
                x = x+60;
                g.drawString(na.getEmp().getCompleteName(), x, y);
                x = x+220;
                g.drawString(na.getEmp().getEmp_department().getDept_code(), x, y);
                x = x+80;
                g.drawString(na.getNature(), x, y);
                x = 20;
                y = y+10;
            }
        }else if(dtcList != null){
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Daily Time Correction", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Daily Time Correction List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Employee Name", 100, 90);
            g.drawString("Application Date", 300, 90);
            g.drawString("Reason", 400, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            x = 20;
            y = 110;
            for(DTC dtc : dtcList){
                g.drawString(dtc.getControl_no(), x, y);
                x = x+80;
                g.drawString(dtc.getEmployee().getCompleteName(), x, y);
                x = x+200;
                g.drawString(dtc.getDate_app().toString(), x, y);
                x = x+100;
                g.drawString(dtc.getReason(), x, y);
                x = 20;
                y = y+10;
            }
        }else if(itList != null){
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Iterinary of Travel", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Iterinary of Travel List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Employee Name", 80, 90);
            g.drawString("Travel Date", 280, 90);
            g.drawString("Distination", 410, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            x = 20;
            y = 110;
            for(IT it : itList){
                g.drawString(it.getControl_no(), x, y);
                x = x+60;
                g.drawString(it.getEmployee().getCompleteName(), x, y);
                x = x+200;
                g.drawString(it.getTravel_date_from()+"/"+it.getTravel_date_to(), x, y);
                x = x+130;
                g.drawString(it.getDistination(), x, y);
                x = 20;
                y = y+10;
            }
        }else if(poList != null){
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Purchase Order (PO)", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Purchase Order List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("PO No.", 20, 90);
            g.drawString("Date Prepared", 70, 90);
            g.drawString("Supplier", 150, 90);
            g.drawString("RIV No.", 410, 90);
            g.drawString("Total Amount", 460, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            x = 20;
            y = 110;
            for(PO po : poList){
                g.drawString(po.getControl_no(), x, y);
                x = x+50;
                g.drawString(po.getDate().toString(), x, y);
                x = x+80;
                g.drawString(po.getSupplier(), x, y);
                x = x+260;
                g.drawString(po.getRiv().getControl_no(), x, y);
                x = x+50;
                g.drawString(po.getTotal_amount()+"", x, y);
                x = 20;
                y = y+10;
            }
        }else if(rivList != null){
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Requisition Voucher (RIV)", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Requisition Voucher List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("RIV No.", 20, 90);
            g.drawString("Date Received", 70, 90);
            g.drawString("Office", 150, 90);
            g.drawString("Description", 250, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            x = 20;
            y = 110;
            for(RIV riv : rivList){
                g.drawString(riv.getControl_no(), x, y);
                x = x+50;
                g.drawString(riv.getDate().toString(), x, y);
                x = x+80;
                g.drawString(riv.getEmp().getEmp_department().getDept_code(), x, y);
                x = x+100;
                g.drawString(riv.getDescription(), x, y);
                x = 20;
                y = y+10;
            }
        }
        
        g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, y);
        g.drawString("Date Printed: "+m.getDate_date_app(), 20, y+10);
        
        if(y < 715)
            y = 700;
        this.setSize (550, y+15);
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
            int numBreaks = (getPageSize()-1)/linesPerPage;
            pageBreak = new int[numBreaks];
            for(int b=0; b<numBreaks; b++)
                pageBreak[b] = (b-1)*linesPerPage;
        }
        
        if(page > pageBreak.length)
            return NO_SUCH_PAGE;
        
        g.translate(pf.getImageableX(), pf.getImageableY());
        //Data Information here
        if(obsList != null){
            font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Official Business Slip", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
            g.setFont(font);
            g.drawString("Official Business Slip List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Date of Aplication", 100, 90);
            g.drawString("Distination", 230, 90);
            g.drawString("Travel Date", 450, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            int x = 20;
            int y = 110;
            for(OBS obs : obsList){
                g.drawString(obs.getControl_no(), x, y);
                x = x+80;
                g.drawString(obs.getDate_app().toString(), x, y);
                x = x+130;
                g.drawString(obs.getDistination(), x, y);
                x = x+220;
                g.drawString(obs.getDate_travel().toString(), x, y);
                x = 20;
                y = y+10;
            }
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 700);
            g.drawString("Date Printed: "+m.getDate_date_app(), 20, 710);
        }else if(naList != null){
            font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Notice of Absence", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Notice of Absences List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Employee Name", 80, 90);
            g.drawString("Office", 300, 90);
            g.drawString("Nature", 380, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            int x = 20;
            int y = 110;
            for(NA na : naList){
                g.drawString(na.getControl_no(), x, y);
                x = x+60;
                g.drawString(na.getEmp().getCompleteName(), x, y);
                x = x+220;
                g.drawString(na.getEmp().getEmp_department().getDept_code(), x, y);
                x = x+80;
                g.drawString(na.getNature(), x, y);
                x = 20;
                y = y+10;
            }
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 700);
            g.drawString("Date Printed: "+m.getDate_date_app(), 20, 710);
        }else if(dtcList != null){
            font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Daily Time Correction", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Daily Time Correction List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Employee Name", 100, 90);
            g.drawString("Application Date", 300, 90);
            g.drawString("Reason", 400, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            int x = 20;
            int y = 110;
            for(DTC dtc : dtcList){
                g.drawString(dtc.getControl_no(), x, y);
                x = x+80;
                g.drawString(dtc.getEmployee().getCompleteName(), x, y);
                x = x+200;
                g.drawString(dtc.getDate_app().toString(), x, y);
                x = x+100;
                g.drawString(dtc.getReason(), x, y);
                x = 20;
                y = y+10;
            }
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 700);
            g.drawString("Date Printed: "+m.getDate_date_app(), 20, 710);
        }else if(itList != null){
            font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
            g.setFont(font);
            g.drawString("Iterinary of Travel", 20, 30);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
            g.setFont(font);
            g.drawString("PhilHealth Regional Office - ARMM", 20, 50);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(font);
            g.drawString("Iterinary of Travel List as of "+date+".", 20, 70);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 80);
            g.drawString("Control No.", 20, 90);
            g.drawString("Employee Name", 80, 90);
            g.drawString("Travel Date", 280, 90);
            g.drawString("Distination", 410, 90);
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 100);
            
            int x = 20;
            int y = 110;
            for(IT it : itList){
                g.drawString(it.getControl_no(), x, y);
                x = x+60;
                g.drawString(it.getEmployee().getCompleteName(), x, y);
                x = x+200;
                g.drawString(it.getTravel_date_from()+"/"+it.getTravel_date_to(), x, y);
                x = x+130;
                g.drawString(it.getDistination(), x, y);
                x = 20;
                y = y+10;
            }
            g.drawString("---------------------------------------------------------------"
                    + "------------------------------------------------------------------"
                    + "---------------------------------------------", 20, 700);
            g.drawString("Date Printed: "+m.getDate_date_app(), 20, 710);
        }
        
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
    
    public void setOBSList(ArrayList<OBS> obsList){
        this.obsList = obsList;
    }
    
    public void setDTCList(ArrayList<DTC> dtcList){
        this.dtcList = dtcList;
    }
    
    public void setITList(ArrayList<IT> itList){
        this.itList = itList;
    }
    
    public void setNAList(ArrayList<NA> naList){
        this.naList = naList;
    }
    
    public void setPOList(ArrayList<PO> poList){
        this.poList = poList;
    }
    
    public void setRIVList(ArrayList<RIV> rivList){
        this.rivList = rivList;
    }
    
    public void setStringDate(String date){
        this.date = date;
    }
    
    private int getPageSize(){
        if(obsList != null)
            return obsList.size();
        else if(itList != null)
            return itList.size();
        else if(naList != null)
            return naList.size();
        else if(dtcList != null)
            return dtcList.size();
        else if(poList != null)
            return poList.size();
        else if(rivList != null)
            return rivList.size();
        return 0;
    }
    
}
