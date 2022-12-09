/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class ElegirAsientos extends javax.swing.JFrame {

    /**
     * Creates new form ElegirAsientos
     */
    public ElegirAsientos() {
        initComponents();
        setLocationRelativeTo(null);
        asientos();
    }
    
    
    
    
    private JCheckBox [][]asientosA= new JCheckBox[3][5];
    private JCheckBox [][]asientosB1= new JCheckBox[2][3];
    private JCheckBox [][]asientosB2= new JCheckBox[2][3];
    private JCheckBox [][]asientosC= new JCheckBox[3][5];
    
    public void asientos(){
        
        //ASIENTOS A
        int filas= 3;
        int columnas= 5;
        int ejex=0;
        int ejey= 20;
        int largo=73;
        int ancho=46;
        asientosA= new JCheckBox[filas][columnas];
        int numAsiento=0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                numAsiento++;
                asientosA[i][j]= new JCheckBox();
                asientosA[i][j].setBounds(ejex, ejey, largo, ancho);
                asientosA[i][j].setBackground(Color.white);
                ImageIcon imgAsiento= new ImageIcon("src/Vista/Img/Asiento.png");
                ImageIcon imgAsientoSelec= new ImageIcon("src/Vista/Img/AsientoSeleccionado.png");
                ImageIcon imgAsientoOcup= new ImageIcon("src/Vista/Img/AsientoOcupado.png");
                asientosA[i][j].setIcon(imgAsiento);
                asientosA[i][j].setSelectedIcon(imgAsientoSelec);
                asientosA[i][j].setDisabledIcon(imgAsientoOcup);
                asientosA[i][j].setFont(new Font("Century Gothic", 0, 10));
                asientosA[i][j].setText("A"+numAsiento);
                panelAsientosA.add(asientosA[i][j]);
                
                ejex += 70;
                
            }
            ejex=0;
            ejey += 40;
        }
        
        //ASIENTOSC
         ejex=0;
         ejey= 20;
        asientosC = new JCheckBox[filas][columnas];
        numAsiento=0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                numAsiento++;
                asientosC[i][j]= new JCheckBox();
                asientosC[i][j].setBounds(ejex, ejey, largo, ancho);
                asientosC[i][j].setBackground(Color.white);
                ImageIcon imgAsiento= new ImageIcon("src/Vista/Img/Asiento.png");
                ImageIcon imgAsientoSelec= new ImageIcon("src/Vista/Img/AsientoSeleccionado.png");
                ImageIcon imgAsientoOcup= new ImageIcon("src/Vista/Img/AsientoOcupado.png");
                asientosC[i][j].setIcon(imgAsiento);
                asientosC[i][j].setSelectedIcon(imgAsientoSelec);
                asientosC[i][j].setDisabledIcon(imgAsientoOcup);
                asientosC[i][j].setFont(new Font("Century Gothic", 0, 10));
                asientosC[i][j].setText("C"+numAsiento);
                panelAsientosC.add(asientosC[i][j]);
                
                ejex += 70;
                
            }
            ejex=0;
            ejey += 40;
        }
        
        //ASIENTOSB
        filas=3;
        columnas=2;
         ejex=0;
         ejey= 20;
         asientosB1 = new JCheckBox[filas][columnas];
        numAsiento=0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                numAsiento++;
                asientosB1[i][j]= new JCheckBox();
                asientosB1[i][j].setBounds(ejex, ejey, largo, ancho);
                asientosB1[i][j].setBackground(Color.white);
                ImageIcon imgAsiento= new ImageIcon("src/Vista/Img/Asiento.png");
                ImageIcon imgAsientoSelec= new ImageIcon("src/Vista/Img/AsientoSeleccionado.png");
                ImageIcon imgAsientoOcup= new ImageIcon("src/Vista/Img/AsientoOcupado.png");
                asientosB1[i][j].setIcon(imgAsiento);
                asientosB1[i][j].setSelectedIcon(imgAsientoSelec);
                asientosB1[i][j].setDisabledIcon(imgAsientoOcup);
                asientosB1[i][j].setFont(new Font("Century Gothic", 0, 10));
                asientosB1[i][j].setText("B"+numAsiento);
                panelAsientosB1.add(asientosB1[i][j]);
                
                ejex += 70;
                
            }
            ejex=0;
            ejey += 40;
        }
        
        filas=3;
        columnas=2;
         ejex=0;
         ejey= 20;
         asientosB2 = new JCheckBox[filas][columnas];
        numAsiento=6;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                numAsiento++;
                asientosB2[i][j]= new JCheckBox();
                asientosB2[i][j].setBounds(ejex, ejey, largo, ancho);
                asientosB2[i][j].setBackground(Color.white);
                ImageIcon imgAsiento= new ImageIcon("src/Vista/Img/Asiento.png");
                ImageIcon imgAsientoSelec= new ImageIcon("src/Vista/Img/AsientoSeleccionado.png");
                ImageIcon imgAsientoOcup= new ImageIcon("src/Vista/Img/AsientoOcupado.png");
                asientosB2[i][j].setIcon(imgAsiento);
                asientosB2[i][j].setSelectedIcon(imgAsientoSelec);
                asientosB2[i][j].setDisabledIcon(imgAsientoOcup);
                asientosB2[i][j].setFont(new Font("Century Gothic", 0, 10));
                asientosB2[i][j].setText("B"+numAsiento);
                panelAsientosB2.add(asientosB2[i][j]);
                
                ejex += 70;
                
            }
            ejex=0;
            ejey += 40;
        }
 
    }
    
    //getyset

    public JCheckBox[][] getAsientosA() {
        return asientosA;
    }

    public void setAsientosA(JCheckBox[][] asientosA) {
        this.asientosA = asientosA;
    }

    public JCheckBox[][] getAsientosB1() {
        return asientosB1;
    }

    public void setAsientosB1(JCheckBox[][] asientosB1) {
        this.asientosB1 = asientosB1;
    }

    public JCheckBox[][] getAsientosB2() {
        return asientosB2;
    }

    public void setAsientosB2(JCheckBox[][] asientosB2) {
        this.asientosB2 = asientosB2;
    }

    public JCheckBox[][] getAsientosC() {
        return asientosC;
    }

    public void setAsientosC(JCheckBox[][] asientosC) {
        this.asientosC = asientosC;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(JButton btnRegresarMenu) {
        this.btnRegresar = btnRegresar;
    }

    public JTextField getTxtMontoPagar() {
        return txtMontoPagar;
    }

    public void setTxtMontoPagar(JTextField txtMontoPagar) {
        this.txtMontoPagar = txtMontoPagar;
    }

    public JTextField getTxtPrecioPagar() {
        return txtPrecioPagar;
    }

    public void setTxtPrecioPagar(JTextField txtPrecioPagar) {
        this.txtPrecioPagar = txtPrecioPagar;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        panel2 = new java.awt.Panel();
        btnRegresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panelAsientosA = new java.awt.Panel();
        panelAsientosC = new java.awt.Panel();
        panelAsientosB1 = new java.awt.Panel();
        panelAsientosB2 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMontoPagar = new javax.swing.JTextField();
        txtPrecioPagar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel2.setBackground(new java.awt.Color(25, 43, 55));

        btnRegresar.setBackground(new java.awt.Color(25, 43, 55));
        btnRegresar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/regreso2.png"))); // NOI18N
        btnRegresar.setToolTipText("Regresar");
        btnRegresar.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Regresar");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addGap(0, 644, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel4)))
                .addGap(12, 12, 12))
        );

        panel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        btnCancelar.setBackground(new java.awt.Color(153, 153, 153));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Aceptar");
        btnCancelar.setRolloverEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 480, 120, 32));

        btnAceptar.setBackground(new java.awt.Color(255, 102, 0));
        btnAceptar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Aceptar");
        btnAceptar.setToolTipText("Aceptar");
        btnAceptar.setRolloverEnabled(false);
        panel1.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 480, 120, 32));

        jLabel7.setBackground(new java.awt.Color(255, 51, 0));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Monto a pagar: ");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 110, -1));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 570, 30));

        jLabel5.setBackground(new java.awt.Color(255, 51, 0));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Precio a pagar: ");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 120, -1));

        javax.swing.GroupLayout panelAsientosALayout = new javax.swing.GroupLayout(panelAsientosA);
        panelAsientosA.setLayout(panelAsientosALayout);
        panelAsientosALayout.setHorizontalGroup(
            panelAsientosALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        panelAsientosALayout.setVerticalGroup(
            panelAsientosALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        panel1.add(panelAsientosA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 360, 150));

        javax.swing.GroupLayout panelAsientosCLayout = new javax.swing.GroupLayout(panelAsientosC);
        panelAsientosC.setLayout(panelAsientosCLayout);
        panelAsientosCLayout.setHorizontalGroup(
            panelAsientosCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        panelAsientosCLayout.setVerticalGroup(
            panelAsientosCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        panel1.add(panelAsientosC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 360, 150));

        javax.swing.GroupLayout panelAsientosB1Layout = new javax.swing.GroupLayout(panelAsientosB1);
        panelAsientosB1.setLayout(panelAsientosB1Layout);
        panelAsientosB1Layout.setHorizontalGroup(
            panelAsientosB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelAsientosB1Layout.setVerticalGroup(
            panelAsientosB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        panel1.add(panelAsientosB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 140, 140));

        javax.swing.GroupLayout panelAsientosB2Layout = new javax.swing.GroupLayout(panelAsientosB2);
        panelAsientosB2.setLayout(panelAsientosB2Layout);
        panelAsientosB2Layout.setHorizontalGroup(
            panelAsientosB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelAsientosB2Layout.setVerticalGroup(
            panelAsientosB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        panel1.add(panelAsientosB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, -1, 140));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/AsientoOcupado.png"))); // NOI18N
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/AsientoSeleccionado.png"))); // NOI18N
        panel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Ocupado");
        panel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, -1));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Seleccionado");
        panel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, -1));

        txtMontoPagar.setBackground(new java.awt.Color(255, 255, 255));
        txtMontoPagar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMontoPagar.setToolTipText("");
        txtMontoPagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel1.add(txtMontoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 160, 20));

        txtPrecioPagar.setEditable(false);
        txtPrecioPagar.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioPagar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPrecioPagar.setToolTipText("");
        txtPrecioPagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel1.add(txtPrecioPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 160, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(ElegirAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ElegirAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ElegirAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ElegirAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ElegirAsientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panelAsientosA;
    private java.awt.Panel panelAsientosB1;
    private java.awt.Panel panelAsientosB2;
    private java.awt.Panel panelAsientosC;
    private javax.swing.JTextField txtMontoPagar;
    private javax.swing.JTextField txtPrecioPagar;
    // End of variables declaration//GEN-END:variables
}
