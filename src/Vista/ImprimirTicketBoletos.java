/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * 
 */
public class ImprimirTicketBoletos extends javax.swing.JFrame {

    /**
     * Creates new form ImprimirTicketBoletos
     */
    public ImprimirTicketBoletos() {
        initComponents();
        
//        BoletoVista boleto= new BoletoVista();
//        boleto.setSize(500,240);
//        boleto.setLocation(0,0);
//        
//        scrollPaneBoletos.removeAll();
//        scrollPaneBoletos.add(boleto, BorderLayout.CENTER);
//        scrollPaneBoletos.revalidate();
//        scrollPaneBoletos.repaint();
        
       
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(JButton btnRegresar) {
        this.btnRegresar = btnRegresar;
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public void setBtnSiguiente(JButton btnSiguiente) {
        this.btnSiguiente = btnSiguiente;
    }

    public TextArea getTxtTicket() {
        return txtTicket;
    }

    public void setTxtTicket(TextArea txtTicket) {
        this.txtTicket = txtTicket;
    }

    public Panel getPanelBoletos() {
        return panelBoletos;
    }

    public void setPanelBoletos(Panel panelBoletos) {
        this.panelBoletos = panelBoletos;
    }

    public Label getTxtAsiento() {
        return txtAsiento;
    }

    public void setTxtAsiento(Label txtAsiento) {
        this.txtAsiento = txtAsiento;
    }

    public Label getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Label txtFecha) {
        this.txtFecha = txtFecha;
    }

    public Label getTxtHora() {
        return txtHora;
    }

    public void setTxtHora(Label txtHora) {
        this.txtHora = txtHora;
    }

    

    public Label getTxtNombreObra() {
        return txtNombreObra;
    }

    public void setTxtNombreObra(Label txtNombreObra) {
        this.txtNombreObra = txtNombreObra;
    }

   
    
   


    

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel7 = new java.awt.Panel();
        panel6 = new java.awt.Panel();
        jLabel5 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        panel9 = new java.awt.Panel();
        jLabel6 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        panel4 = new java.awt.Panel();
        txtTicket = new java.awt.TextArea();
        panelBoletos = new java.awt.Panel();
        txtNombreObra = new java.awt.Label();
        jLabel7 = new javax.swing.JLabel();
        txtAsiento = new java.awt.Label();
        txtHora = new java.awt.Label();
        label9 = new java.awt.Label();
        txtFecha = new java.awt.Label();
        label11 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel7.setBackground(new java.awt.Color(255, 255, 254));
        panel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel6.setBackground(new java.awt.Color(25, 43, 55));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Agregar Obras");

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addContainerGap(623, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel7.add(panel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 820, 50));

        btnSiguiente.setBackground(new java.awt.Color(255, 102, 0));
        btnSiguiente.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setText(">");
        btnSiguiente.setToolTipText("");
        panel7.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 80, 30));

        panel9.setBackground(new java.awt.Color(255, 102, 0));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Salir");

        btnRegresar.setBackground(new java.awt.Color(255, 102, 0));
        btnRegresar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/regreso.png"))); // NOI18N
        btnRegresar.setToolTipText("Regresar");
        btnRegresar.setBorder(null);

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(0, 746, Short.MAX_VALUE))
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        panel7.add(panel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 30));

        panel4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        panel7.add(panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 820, 30));

        txtTicket.setEditable(false);
        panel7.add(txtTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 250, 280));

        panelBoletos.setBackground(new java.awt.Color(25, 43, 55));
        panelBoletos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreObra.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombreObra.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreObra.setText("-");
        panelBoletos.add(txtNombreObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Img/Imagen1.png"))); // NOI18N
        panelBoletos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 100));

        txtAsiento.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        txtAsiento.setForeground(new java.awt.Color(255, 153, 0));
        txtAsiento.setText("-");
        panelBoletos.add(txtAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 100, -1));

        txtHora.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtHora.setForeground(new java.awt.Color(255, 255, 255));
        txtHora.setText("-");
        panelBoletos.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        label9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        label9.setForeground(new java.awt.Color(255, 255, 255));
        label9.setText("FUNCIÓN:");
        panelBoletos.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        txtFecha.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(255, 255, 255));
        txtFecha.setText("-");
        panelBoletos.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        label11.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        label11.setForeground(new java.awt.Color(255, 153, 0));
        label11.setText("CHACHITA");
        panelBoletos.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        panel7.add(panelBoletos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 490, 230));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
            java.util.logging.Logger.getLogger(ImprimirTicketBoletos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImprimirTicketBoletos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImprimirTicketBoletos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImprimirTicketBoletos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImprimirTicketBoletos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private java.awt.Label label11;
    private java.awt.Label label9;
    private java.awt.Panel panel4;
    private java.awt.Panel panel6;
    private java.awt.Panel panel7;
    private java.awt.Panel panel9;
    private java.awt.Panel panelBoletos;
    private java.awt.Label txtAsiento;
    private java.awt.Label txtFecha;
    private java.awt.Label txtHora;
    private java.awt.Label txtNombreObra;
    private java.awt.TextArea txtTicket;
    // End of variables declaration//GEN-END:variables
}
