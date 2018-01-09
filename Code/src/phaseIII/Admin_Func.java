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
public class Admin_Func extends javax.swing.JPanel {

    /**
     * Creates new form Admin_Func
     */
    public TestFrame frame;
    public Admin_Func(TestFrame f) {
        initComponents();
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
        penddpLabel = new javax.swing.JLabel();
        pendAccountLabel = new javax.swing.JLabel();
        LogoutButton = new javax.swing.JButton();
        jDesktopPane8 = new javax.swing.JDesktopPane();
        jDesktopPane9 = new javax.swing.JDesktopPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 400));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Choose Functionality");

        penddpLabel.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        penddpLabel.setForeground(new java.awt.Color(102, 102, 102));
        penddpLabel.setText("<html><u>Pending data points</u></html>");
        penddpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penddpLabelMouseClicked(evt);
            }
        });

        pendAccountLabel.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        pendAccountLabel.setForeground(new java.awt.Color(102, 102, 102));
        pendAccountLabel.setText("<html><u>Pending City Official Accounts</u></html>");
        pendAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendAccountLabelMouseClicked(evt);
            }
        });

        LogoutButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        LogoutButton.setText("Log out");
        LogoutButton.setToolTipText("");
        LogoutButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(pendAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(144, 144, 144))))
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(penddpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(41, 41, 41)
                .addComponent(penddpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(pendAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jDesktopPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void penddpLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penddpLabelMouseClicked
        // TODO add your handling code here:
      Object[][] result = DB_test.Pending_DataPoint_Graph();  
       frame.getContentPane().removeAll();
       frame.getContentPane().invalidate();
       frame.getContentPane().add(new Pending_data_pt(frame,result));
       frame.getContentPane().revalidate();
       frame.pack();
      for(int i=0; i<result.length; i++) {
        for(int j=0; j<result[i].length; j++) {
            System.out.println("Values at arr["+i+"]["+j+"] is "+result[i][j]);
        }
    }
    }//GEN-LAST:event_penddpLabelMouseClicked

    private void pendAccountLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendAccountLabelMouseClicked
        // TODO add your handling code here:
       Object[][] result = DB_test.Pending_CityOfficial_Graph();
       frame.getContentPane().removeAll();
       frame.getContentPane().invalidate();
       frame.getContentPane().add(new Pending_City_Official_Accounts(frame,result));
       frame.getContentPane().revalidate();
       frame.pack();
       for(int i=0; i<result.length; i++) {
        for(int j=0; j<result[i].length; j++) {
            System.out.println("Values at arr["+i+"]["+j+"] is "+result[i][j]);
        }
       }
       
    }//GEN-LAST:event_pendAccountLabelMouseClicked

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
       frame.getContentPane().removeAll();
       frame.getContentPane().invalidate();
       frame.getContentPane().add(new Login(frame));
       frame.getContentPane().revalidate();
       frame.pack();
    }//GEN-LAST:event_LogoutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogoutButton;
    private javax.swing.JDesktopPane jDesktopPane8;
    private javax.swing.JDesktopPane jDesktopPane9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel pendAccountLabel;
    private javax.swing.JLabel penddpLabel;
    // End of variables declaration//GEN-END:variables
}
