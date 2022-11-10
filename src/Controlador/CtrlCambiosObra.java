/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAOObra;
import Modelo.Obra;
import Vista.CambiosObra;
import Vista.MenuAdmi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author diana
 */
public class CtrlCambiosObra implements ActionListener{
    private Obra obra;
    private CambiosObra frmCambiosObra;

    public CtrlCambiosObra(Obra obra, CambiosObra frmCambiosObra) {
        this.obra = obra;
        this.frmCambiosObra = frmCambiosObra;
        agregarObrasComboBox();
        
        this.frmCambiosObra.getBtnCancelar().addActionListener(this);
        this.frmCambiosObra.getBtnEliminar().addActionListener(this);
        this.frmCambiosObra.getBtnModificar().addActionListener(this);
        this.frmCambiosObra.getBtnRegresarMenu().addActionListener(this);
        this.frmCambiosObra.getComboBoxObra().addActionListener(this);
        this.frmCambiosObra.setVisible(true);
        
        
    }
    
    public void agregarObrasComboBox(){
        DAOObra daoObras= new DAOObra();
        ArrayList<Obra> obras= daoObras.obrasRegistradas();
        
        for (int i = 0; i < obras.size(); i++) {
            this.frmCambiosObra.getComboBoxObra().addItem(obras.get(i).getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //agregar todos los datos
        if(e.getSource() == this.frmCambiosObra.getComboBoxObra()){
            String obraSeleccionada= this.frmCambiosObra.getComboBoxObra().getSelectedItem().toString();
            if(obraSeleccionada != "-Seleccionar Obra-"){
                DAOObra daoObra= new DAOObra();
                Obra obra= daoObra.buscarObra(obraSeleccionada);
                rellenarCampos(obra);
            }
        }
        
        //boton cancelar
        if (e.getSource() == this.frmCambiosObra.getBtnCancelar()){
            int opcion = JOptionPane.showConfirmDialog(frmCambiosObra, "¿Desea cancelar la acción?", null,
                    JOptionPane.YES_NO_OPTION, -1);
            if (opcion == 0) {
                limpiarCampos();
            }
        }
        
        //regresar al menu
        if (e.getSource() == this.frmCambiosObra.getBtnRegresarMenu()) {
            int opcion = JOptionPane.showConfirmDialog(frmCambiosObra, "¿Está seguro de regresar al menú?", null,
                    JOptionPane.YES_NO_OPTION, 1);
            if (opcion == 0) {
                new CtrlMenu(new MenuAdmi());
                this.frmCambiosObra.setVisible(false);
                this.frmCambiosObra.dispose();
            }
        }
        
        //modificar
        if (e.getSource() == this.frmCambiosObra.getBtnModificar()){
                if(!esVacioInput())  {
                    //me falta verificar que la obra no exista ya
                    String obraSeleccionada= this.frmCambiosObra.getComboBoxObra().getSelectedItem().toString();
                    DAOObra daoObra= new DAOObra();
                    Obra obra= daoObra.buscarObra(obraSeleccionada);
                    
                    if (daoObra.actualizarObra(obra)) {
                            JOptionPane.showMessageDialog(frmCambiosObra, "Actualización exitosa");
                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(frmCambiosObra, "Algo ha salido mal");
                        }
                }else{
                     JOptionPane.showMessageDialog(frmCambiosObra, "Todos los campos son obligatorios");
                }           
        }
    }
    
    
    
    public boolean esVacioInput(){
        return(frmCambiosObra.getTxtNombre().getText().isEmpty() |
        frmCambiosObra.getTxtDuracion().getText().isEmpty()|
        frmCambiosObra.getTxtGenero().getText().isEmpty()|
        frmCambiosObra.getTxtPrecio().getText().isEmpty()|
        frmCambiosObra.getTxtPrimerActor().getText().isEmpty()|
        frmCambiosObra.getTxtSegundoActor().getText().isEmpty());
    }
    
    public void limpiarCampos(){
        frmCambiosObra.getTxtNombre().setText(null);
        frmCambiosObra.getTxtDuracion().setText(null);
        frmCambiosObra.getTxtGenero().setText(null);
        frmCambiosObra.getTxtPrecio().setText(null);
        frmCambiosObra.getTxtPrimerActor().setText(null);
        frmCambiosObra.getTxtSegundoActor().setText(null);
        frmCambiosObra.getTxtResumenTematico().setText(null);
        
        frmCambiosObra.getComboBoxObra().setSelectedItem("-Seleccionar Obra-");
    }
    
    public void rellenarCampos(Obra obra){
        frmCambiosObra.getTxtNombre().setText(obra.getNombre());
        frmCambiosObra.getTxtDuracion().setText(Double.toString(obra.getDuracion()));
        frmCambiosObra.getTxtGenero().setText(obra.getGenero());
        frmCambiosObra.getTxtPrecio().setText(Double.toString(obra.getPrecioBoleto()));
        frmCambiosObra.getTxtPrimerActor().setText(obra.getPrimerActor());
        frmCambiosObra.getTxtSegundoActor().setText(obra.getSegundoActor());
        frmCambiosObra.getTxtResumenTematico().setText(obra.getResumen());
    }
    
}
