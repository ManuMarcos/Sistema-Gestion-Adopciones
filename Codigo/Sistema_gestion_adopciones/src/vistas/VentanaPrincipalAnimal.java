package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controladores.AnimalController;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Font;

public class VentanaPrincipalAnimal extends JFrame implements ActionListener, TableModelListener, MouseListener{

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
		setBounds(100, 100, 930, 476);
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
	
	public void setTabla() {
		DefaultTableModel modeloTablaAnimales = new DefaultTableModel();
		
		String[] columnas = {"Id", "Tipo", "Especie", "Estado", "Fecha Nacimiento", "Altura", "Peso"};
		modeloTablaAnimales.setColumnIdentifiers(columnas);
		
		
		List<AnimalDto> animales = controlador.listarAnimales();
		for (AnimalDto animal : animales) {
			modeloTablaAnimales.addRow(new Object[] {animal.getId(),animal.getTipo(),animal.getEspecie(), animal.getEstado(), animal.getFecha_nac(), animal.getAltura(), animal.getPeso()});
		}
		
		table.setModel(modeloTablaAnimales);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setRowHeight(25);
		
//		//Para que la columna Tipo tenga un ComboBox asociado
//		TableColumn comboColumnaTipo = table.getColumnModel().getColumn(1);
//		JComboBox<TipoAnimal> comboBoxTipo = new JComboBox<TipoAnimal>();
//		comboBoxTipo.setModel(new DefaultComboBoxModel<>(TipoAnimal.values()));
//		comboColumnaTipo.setCellEditor(new DefaultCellEditor(comboBoxTipo));
//		
//		//Para que la columna Tipo tenga un ComboBox asociado
//		TableColumn comboColumnaEstado = table.getColumnModel().getColumn(3);
//		JComboBox<EstadoAnimal> comboBoxEstado = new JComboBox<EstadoAnimal>();
//		comboBoxEstado.setModel(new DefaultComboBoxModel<>(EstadoAnimal.values()));
//		comboColumnaEstado.setCellEditor(new DefaultCellEditor(comboBoxEstado));
//	
//		table.getModel().addTableModelListener(this);
		table.addMouseListener(this);
	}
	
	
	public void setController(AnimalController controlador) {
		this.controlador = controlador;
		this.setTabla();
	}
	
	private void agregarTabla() {
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
			this.setTabla();
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Fila: " + e.getLastRow() + " Columna: " + e.getColumn() );
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int filaSeleccionada = this.table.getSelectedRow();
		if(filaSeleccionada != -1) {
			//Se retorna la columna 0 (id) de la fila seleccionada
			controlador.mostrarVentanaRegistro((int)this.table.getValueAt(filaSeleccionada, 0));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
