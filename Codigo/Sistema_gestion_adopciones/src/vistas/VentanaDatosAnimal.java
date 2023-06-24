package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.AnimalController;
import excepciones.CodigoError;
import excepciones.HandledException;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;
import modelo.exportacion.FormatoExportacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class VentanaDatosAnimal extends JFrame implements ActionListener{

	
	private AnimalController controlador;
	
	private JTextField textFieldEspecie, textFieldAltura,textFieldPeso, textFieldEdad, textFieldNroIngreso;
	private JButton botonCancelar, botonGuardar;
	private JComboBox<TipoAnimal> comboBoxTipo;
	private JComboBox<EstadoAnimal> comboBoxEstado;
	private JPanel panelPrincipal,  panelDatos, panelBotones , panelTipo, panelEspecie, panelAltura, panelPeso, panelEdad, 
	panelEstado, panelNroIngreso;
	private JLabel labelNroIngreso, labelTipo, labelEspecie, labelAltura, labelPeso, labelEdad, labelCondicion;
	private JMenuBar menuBar;
	private JMenu menuExportar;
	private JMenuItem itemPdf, itemExcel;

	
	public VentanaDatosAnimal() {
		setBounds(100, 100, 551, 379);
		setLocationRelativeTo(null);
		setTitle("Nuevo Animal");
		
		this.agregarPaneles();
		this.agregarMenu();
		this.agregarLabels();
		this.agregarTextFields();
		this.agregarBotones();
	
	}
	
	public void setController(AnimalController controlador) {
		this.controlador = controlador;
		this.setComboBoxes();
	}

	public void mostrarDatos(AnimalDto animal) {
		this.menuBar.setVisible(true);
		this.botonGuardar.setActionCommand("ACTUALIZAR_ANIMAL");
		this.textFieldNroIngreso.setEditable(false);
		comboBoxTipo.setSelectedItem(animal.getTipo());
		textFieldEspecie.setText(animal.getEspecie());
		textFieldAltura.setText(Integer.toString(animal.getAltura()));
		textFieldPeso.setText(Integer.toString(animal.getPeso()));
		textFieldEdad.setText(Integer.toString(fechaNacimientoToEdad(animal.getFecha_nac())));
		comboBoxEstado.setSelectedItem(animal.getEstado());
		textFieldNroIngreso.setText(Integer.toString(animal.getNroIngreso()));
	}
	
	public void mostrarMensajeInfo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void registrarAnimal() {
		this.limpiar();
		this.textFieldNroIngreso.setEditable(true);
		this.botonGuardar.setActionCommand("REGISTRAR_ANIMAL");
		this.menuBar.setVisible(false);
	}
	
	private void agregarPaneles() {
		//Panel Principal
		panelPrincipal = new JPanel();
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		//Panel Datos
		panelDatos = new JPanel();
		panelPrincipal.add(panelDatos);
		panelDatos.setLayout(new GridLayout(7, 2, 0, 0));
		
		//Panel Nro Ingreso
		panelNroIngreso = new JPanel();
		panelDatos.add(panelNroIngreso);
		
		//Panel Tipo
		panelTipo = new JPanel();
		panelDatos.add(panelTipo);
		
		//Panel Especie
		panelEspecie = new JPanel();
		panelDatos.add(panelEspecie);
		
		//Panel Altura
		panelAltura = new JPanel();
		panelDatos.add(panelAltura);
		
		//Panel Peso
		panelPeso = new JPanel();
		panelDatos.add(panelPeso);
		
		//Panel Edad
		panelEdad = new JPanel();
		panelDatos.add(panelEdad);
		
		//Panel Estado
		panelEstado = new JPanel();
		panelDatos.add(panelEstado);
		
		//Panel Botones (SUR : BORDERLAYOUT)
		panelBotones = new JPanel();
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
	}
	
	
	private void agregarLabels() {
		labelNroIngreso = new JLabel("N° de Ingreso");
		panelNroIngreso.add(labelNroIngreso);
		
		labelTipo = new JLabel("Tipo");
		panelTipo.add(labelTipo);
		
		labelEspecie = new JLabel("Especie");
		panelEspecie.add(labelEspecie);
		
		labelAltura = new JLabel("Altura");
		panelAltura.add(labelAltura);
		
		labelPeso = new JLabel("Peso");
		panelPeso.add(labelPeso);
		
		labelEdad = new JLabel("Edad aproximada");
		panelEdad.add(labelEdad);
		
		labelCondicion = new JLabel("Condición Médica");
		panelEstado.add(labelCondicion);
	}
	
	private void agregarTextFields() {
		textFieldNroIngreso = new JTextField();
		panelNroIngreso.add(textFieldNroIngreso);
		textFieldNroIngreso.setColumns(10);
		
		textFieldEspecie = new JTextField();
		panelEspecie.add(textFieldEspecie);
		textFieldEspecie.setColumns(10);
		
		textFieldAltura = new JTextField();
		panelAltura.add(textFieldAltura);
		textFieldAltura.setColumns(10);

		textFieldPeso = new JTextField();
		panelPeso.add(textFieldPeso);
		textFieldPeso.setColumns(10);

		textFieldEdad = new JTextField();
		panelEdad.add(textFieldEdad);
		textFieldEdad.setColumns(10);

	}
	
	private void agregarBotones() {
		botonGuardar = new JButton("Guardar");
		panelBotones.add(botonGuardar);
		botonGuardar.addActionListener(this);
		
		botonCancelar = new JButton("Cancelar");
		panelBotones.add(botonCancelar);
		botonCancelar.addActionListener(this);
	}
	
	private void setComboBoxes() {
		comboBoxTipo = new JComboBox<TipoAnimal>();
		comboBoxTipo.setModel(new DefaultComboBoxModel<>(TipoAnimal.values()));
		panelTipo.add(comboBoxTipo);
		comboBoxEstado = new JComboBox<EstadoAnimal>();
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(EstadoAnimal.values()));
		panelEstado.add(comboBoxEstado);
	}
	
	private int fechaNacimientoToEdad(LocalDate fechaNacimiento) {
		LocalDate fechaActual = LocalDate.now();
		return fechaActual.getYear() - fechaNacimiento.getYear();
	}
	
	public void limpiar() {
		comboBoxTipo.setSelectedItem(TipoAnimal.DOMESTICO);
		comboBoxEstado.setSelectedItem(EstadoAnimal.SALUDABLE);
		textFieldEspecie.setText("");
		textFieldAltura.setText("");
		textFieldPeso.setText("");
		textFieldEdad.setText("");
		textFieldNroIngreso.setText("");
	}
	
	private void agregarMenu(){
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuExportar = new JMenu("Exportar");
		menuBar.add(menuExportar);
	
		itemPdf = new JMenuItem("Pdf");
		itemPdf.addActionListener(this);
		menuExportar.add(itemPdf);
		
		itemExcel = new JMenuItem("Excel");
		itemExcel.addActionListener(this);
		menuExportar.add(itemExcel);
	}
	
	private AnimalDto obtenerDatosCargados() throws HandledException{
		AnimalDto animal = null;
		try {
			animal = new AnimalDto();
			animal.setTipo((TipoAnimal) comboBoxTipo.getSelectedItem());
			animal.setEspecie(textFieldEspecie.getText());
			animal.setAltura(Integer.parseInt(textFieldAltura.getText()));
			animal.setPeso(Integer.parseInt(textFieldPeso.getText()));
			int edadAproximada = Integer.parseInt(textFieldEdad.getText());
			animal.setFecha_nac(calcularFechaNacimiento(edadAproximada));
			animal.setEstado((EstadoAnimal) comboBoxEstado.getSelectedItem());
			animal.setNroIngreso(Integer.parseInt(textFieldNroIngreso.getText()));
		}
		catch(Exception ex){
			throw new HandledException(CodigoError.INPUT_DATA_ERROR, "Error en el ingreso de datos");
		}
		return animal;
	}
	
	private LocalDate calcularFechaNacimiento(int edadAproximada) {
		LocalDate fechaActual = LocalDate.now();
		return fechaActual.minusYears(edadAproximada);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		if (e.getSource() == botonCancelar) {
			this.dispose();
		}
		else if(e.getActionCommand().equals("REGISTRAR_ANIMAL")) {
			AnimalDto animal;
			try {
				animal = this.obtenerDatosCargados();
				
				if(controlador.existeAnimal(animal)) {
					JOptionPane.showMessageDialog(this, "El nro de ingreso ya existe", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					controlador.cargarAnimal(animal);
					this.setVisible(false);
					controlador.actualizarTabla();
				}
			} catch (HandledException error) {
				JOptionPane.showMessageDialog(this, error.getMessage() + "\nCodigo: " + error.getCodigo(), "Error", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getActionCommand().equals("ACTUALIZAR_ANIMAL")) {
			AnimalDto animal;
			try {
				animal = this.obtenerDatosCargados();
				
				if(controlador.actualizarAnimal(animal)) {
					JOptionPane.showMessageDialog(this, "Animal actualizado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				this.setVisible(false);
				controlador.actualizarTabla();
				}
				
			 	catch (HandledException error) {
				// TODO Auto-generated catch block
			 		JOptionPane.showMessageDialog(this, error.getMessage() + "\nCodigo: " + error.getCodigo(), "Error", 
							JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == itemPdf) {
			String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo PDF");
			controlador.exportarFichaMedica(Integer.parseInt(textFieldNroIngreso.getText()), FormatoExportacion.PDF, nombreArchivo);
		}
		else if(e.getSource() == itemExcel) {
			String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo Excel");
			controlador.exportarFichaMedica(Integer.parseInt(textFieldNroIngreso.getText()), FormatoExportacion.EXCEL, nombreArchivo);
		}
	}

}
