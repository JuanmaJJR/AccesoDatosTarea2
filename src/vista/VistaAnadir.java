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

public class VistaAnadir extends JFrame {
	

	private JPanel contentPane;
	private ModeloSQL modeloSQL;
	private ModeloAux modeloaux;
	private Controlador controlador;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtPosicion;
	private JComboBox<String> txtEquipo;
	private ArrayList<String> equipos = new ArrayList<String>();

	
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VistaAnadir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAadirJugador = new JLabel("A\u00F1adir Jugador");
		lblAadirJugador.setFont(new Font("Sylfaen", Font.BOLD, 30));
		lblAadirJugador.setBounds(102, 11, 219, 40);
		panel.add(lblAadirJugador);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 70, 46, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(65, 67, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 108, 46, 14);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(65, 105, 86, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblPosicion = new JLabel("Posicion:");
		lblPosicion.setBounds(251, 70, 46, 14);
		panel.add(lblPosicion);
		
		txtPosicion = new JTextField();
		txtPosicion.setColumns(10);
		txtPosicion.setBounds(307, 67, 86, 20);
		panel.add(txtPosicion);
		
		JLabel lblEquipo = new JLabel("Equipo: ");
		lblEquipo.setBounds(251, 108, 46, 14);
		panel.add(lblEquipo);
		
		this.txtEquipo = new JComboBox();
		txtEquipo.setBounds(307, 105, 86, 20);
		panel.add(txtEquipo);
	
		
		
		JButton btnAadirJugador = new JButton("A\u00F1adir jugador");
		btnAadirJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.guardarDatos(txtNombre.getText(),txtApellido.getText(),txtPosicion.getText(),txtEquipo.getName());
			}
		});
		btnAadirJugador.setBounds(62, 213, 103, 23);
		panel.add(btnAadirJugador);
		
		
		
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
		equipos = controlador.comboEquipos();
		for (int i = 0; i < equipos.size();i++) {
			txtEquipo.addItem(equipos.get(i));
		}
		// TODO Auto-generated method stub
		
	}
}
