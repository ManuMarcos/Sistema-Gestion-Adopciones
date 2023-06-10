package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaAnimal extends JFrame {

	private JPanel panelPrincipal;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnimal frame = new VentanaAnimal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAnimal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 361);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(5, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel);
		
		JLabel lblNewLabel = new JLabel("Tipo de Animal");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		panelPrincipal.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Altura");
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		panelPrincipal.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Peso");
		panel_2.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		panelPrincipal.add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Edad");
		panel_3.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_3.add(textField_3);
		textField_3.setColumns(20);
		
		JPanel panel_4 = new JPanel();
		panelPrincipal.add(panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("Condicion Medica");
		panel_4.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		panel_4.add(textField_4);
		textField_4.setColumns(20);
	}

}
