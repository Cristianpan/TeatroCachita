package Controlador;

import Modelo.*;
import DAO.*;
import ExcepcionesTeatro.ExcepcionCamposVacios;
import ExcepcionesTeatro.ExcepcionDAOObras;
import Vista.CrearObra;
import Vista.MenuAdmi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            try {
                esVacioInput();
                DAOObra daoObra= new DAOObra();
                if(daoObra.buscarObra(this.frmCrearObra.getTxtNombre().getText().trim()) == null){
                    obtenerDatosRegistro();
                    limpiarCampos();
                    daoObra.agregarObra(this.obra);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                }
            } catch (ExcepcionCamposVacios ex) {
                JOptionPane.showMessageDialog(frmCrearObra, ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmCrearObra, "El dato precio o duración es incorrecto");
            } catch (ExcepcionDAOObras ex) {
                 JOptionPane.showMessageDialog(frmCrearObra, ex.getMessage());
            } catch (Exception ex) {}
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
    
    public void esVacioInput() throws ExcepcionCamposVacios{
        if((frmCrearObra.getTxtNombre().getText().isEmpty() ||
        this.frmCrearObra.getTxtDuracion().getText().isEmpty()||
        this.frmCrearObra.getTxtGenero().getText().isEmpty()||
        this.frmCrearObra.getTxtPrecio().getText().isEmpty()||
        this.frmCrearObra.getTxtPrimerActor().getText().isEmpty()||
        this.frmCrearObra.getTxtSegundoActor().getText().isEmpty()) || 
        this.frmCrearObra.getTxtResumenTematico().getText().isEmpty()){
            throw new ExcepcionCamposVacios("Todos los campos son obligatorios");
        }
    }
    
    public void obtenerDatosRegistro() throws Exception{
        this.obra.setNombre(frmCrearObra.getTxtNombre().getText().trim());
        this.obra.setGenero(frmCrearObra.getTxtGenero().getText().trim());
        this.obra.setPrimerActor(frmCrearObra.getTxtPrimerActor().getText().trim());
        this.obra.setSegundoActor(frmCrearObra.getTxtSegundoActor().getText().trim());
        this.obra.setResumen(frmCrearObra.getTxtResumenTematico().getText().trim());
        this.obra.setPrecioBoleto(Double.parseDouble(frmCrearObra.getTxtPrecio().getText().trim()));
        this.obra.setDuracion(Integer.parseInt(frmCrearObra.getTxtDuracion().getText().trim()));
        
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
