package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Jugador;
import modelo.ModeloSQL;

import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JButton btnEliminar;
	private JButton btnEliminarTodo;
	private Controlador controlador;
	private ModeloSQL modeloSQL;
	private String iddel;
	private String resp;

	private JScrollPane scrollPane;

	private Jugador jugador;
	private static   JTable table;

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public ModeloSQL getModelo() {
		return modeloSQL;
	}

	public void setModelo(ModeloSQL modeloSQL) {
		this.modeloSQL = modeloSQL;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	DefaultTableModel modelotab = new DefaultTableModel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Principal(Controlador controlador) {

		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] columnas = { "id", "Nombre", "Apellidos", "Posicion", "Equipo" };
		modelotab.setColumnIdentifiers(columnas);

		JButton btnAdd = new JButton("A\u00F1adir");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.anadirJugador();

			}
		});
		btnAdd.setBounds(0, 217, 89, 23);
		contentPane.add(btnAdd);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iddel=(String) table.getValueAt(table.getSelectedRow(),0);
				controlador.delJug(iddel);
			}
		});
		btnEliminar.setBounds(99, 217, 89, 23);
		contentPane.add(btnEliminar);

		btnEliminarTodo = new JButton("Eliminar todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.delTodo();
			}
		});
		btnEliminarTodo.setBounds(198, 217, 118, 23);
		contentPane.add(btnEliminarTodo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setBounds(0, 0, 434, 184);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolcar = new JButton("Volcar");
		btnVolcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.volcar();
			}
		});
		btnVolcar.setBounds(335, 217, 89, 23);
		contentPane.add(btnVolcar);

	}

	public void cargarDatos() {
		controlador.cargarDatos(resp);
		
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public void pintarTabla(Jugador jugador) {
		
		modelotab.addRow(new Object[] { jugador.getID(), jugador.getNombre(), jugador.getApellido(),
				jugador.getPosicion(), jugador.getEquipo() });
		
		
		
		table.setModel(modelotab);
		
		

	}
	public void refreshTabla() {
		int rowCount = modelotab.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    modelotab.removeRow(i);
		}
		controlador.cargarDatos(resp);
	}
	public void seleccion() {
		String[] opciones = {
	            "SQL",
	            "Fichero"
	        };
	         resp = (String) JOptionPane.showInputDialog(null, "Seleccione la opción requerida", "Opciones", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
	        if(resp.equals("SQL")) {
	        	System.out.println("lol");
	        }
		
	}
}
