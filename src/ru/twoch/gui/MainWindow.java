package ru.twoch.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import ru.twoch.WorkerThread;
import ru.twoch.entity.db.Message;
import ru.twoch.entity.db.MessagePersistanceManager;

/**
 *
 * @author terranz
 */
public class MainWindow extends javax.swing.JFrame implements WorkIsDoneListener
{
    /** Creates new form MainWindow */
    private Connection c;

    public MainWindow()
    {
        initComponents();
    }

    public MainWindow(Connection connection)
    {
        this.c = connection;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_log = new javax.swing.JTextArea();
        te_thread_id = new javax.swing.JTextField();
        btnSaveThread = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("/b/");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Борда");

        jButton1.setText("Обновить");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ta_log.setColumns(20);
        ta_log.setRows(5);
        jScrollPane1.setViewportView(ta_log);

        te_thread_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                te_thread_idActionPerformed(evt);
            }
        });

        btnSaveThread.setText("Сохранить тред");
        btnSaveThread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveThreadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(54, 54, 54)
                        .addComponent(te_thread_id, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveThread)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(te_thread_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveThread))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField1ActionPerformed
    {//GEN-HEADEREND:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // TODO add your handling code here:
        WorkerThread wt = new WorkerThread(jTextField1.getText(), this, c);
        Thread t = new Thread(wt);
        t.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSaveThreadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSaveThreadActionPerformed
    {//GEN-HEADEREND:event_btnSaveThreadActionPerformed
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(MainWindow.this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            FileOutputStream fos = null;
            {
                OutputStreamWriter out = null;
                try
                {
                    File file = fc.getSelectedFile();
                    ta_log.append("Saving: " + file.getName() + "." + System.lineSeparator());
                    fos = new FileOutputStream(file);
                    out = new OutputStreamWriter(fos, "UTF-8");
                    List<Message> msgs = new MessagePersistanceManager(c).findMessagesByParent(Long.parseLong(te_thread_id.getText()));
                    out.append("<html><body>");
                    for (Message msg : msgs)
                    {
                        out.append(msg.getComment());
                        ta_log.append("saved message : " + msg.getNum() + System.lineSeparator());
                    }
                    out.append("</body></html>");
                } catch (IOException ex)
                {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                {
                    try
                    {
                        out.close();
                        fos.close();
                    } catch (IOException ex)
                    {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else
        {
            ta_log.append("Save command cancelled by user." + System.lineSeparator());
        }
    }//GEN-LAST:event_btnSaveThreadActionPerformed

    private void te_thread_idActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_te_thread_idActionPerformed
    {//GEN-HEADEREND:event_te_thread_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_te_thread_idActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveThread;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea ta_log;
    private javax.swing.JTextField te_thread_id;
    // End of variables declaration//GEN-END:variables

    @Override
    public void done(String... params)
    {
        ta_log.append(params[0]);
    }
}
