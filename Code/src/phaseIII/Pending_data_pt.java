/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phaseIII;

/**
 *
 * @author WYF
 */
import javax.swing.*;
import javax.swing.table.*;
import phaseIII.myTable;
public class Pending_data_pt extends javax.swing.JPanel {

    /**
     * Creates new form Pending_data_pt
     */
    public TestFrame frame;
    public myTable table;
    public Object[] column = {"Select","POI Location","Data type","Data value","Time&date of data reading"};
    public Pending_data_pt(TestFrame f,Object[][] result) {
        initComponents(); 
        table = new myTable(result,column,this,new int[]{60,150,500,100});
        //DefaultTableModel model = new DefaultTableModel(result, column);
        //JTable myTable = new JTable(model);
       // myTable.setPreferredScrollableViewportSize(myTable.getPreferredSize());
        //JScrollPane scroll = new JScrollPane(temp);
       // scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scroll.setBounds(100,100,500,100);                
        //this.add(scroll);
        frame =f;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jDesktopPane8 = new javax.swing.JDesktopPane();
        jDesktopPane9 = new javax.swing.JDesktopPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 400));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Pending data points");

        jButton2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.setToolTipText("");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton3.setText("Reject");
        jButton3.setToolTipText("");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jButton4.setText("Accept");
        jButton4.setToolTipText("");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jDesktopPane8.setSize(600,25);

        jDesktopPane9.setSize(600,25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane8)
            .addComponent(jDesktopPane9)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(139, 139, 139))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jDesktopPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         frame.getContentPane().removeAll();
         frame.getContentPane().invalidate();
         frame.getContentPane().add(new Admin_Func(frame));
         frame.getContentPane().revalidate();
         frame.pack();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int[] user_selected = table.getRow(); 
        System.out.println("The selected index length is "+user_selected.length);
        /*
        for(int i = 0;i<user_selected.length;i++){
            System.out.println("The selected index is");
            System.out.println(user_selected[i]);
        }
        */
        DataPoint[] content = new DataPoint[user_selected.length];
        for(int i = 0; i<user_selected.length;i++){
            Object[] temp = table.get_row_object(user_selected[i]);
            String name = temp[1].toString();
            String Date = temp[4].toString();
            int Datavalues = (int)temp[3];
            String DataType = temp[2].toString();
            Boolean accepted = (Boolean)temp[0];            
            DataPoint temp_dp = new DataPoint(name,Date,Datavalues,DataType,accepted);
            content[i] = temp_dp;
            System.out.println("Datavalues " +Datavalues);
                      
        }
        DB_test.accept_Datapoint(content);
        table.delete_row(user_selected);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int[] user_selected = table.getRow(); 
        System.out.println("The selected index length is "+user_selected.length);
        /*
        for(int i = 0;i<user_selected.length;i++){
            System.out.println("The selected index is");
            System.out.println(user_selected[i]);
        }
        */
        DataPoint[] content = new DataPoint[user_selected.length];
        for(int i = 0; i<user_selected.length;i++){
            Object[] temp = table.get_row_object(user_selected[i]);
            String name = temp[1].toString();
            String Date = temp[4].toString();
            int Datavalues = (int)temp[3];
            String DataType = temp[2].toString();
            Boolean accepted = (Boolean)temp[0];            
            DataPoint temp_dp = new DataPoint(name,Date,Datavalues,DataType,accepted);
            content[i] = temp_dp;
            System.out.println("Datavalues " +Datavalues);
                      
        }
        DB_test.reject_Datapoint(content);
        table.delete_row(user_selected);  
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDesktopPane jDesktopPane8;
    private javax.swing.JDesktopPane jDesktopPane9;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
