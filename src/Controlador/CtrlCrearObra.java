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
                    obtenerDatosRegistro();
                    limpiarCampos();

                    if (daoObra.agregarObra(this.obra))
                        JOptionPane.showMessageDialog(null, "Registro exitoso");
                    else
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
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
        frmCrearObra.getTxtDuracion().getText().isEmpty()|
        frmCrearObra.getTxtGenero().getText().isEmpty()|
        frmCrearObra.getTxtPrecio().getText().isEmpty()|
        frmCrearObra.getTxtPrimerActor().getText().isEmpty()|
        frmCrearObra.getTxtSegundoActor().getText().isEmpty());
    }
    
    public void obtenerDatosRegistro() {
        obra.setNombre(frmCrearObra.getTxtNombre().getText().trim());
        obra.setDuracion(Double.parseDouble(frmCrearObra.getTxtDuracion().getText().trim()));
        obra.setGenero(frmCrearObra.getTxtGenero().getText().trim());
        obra.setPrecioBoleto(Double.parseDouble(frmCrearObra.getTxtPrecio().getText().trim()));
        obra.setPrimerActor(frmCrearObra.getTxtPrimerActor().getText().trim());
        obra.setSegundoActor(frmCrearObra.getTxtSegundoActor().getText().trim());
        obra.setResumen(frmCrearObra.getTxtResumenTematico().getText().trim());
    }
    
    public void limpiarCampos(){
        frmCrearObra.getTxtNombre().setText(null);
        frmCrearObra.getTxtDuracion().setText(null);
        frmCrearObra.getTxtGenero().setText(null);
        frmCrearObra.getTxtPrecio().setText(null);
        frmCrearObra.getTxtPrimerActor().setText(null);
        frmCrearObra.getTxtSegundoActor().setText(null);
        frmCrearObra.getTxtResumenTematico().setText(null);
    }
    
}
