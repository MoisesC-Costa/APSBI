package cliente.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cliente.boundary.Boundary;

public class MainApp {

	private Boundary boundary;
	private String token;
	
	private JFrame frame;
	private JButton etl;
	private JButton graficos;
	private JButton home;
	private JPanel contentPanel;
	
	public MainApp(Boundary boundary, String token) {
		this.boundary = boundary;
		this.token = token;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame("Angra");
		frame.setSize(500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(0, 0, 484, 34);
		optionsPanel.setBackground(Color.LIGHT_GRAY);
		optionsPanel.setPreferredSize(new Dimension(0, 34));
		optionsPanel.setLayout(null);
		frame.getContentPane().add(optionsPanel);
		
		etl = new JButton();
		etl.setOpaque(false);
		etl.setFocusPainted(false);
		etl.setBorderPainted(false);
		etl.setBounds(10, 1, 32, 32);
		ImageIcon etlIcon = new ImageIcon("img/etl.png");
		etl.setIcon(etlIcon);
		optionsPanel.add(etl);
		
		graficos = new JButton();
		graficos.setOpaque(false);
		graficos.setFocusPainted(false);
		graficos.setBorderPainted(false);
		graficos.setBounds(54, 1, 32, 32);
		ImageIcon graficosIcon = new ImageIcon("img/graficos.png");
		graficos.setIcon(graficosIcon);
		optionsPanel.add(graficos);
		
		home = new JButton();
		home.setOpaque(false);
		home.setFocusPainted(false);
		home.setBorderPainted(false);
		home.setBounds(402, 1, 32, 32);
		ImageIcon homeIcon = new ImageIcon("img/home.png");
		home.setIcon(homeIcon);
		optionsPanel.add(home);
		
		contentPanel = new JPanel();
		contentPanel.setBounds(0, 34, 484, 277);
		frame.getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
	}

	// Observadores
		
}
