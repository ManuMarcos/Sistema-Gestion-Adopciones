package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.AnimalController;
import modelo.dto.AnimalDto;
import modelo.enumeraciones.EstadoAnimal;
import modelo.enumeraciones.TipoAnimal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class VentanaRegistroAnimal extends JFrame implements ActionListener{

	
	private AnimalController controlador;
	
	private JPanel contentPane;
	private JTextField textFieldEspecie;
	private JTextField textFieldAltura;
	private JTextField textFieldPeso;
	private JTextField textFieldEdad;
	private JTextField textFieldId;
	private JButton botonCancelar ;
	private JButton botonGuardar;
	private JComboBox<TipoAnimal> comboBoxTipo;
	private JComboBox<EstadoAnimal> comboBoxEstado;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4 ;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;

	
	public VentanaRegistroAnimal() {
		setBounds(100, 100, 551, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		
		panel = new JPanel();
		
		setContentPane(panel);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(6, 2, 0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel labelTipo = new JLabel("Tipo");
		panel_3.add(labelTipo);
		
		
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JLabel labelEspecie = new JLabel("Especie");
		panel_4.add(labelEspecie);
		
		textFieldEspecie = new JTextField();
		panel_4.add(textFieldEspecie);
		textFieldEspecie.setColumns(10);
		
		panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JLabel labelAltura = new JLabel("Altura");
		panel_5.add(labelAltura);
		
		textFieldAltura = new JTextField();
		panel_5.add(textFieldAltura);
		textFieldAltura.setColumns(10);
		
		panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JLabel labelPeso = new JLabel("Peso");
		panel_6.add(labelPeso);
		
		textFieldPeso = new JTextField();
		panel_6.add(textFieldPeso);
		textFieldPeso.setColumns(10);
		
		panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		JLabel labelEdad = new JLabel("Edad aproximada");
		panel_7.add(labelEdad);
		
		textFieldEdad = new JTextField();
		panel_7.add(textFieldEdad);
		textFieldEdad.setColumns(10);
		
		panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		JLabel labelCondicion = new JLabel("Condición Médica");
		panel_8.add(labelCondicion);
		
		textFieldId = new JTextField();
		
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		botonGuardar = new JButton("Guardar");
		panel_1.add(botonGuardar);
		botonGuardar.addActionListener(this);
		
		botonCancelar = new JButton("Cancelar");
		panel_1.add(botonCancelar);
		botonCancelar.addActionListener(this);
		
		setTitle("Nuevo Animal");
	
	}
	
	public void setController(AnimalController controlador) {
		this.controlador = controlador;
		this.setComboBoxes();
	}

	public void mostrarDatos(AnimalDto animal) {
		comboBoxTipo.setSelectedItem(animal.getTipo());
		textFieldEspecie.setText(animal.getEspecie());
		textFieldAltura.setText(Integer.toString(animal.getAltura()));
		textFieldPeso.setText(Integer.toString(animal.getPeso()));
		textFieldEdad.setText(Integer.toString(fechaNacimientoToEdad(animal.getFecha_nac())));
		comboBoxEstado.setSelectedItem(animal.getEstado());
		textFieldId.setText(Integer.toString(animal.getId()));
	}
	
	
	
	private void setComboBoxes() {
		comboBoxTipo = new JComboBox<TipoAnimal>();
		comboBoxTipo.setModel(new DefaultComboBoxModel<>(TipoAnimal.values()));
		panel_3.add(comboBoxTipo);
		comboBoxEstado = new JComboBox<EstadoAnimal>();
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(EstadoAnimal.values()));
		panel_8.add(comboBoxEstado);
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
		textFieldId.setText("-1");
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
		else if(e.getSource() == botonGuardar) {
			try {
				AnimalDto animal = new AnimalDto();
				animal.setTipo((TipoAnimal) comboBoxTipo.getSelectedItem());
				animal.setEspecie(textFieldEspecie.getText());
				animal.setAltura(Integer.parseInt(textFieldAltura.getText()));
				animal.setPeso(Integer.parseInt(textFieldPeso.getText()));
				int edadAproximada = Integer.parseInt(textFieldEdad.getText());
				animal.setFecha_nac(calcularFechaNacimiento(edadAproximada));
				animal.setEstado((EstadoAnimal) comboBoxEstado.getSelectedItem());
				if(textFieldId.getText() != "-1") {
					animal.setId(Integer.parseInt(textFieldId.getText()));
				}
				controlador.cargarAnimal(animal);
				this.setVisible(false);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Error en el ingreso de datos", "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			
		}
	}

}
