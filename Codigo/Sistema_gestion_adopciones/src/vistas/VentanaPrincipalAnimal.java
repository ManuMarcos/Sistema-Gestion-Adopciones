package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controladores.AnimalController;
import modelo.dto.AnimalDto;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class VentanaPrincipalAnimal extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTable table;
	private AnimalController controlador;
	private JPanel panelBotones;
	private JButton botonNuevo;
	private JButton botonActualizar;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipalAnimal() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		setTitle("Animales");
		setLocationRelativeTo(null);
		
		this.agregarBotones();
		this.agregarTabla();
	}
	
	public void setListadoAnimales() {
		DefaultTableModel modeloTablaAnimales = new DefaultTableModel();
		
		String[] columnas = {"Tipo", "Especie", "Estado", "Fecha Nacimiento", "Altura", "Peso"};
		modeloTablaAnimales.setColumnIdentifiers(columnas);
		
		List<AnimalDto> animales = controlador.listarAnimales();
		for (AnimalDto animal : animales) {
			modeloTablaAnimales.addRow(new Object[] {animal.getTipo(),animal.getEspecie(), animal.getEstado(), animal.getFecha_nac(), animal.getAltura(), animal.getPeso()});
		}
		
		table.setModel(modeloTablaAnimales);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
	}
	
	public void setController(AnimalController controlador) {
		this.controlador = controlador;
		this.setListadoAnimales();
	}
	
	private void agregarTabla() {
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void agregarBotones() {
		botonNuevo = new JButton("Nuevo Animal");
		botonNuevo.addActionListener(this);
		panelBotones.add(botonNuevo);
		
		botonActualizar = new JButton("Actualizar");
		botonActualizar.addActionListener(this);
		panelBotones.add(botonActualizar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == botonNuevo) {
			controlador.mostrarVentanaRegistro();
		}
		else if (e.getSource() == botonActualizar){
			this.setListadoAnimales();
		}
	}

}
