/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JButton;

/**
 *
 * @author diana
 */
public class PanelDelMenu extends javax.swing.JPanel {

    /**
     * Creates new form PanelDelMenu
     */
    public PanelDelMenu() {
        initComponents();
    }

    public JButton getBtnAgregarMenu() {
        return btnAgregarMenu;
    }

    public void setBtnAgregarMenu(JButton btnAgregarMenu) {
        this.btnAgregarMenu = btnAgregarMenu;
    }

    public JButton getBtnModificarMenu() {
        return btnModificarMenu;
    }

    public void setBtnModificarMenu(JButton btnModificarMenu) {
        this.btnModificarMenu = btnModificarMenu;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnModificarMenu = new javax.swing.JButton();
        btnAgregarMenu = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnModificarMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnModificarMenu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnModificarMenu.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarMenu.setText("Modificar/Eliminar");
        btnModificarMenu.setBorder(null);
        btnModificarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarMenuActionPerformed(evt);
            }
        });

        btnAgregarMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarMenu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAgregarMenu.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarMenu.setText("Agregar");
        btnAgregarMenu.setBorder(null);
        btnAgregarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnModificarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAgregarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMenuActionPerformed
        
    }//GEN-LAST:event_btnAgregarMenuActionPerformed

    private void btnModificarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMenu;
    private javax.swing.JButton btnModificarMenu;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
