/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author diana
 */
public class CambiosFunciones extends javax.swing.JFrame {

    /**
     * Creates new form VentanaModificarFunciones
     */
    public CambiosFunciones() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel17 = new java.awt.Panel();
        panel16 = new java.awt.Panel();
        jLabel20 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        comboBoxObrasAgendadas = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        comboBoxHorario = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        comboBoxObraNueva = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        comboBoxHorarioNuevo = new javax.swing.JComboBox<>();
        jDateFechaNueva = new com.toedter.calendar.JDateChooser();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        panel4 = new java.awt.Panel();
        panel5 = new java.awt.Panel();
        jLabel4 = new javax.swing.JLabel();
        btnRegresarMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar funciones");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel17.setBackground(new java.awt.Color(255, 255, 254));
        panel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel16.setBackground(new java.awt.Color(25, 43, 55));

        jLabel20.setBackground(new java.awt.Color(25, 43, 55));
        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Modificar/Eliminar Funciones");

        javax.swing.GroupLayout panel16Layout = new javax.swing.GroupLayout(panel16);
        panel16.setLayout(panel16Layout);
        panel16Layout.setHorizontalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel16Layout.createSequentialGroup()
                .addContainerGap(221, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel16Layout.setVerticalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel17.add(panel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 50));

        btnModificar.setBackground(new java.awt.Color(255, 102, 0));
        btnModificar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setToolTipText("Modificar");
        panel17.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 130, 30));

        btnEliminar.setBackground(new java.awt.Color(25, 43, 55));
        btnEliminar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Eliminar");
        panel17.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 130, 30));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(25, 43, 55));
        jLabel12.setText("Obras agendadas");
        panel17.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        comboBoxObrasAgendadas.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxObrasAgendadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel17.add(comboBoxObrasAgendadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 240, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(25, 43, 55));
        jLabel16.setText("Fecha");
        panel17.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(25, 43, 55));
        jLabel19.setText("Horario");
        panel17.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(25, 43, 55));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Cancelar");
        panel17.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 130, 30));

        comboBoxHorario.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxHorario.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel17.add(comboBoxHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 110, -1));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(25, 43, 55));
        jLabel23.setText("Obra nueva");
        panel17.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        comboBoxObraNueva.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxObraNueva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel17.add(comboBoxObraNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 240, -1));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(25, 43, 55));
        jLabel24.setText("Fecha nueva");
        panel17.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(25, 43, 55));
        jLabel25.setText("Horario nuevo");
        panel17.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, -1));

        comboBoxHorarioNuevo.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxHorarioNuevo.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxHorarioNuevo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel17.add(comboBoxHorarioNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 110, -1));

        jDateFechaNueva.setBackground(new java.awt.Color(255, 255, 255));
        jDateFechaNueva.setForeground(new java.awt.Color(0, 0, 0));
        panel17.add(jDateFechaNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 110, 20));

        jDateFecha.setBackground(new java.awt.Color(255, 255, 255));
        jDateFecha.setForeground(new java.awt.Color(0, 0, 0));
        panel17.add(jDateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 110, 20));

        getContentPane().add(panel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, 360));

        panel4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 500, 30));

        panel5.setBackground(new java.awt.Color(255, 102, 0));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Menú");

        btnRegresarMenu.setBackground(new java.awt.Color(255, 102, 0));
        btnRegresarMenu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnRegresarMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/regreso.png"))); // NOI18N
        btnRegresarMenu.setToolTipText("Regresar");
        btnRegresarMenu.setBorder(null);

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 417, Short.MAX_VALUE))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(panel5Layout.createSequentialGroup()
                .addComponent(btnRegresarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CambiosFunciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambiosFunciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambiosFunciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambiosFunciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CambiosFunciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegresarMenu;
    private javax.swing.JComboBox<String> comboBoxHorario;
    private javax.swing.JComboBox<String> comboBoxHorarioNuevo;
    private javax.swing.JComboBox<String> comboBoxObraNueva;
    private javax.swing.JComboBox<String> comboBoxObrasAgendadas;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private com.toedter.calendar.JDateChooser jDateFechaNueva;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private java.awt.Panel panel16;
    private java.awt.Panel panel17;
    private java.awt.Panel panel4;
    private java.awt.Panel panel5;
    // End of variables declaration//GEN-END:variables
}
