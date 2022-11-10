/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.PanelMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diana
 */
public class CtrlPanelMenuUsuarios implements ActionListener{
    private PanelMenu panelMenu;

    public CtrlPanelMenuUsuarios(PanelMenu panelMenu) {
        this.panelMenu = panelMenu;
        
        this.panelMenu.getBtnAgregar().addActionListener(this);
        this.panelMenu.getBtnModificar().addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.panelMenu.getBtnAgregar()){
         //     System.out.println("IR A LA VENTANA AGREGAR USUARIOS");  
        }
    }
    
    
    
    
    
}
