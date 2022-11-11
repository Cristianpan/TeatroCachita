/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import DAO.*;
import Vista.CrearObra;
import Vista.CrearObra;
import Vista.MenuAdmi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author diana
 */
public class CtrlCrearObra implements ActionListener{
    private Obra obra;
    private CrearObra frmCrearObra; 

    public CtrlCrearObra(Obra obra, CrearObra frmCrearObra) {
        this.obra = obra;
        this.frmCrearObra = frmCrearObra;
        
        this.frmCrearObra.getBtnAgregar().addActionListener(this);
        this.frmCrearObra.getBtnCancelar().addActionListener(this);
        this.frmCrearObra.getBtnRegresarMenu().addActionListener(this);
        
        this.frmCrearObra.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //botón agregar
        if(e.getSource() == this.frmCrearObra.getBtnAgregar()){
            if (esVacioInput()){
                JOptionPane.showMessageDialog(this.frmCrearObra, "Todos los campos son obligatorios");
            }else{
                DAOObra daoObra= new DAOObra();
                if(daoObra.buscarObra(this.frmCrearObra.getTxtNombre().getText().trim()) == null){
                    boolean checar=obtenerDatosRegistro();
                    
                    if(checar==true){
                        limpiarCampos();
                        if (daoObra.agregarObra(this.obra))
                        JOptionPane.showMessageDialog(null, "Registro exitoso");
                        else
                            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                        }
                }
            }
        }
        
        //boton cancelar
        if (e.getSource() == this.frmCrearObra.getBtnCancelar()){
         int opcion = JOptionPane.showConfirmDialog(frmCrearObra, "¿Desea cancelar la acción?", null,
                    JOptionPane.YES_NO_OPTION, -1);
            if (opcion == 0) {
                limpiarCampos();
            }
        }
        
        //regresar al menu
        if (e.getSource() == this.frmCrearObra.getBtnRegresarMenu()) {
            new CtrlMenu(new MenuAdmi());
            this.frmCrearObra.setVisible(false);
            this.frmCrearObra.dispose();
        }
    }
    
    public boolean esVacioInput(){
        return(frmCrearObra.getTxtNombre().getText().isEmpty() |
        this.frmCrearObra.getTxtDuracion().getText().isEmpty()|
        this.frmCrearObra.getTxtGenero().getText().isEmpty()|
        this.frmCrearObra.getTxtPrecio().getText().isEmpty()|
        this.frmCrearObra.getTxtPrimerActor().getText().isEmpty()|
        this.frmCrearObra.getTxtSegundoActor().getText().isEmpty()) |
        this.frmCrearObra.getTxtResumenTematico().getText().isEmpty();
    }
    
    public boolean obtenerDatosRegistro() {
        try {
            this.obra.setNombre(frmCrearObra.getTxtNombre().getText().trim());
            this.obra.setDuracion(Double.parseDouble(frmCrearObra.getTxtDuracion().getText().trim()));
            this.obra.setGenero(frmCrearObra.getTxtGenero().getText().trim());
            this.obra.setPrecioBoleto(Double.parseDouble(frmCrearObra.getTxtPrecio().getText().trim()));
            this.obra.setPrimerActor(frmCrearObra.getTxtPrimerActor().getText().trim());
            this.obra.setSegundoActor(frmCrearObra.getTxtSegundoActor().getText().trim());
            this.obra.setResumen(frmCrearObra.getTxtResumenTematico().getText().trim());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmCrearObra, "Algún dato no corresponde al campo");
             return false;
        }
    }
    
    public void limpiarCampos(){
        this.frmCrearObra.getTxtNombre().setText(null);
        this.frmCrearObra.getTxtDuracion().setText(null);
        this.frmCrearObra.getTxtGenero().setText(null);
        this.frmCrearObra.getTxtPrecio().setText(null);
        this.frmCrearObra.getTxtPrimerActor().setText(null);
        this.frmCrearObra.getTxtSegundoActor().setText(null);
        this.frmCrearObra.getTxtResumenTematico().setText(null);
    }
    
}
