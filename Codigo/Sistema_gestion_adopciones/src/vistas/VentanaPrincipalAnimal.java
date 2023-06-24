package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
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
import vistas.enumeraciones.CliViewNames;
import vistas.utils.ICliView;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipalAnimal extends JFrame implements ActionListener, TableModelListener, MouseListener, ICliView{

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTable table;
	private AnimalController controlador;
	private JPanel panelBotones;
	private JButton botonNuevo, botonActualizar;
	

	/**
	 * Create the frame.
	 */
	public VentanaPrincipalAnimal() {		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				procesar();
			}
		});
		
	}
	
	
	public void setearTabla() {
		DefaultTableModel modeloTablaAnimales = new DefaultTableModel();
		
		String[] columnas = {"Nro Ingreso", "Tipo", "Especie", "Estado", "Fecha Nacimiento", "Altura", "Peso"};
		modeloTablaAnimales.setColumnIdentifiers(columnas);
		
		
		List<AnimalDto> animales = controlador.listarAnimales();
		for (AnimalDto animal : animales) {
			modeloTablaAnimales.addRow(new Object[] {animal.getNroIngreso(),animal.getTipo(),animal.getEspecie(), animal.getEstado(), animal.getFecha_nac(), animal.getAltura(), animal.getPeso()});
		}
		
		table.setModel(modeloTablaAnimales);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setRowHeight(25);
		
		table.addMouseListener(this);
	}
	
	
	
	
	
	public void setController(AnimalController controlador) {
		this.controlador = controlador;
		this.setearTabla();
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
			this.setearTabla();
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
			controlador.mostrarVentanaDatos((int)this.table.getValueAt(filaSeleccionada, 0));
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

	@Override
	public CliViewNames procesar() {
		// TODO Auto-generated method stub
		
		return CliViewNames.BACK;
	}


}
