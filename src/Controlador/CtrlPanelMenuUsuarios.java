/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.PanelDelMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diana
 */
public class CtrlPanelMenuUsuarios implements ActionListener{
    private PanelDelMenu panelMenu;

    public CtrlPanelMenuUsuarios(PanelDelMenu panelMenu) {
        this.panelMenu = panelMenu;
        
        this.panelMenu.getBtnAgregarMenu().addActionListener(this);
        this.panelMenu.getBtnModificarMenu().addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panelMenu.getBtnAgregarMenu()){
         //     System.out.println("IR A LA VENTANA AGREGAR USUARIOS");  
        }
    }
    
    
    
    
    
}
