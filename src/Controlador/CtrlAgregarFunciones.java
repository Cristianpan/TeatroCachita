package Controlador;

import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DAO.DAOFuncion;
import DAO.DAOObra;
import Modelo.Funcion;
import Modelo.Obra;
import Vista.CrearFuncion;


public class CtrlAgregarFunciones implements ActionListener {
	
	private Funcion funcion;
	private CrearFuncion vista;
	
	public CtrlAgregarFunciones(Funcion funcion, CrearFuncion vista) {
		this.funcion = funcion;
		this.vista = vista;
		agregarObrasComboBox();
	}

	public void agregarObrasComboBox() {
        DAOObra daoObras= new DAOObra();
        ArrayList<Obra> obras = daoObras.obrasRegistradas();
        this.vista.getComboBoxObra().addItem("-Seleccionar Obra-");
        
        for (int i = 0; i < obras.size(); i++) {
            this.vista.getComboBoxObra().addItem(obras.get(i).getNombre());
        }
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.vista.getjDateChooser1()) {
            String obraSeleccionada = this.vista.getComboBoxObra().getSelectedItem().toString();

			if (!"-Seleccionar Obra-".equals(obraSeleccionada)) {
				SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
				String fecha = Format.format(this.vista.getjDateChooser1().getDate());
                
				DAOObra daoObra = new DAOObra();
				DAOFuncion daoFuncion = new DAOFuncion();

                Obra obra = daoObra.buscarObra(obraSeleccionada);
				ArrayList<Funcion> funciones = daoFuncion.buscarPorFecha(fecha);

				if (funciones.isEmpty()) {
					Funcion nuevaFuncion = new Funcion();

					try {
						nuevaFuncion.setFechaPresentacion(new Date(vista.getjDateChooser1().getDate().getTime()));
						nuevaFuncion.setHoraPresentacion(new Time(sdf.parse(vista.getComboBoxHora().toString()).getTime()));
						nuevaFuncion.setObraTeatral(obraSeleccionada);
					} catch (Exception e) {
						System.out.println("Error while parsing creating a new funcion");
					}

					daoFuncion.agregarFuncion(nuevaFuncion, obra.getId());
				}

				if (funciones.size() == 2) {
					this.vista.getBtnAgregar().setEnabled(false);
					// Implementar JOPTIONPane alerta de no horarios disponibles
				}

				// Si ya existe la de las 6 y la función tarda más de 2.5 horas no muestra nada el combobox de horas.
				for (Funcion funcionIt : funciones) {
					if ((funcionIt.getHoraPresentacion().equals(new Time(18, 0, 0))) && (obra.getDuracion() > 2.5)) {
						this.vista.getBtnAgregar().setEnabled(false);
						// Implementar JOPTIONPane alerta de no horarios disponibles
					}
				}
			}
		}
	}
}
