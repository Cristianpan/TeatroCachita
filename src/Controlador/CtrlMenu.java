/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAOUsuario;
import Modelo.User;
import Vista.Login;
import Vista.MenuAdmi;
import Vista.PanelDelMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diana
 */
public class CtrlMenu implements ActionListener{
    private MenuAdmi menu;

    public CtrlMenu(MenuAdmi menu) {
        this.menu = menu;
        
        this.menu.getBtnUsuarios().addActionListener(this);
        this.menu.getBtnObras().addActionListener(this);
        this.menu.getBtnFunciones().addActionListener(this);
        this.menu.getBtnReportes().addActionListener(this);
        this.menu.getBtnSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.getBtnUsuarios()) {
            menu.getBtnUsuarios().setBackground(new Color(255,102,0));
            menu.getBtnObras().setBackground(new Color(32,58,74));
            menu.getBtnFunciones().setBackground(new Color(32,58,74));
            PanelDelMenu panel= new PanelDelMenu();
            panel.setSize(190,110); //191, 92
            panel.setLocation(0, 0);
            menu.getPanelSubmenu().removeAll();
            menu.getPanelSubmenu().add(panel, BorderLayout.CENTER);
            menu.getPanelSubmenu().revalidate();
            menu.getPanelSubmenu().repaint();
            CtrlPanelMenuUsuarios menuUsuarios= new CtrlPanelMenuUsuarios(panel);
        }
        
        if (e.getSource() == menu.getBtnObras()) {
            menu.getBtnUsuarios().setBackground(new Color(32,58,74));
            menu.getBtnObras().setBackground(new Color(255,102,0));
            menu.getBtnFunciones().setBackground(new Color(32,58,74));
            PanelDelMenu panel= new PanelDelMenu();
            panel.setSize(190,110); //191, 92
            panel.setLocation(0, 0);
            menu.getPanelSubmenu().removeAll();
            menu.getPanelSubmenu().add(panel, BorderLayout.CENTER);
            menu.getPanelSubmenu().revalidate();
            menu.getPanelSubmenu().repaint();
            CtrlPanelMenuObras menuUsuarios= new CtrlPanelMenuObras(panel);
        }
        
        if (e.getSource() == menu.getBtnFunciones()) {
            menu.getBtnUsuarios().setBackground(new Color(32,58,74));
            menu.getBtnObras().setBackground(new Color(32,58,74));
            menu.getBtnFunciones().setBackground(new Color(255,102,0));
            PanelDelMenu panel= new PanelDelMenu();
            panel.setSize(190,110); //191, 92
            panel.setLocation(0, 0);
            menu.getPanelSubmenu().removeAll();
            menu.getPanelSubmenu().add(panel, BorderLayout.CENTER);
            menu.getPanelSubmenu().revalidate();
            menu.getPanelSubmenu().repaint();
            CtrlPanelMenuFunciones menuUsuarios= new CtrlPanelMenuFunciones(panel);
        }
        
        if (e.getSource() == menu.getBtnReportes()) {
            //IR A LOS REPORTES
            
        }
        
        if (e.getSource() == menu.getBtnSalir()) {
            this.menu.setVisible(false);
            
            Login frmLog = new Login();
            //Register frmRegister = new Register();  
            DAOUsuario consultas = new DAOUsuario(); 
            User user = new User(); 

            //CtrlRegister ctrlRegister = new CtrlRegister(user, consultas, frmRegister); 
            //frmRegister.setVisible(true);

            CtrlLogin ctrl = new CtrlLogin(user, consultas, frmLog);
            frmLog.setVisible(true); 
            
        }
        
        
    }
    
}
