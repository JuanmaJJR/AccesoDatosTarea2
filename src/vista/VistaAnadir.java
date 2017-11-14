package vista;

import controlador.Controlador;
import modelo.ModeloAux;
import modelo.ModeloSQL;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

public class VistaAnadir extends JFrame {
	

	private JPanel contentPane;
	private ModeloSQL modeloSQL;
	private ModeloAux modeloaux;
	private Controlador controlador;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtPosicion;
	private JComboBox<String> txtEquipo;
	private ArrayList<String> equipos;
	private JTextField equipoNombre;
	private boolean primeraVez = true;

	
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VistaAnadir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setForeground(SystemColor.activeCaptionBorder);
		panel.setBounds(0, 0, 794, 444);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAadirJugador = new JLabel("A\u00F1adir Jugador");
		lblAadirJugador.setFont(new Font("Sylfaen", Font.BOLD, 30));
		lblAadirJugador.setBounds(70, 11, 219, 40);
		panel.add(lblAadirJugador);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(70, 70, 86, 34);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(166, 80, 99, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblApellido.setBounds(70, 140, 75, 20);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(166, 140, 99, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblPosicion = new JLabel("Posicion:");
		lblPosicion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPosicion.setBounds(70, 218, 86, 20);
		panel.add(lblPosicion);
		
		txtPosicion = new JTextField();
		txtPosicion.setColumns(10);
		txtPosicion.setBounds(166, 221, 99, 20);
		panel.add(txtPosicion);
		
		JLabel lblEquipo = new JLabel("Equipo: ");
		lblEquipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEquipo.setBounds(70, 304, 75, 20);
		panel.add(lblEquipo);
		
		this.txtEquipo = new JComboBox();
		txtEquipo.setBounds(166, 304, 99, 20);
		panel.add(txtEquipo);
	
		
		
		JButton btnAadirJugador = new JButton("A\u00F1adir jugador");
		btnAadirJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.guardarDatos(txtNombre.getText(),txtApellido.getText(),txtPosicion.getText(),txtEquipo.getSelectedItem().toString());
			}
		});
		btnAadirJugador.setBounds(114, 410, 120, 23);
		panel.add(btnAadirJugador);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setForeground(new Color(191, 205, 219));
		panel_1.setBounds(390, 0, 404, 444);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAadirEquipo = new JLabel("A\u00F1adir Equipo");
		lblAadirEquipo.setBounds(103, 11, 219, 40);
		panel_1.add(lblAadirEquipo);
		lblAadirEquipo.setFont(new Font("Sylfaen", Font.BOLD, 30));
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(46, 68, 86, 34);
		panel_1.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		equipoNombre = new JTextField();
		equipoNombre.setEnabled(false);
		equipoNombre.setBounds(142, 74, 123, 20);
		panel_1.add(equipoNombre);
		equipoNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(31, 136, 101, 34);
		panel_1.add(lblDescripcion);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JTextArea equipoDescrip = new JTextArea();
		equipoDescrip.setEnabled(false);
		equipoDescrip.setBounds(146, 147, 120, 107);
		panel_1.add(equipoDescrip);
		
		JButton btnAadirEquipo = new JButton("A\u00F1adir equipo");
		btnAadirEquipo.setEnabled(false);
		btnAadirEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.anadirEquipo(equipoNombre.getText(),equipoDescrip.getText());
			}
		});
		btnAadirEquipo.setBounds(127, 410, 120, 23);
		panel_1.add(btnAadirEquipo);
		
		
		
	}
	
	
	public ModeloAux getModeloaux() {
		return modeloaux;
	}


	public void setModeloaux(ModeloAux modeloaux) {
		this.modeloaux = modeloaux;
	}


	public Controlador getControlador() {
		return controlador;
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


	public void cargaEquipos() {
		
			equipos = new ArrayList<String>();
			equipos = controlador.comboEquipos();
			for (int i = 0; i < equipos.size();i++) {
				txtEquipo.addItem(equipos.get(i));
			}
			primeraVez = false;
	
		
		
		// TODO Auto-generated method stub
		
	}
}
