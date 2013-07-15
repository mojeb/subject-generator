/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchEmployee.java
 *
 * Created on 05 15, 13, 1:09:31 PM
 */
package GUI;

import Model.Person;
import Serv_impl.Person_serv_impl;
import Serv_interface.Person_serv_interface;
import Tool.ErrorException;
import Tool.Methods;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hatiefm
 */
public class SearchEmployee extends javax.swing.JDialog {

    /** Creates new form SearchEmployee */
    //
    private JTextField tField;
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    
    private JFormattedTextField f1;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JComboBox c1;
    
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    
    private Methods m = new Methods();
    private ArrayList<Person> pList = new ArrayList<Person>();
    private Person_serv_interface pServ;
    private int index = -1;
    private int identifier = -1;
    
    public SearchEmployee(javax.swing.JDialog parent, boolean modal, int identifier) {
        super(parent, modal);
        initComponents();
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setLocationRelativeTo(null);
        this.setTitle("Search Employee");
        this.identifier = identifier;
        
        pServ = new Person_serv_impl();
        
        enableFields(false);
        jButton3.setEnabled(false);
    }
    
    public SearchEmployee(javax.swing.JFrame parent, boolean modal, int identifier) {
        super(parent, modal);
        initComponents();
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setLocationRelativeTo(null);
        this.setTitle("Search Employee");
        this.identifier = identifier;
        
        pServ = new Person_serv_impl();
        
        enableFields(false);
        jButton3.setEnabled(false);
    }
    
    private void enableFields(Boolean bool){
        jFormattedTextField1.setEnabled(bool);
        jTextField1.setEnabled(bool);
        jTextField2.setEnabled(bool);
        
        if(bool == true)
            setColor(Color.yellow);
        else setColor(Color.white);
    }
    
    private void clearFields(){
        jFormattedTextField1.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
    }
    
    private void setColor(Color color){
        jFormattedTextField1.setBackground(color);
        jTextField1.setBackground(color);
        jTextField2.setBackground(color);
    }
    
    public void setLabels(JLabel label1, JLabel label2, JLabel label3){
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
    }
    
    public void setLabelForSearchEmployee(JFormattedTextField f1, JTextField t1, 
            JTextField t2, JTextField t3, JTextField t4, JTextField t5, 
            JComboBox c1){
        this.f1 = f1;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.c1 = c1;
    }
    
    public void setButtons(JButton b1, JButton b2, JButton b3){
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
    }
    
    public void setTextField(JTextField tField){
        this.tField = tField;
    }
    
    public void setTextField_riv(JTextField t1, JTextField t2, JTextField t3, 
            JTextField t4){
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Employee Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 51));
        jLabel4.setText("Search Employee");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1.setText("ID");
        jRadioButton1.setFocusable(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2.setText("Lastname");
        jRadioButton2.setFocusable(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Firstname:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Lastname:");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Employee ID:");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton3.setText("Firstname");
        jRadioButton3.setFocusable(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/magnifier16.png"))); // NOI18N
        jButton1.setText("Find");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/application_put.png"))); // NOI18N
        jButton3.setText("Select");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cancel16.png"))); // NOI18N
        jButton2.setText("Cancel");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1)
                        .addComponent(jTextField2)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        enableFields(false);
        clearFields();
        jFormattedTextField1.setEnabled(true);
        jFormattedTextField1.setBackground(Color.yellow);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        enableFields(false);
        clearFields();
        jTextField1.setEnabled(true);
        jTextField1.setBackground(Color.yellow);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        enableFields(false);
        clearFields();
        jTextField2.setEnabled(true);
        jTextField2.setBackground(Color.yellow);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        pList.clear();
        if(!jRadioButton1.isSelected() && !jRadioButton2.isSelected() && !jRadioButton3.isSelected()){
            JOptionPane.showMessageDialog(this, "Error! Please select search option!", 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                if(jRadioButton1.isSelected()){//search for ID
                    String id = jFormattedTextField1.getText();
                    if(!id.isEmpty()){
                        Person p = new Person();
                        p = pServ.getPersonID(id);
                        pList.add(p);
                    }else{
                        JOptionPane.showMessageDialog(this, "Error! Please indicate"
                                + " the Employee ID No.", "Error Message", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }else if(jRadioButton2.isSelected()){//search for lastname
                    String lname = jTextField1.getText();
                    if(!lname.isEmpty()){
                        pList = pServ.getPersonLastname(lname);
                    }else{
                        JOptionPane.showMessageDialog(this, "Error! Please indicate"
                                + " the Employee lastname.", "Error Message", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }else if(jRadioButton3.isSelected()){//search for firstname
                    String fname = jTextField2.getText();
                    if(!fname.isEmpty()){
                        pList = pServ.getPersonFirstname(fname);
                    }else{
                        JOptionPane.showMessageDialog(this, "Error! Please indicate"
                                + " the Employee firstname.", "Error Message", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                //display data
                if(pList.isEmpty()){
                    JOptionPane.showMessageDialog(this, "No data found!", 
                            "Information Message", JOptionPane.INFORMATION_MESSAGE);
                    m.clearTable((DefaultTableModel)jTable1.getModel());
                }else m.displayPerson((DefaultTableModel)jTable1.getModel(), pList);
            }catch(ErrorException ex){}
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        index = jTable1.getSelectedRow();
        if(index >= 0)
            jButton3.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Person p = pList.get(index);
        if(identifier == -1){
            ManageDepartment.person = p;
            label1.setText(p.getEmp_id()+"");
            label2.setText(p.getCompleteName());
            label3.setText(p.getEmp_position());
        }else if(identifier == -2){
            tField.setText(p.getEmp_id()+"");
        }else if(identifier == -3){
            t1.setText(p.getEmp_id());
            t2.setText(p.getCompleteName());
            t3.setText(p.getEmp_position());
            t4.setText(p.getEmp_department().getDept_code());
        }else{
            ManageEmployee.person = p;
            f1.setText(p.getEmp_id()+"");
            t1.setText(p.getEmp_lname());
            t2.setText(p.getEmp_fname());
            t3.setText(p.getEmp_mname());
            t4.setText(p.getEmp_sname());
            t5.setText(p.getEmp_position());
            c1.setSelectedItem(p.getEmp_department().getDept_code());
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
        }
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
        jButton1.doClick();
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        jButton1.doClick();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        jButton1.doClick();
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                SearchEmployee dialog = new SearchEmployee(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
