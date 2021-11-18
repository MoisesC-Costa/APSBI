package cliente.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONException;
import org.json.JSONObject;

import com.opencsv.exceptions.CsvValidationException;

import cliente.app.listeners.SwitchComponentListener;
import cliente.app.tools.SwitchComponents;
import cliente.boundary.Boundary;
import cliente.tools.DataConveter;

public class MainApp {

	private Boundary boundary;
	private String token;
	
	private JFrame frame;
	
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
		
		JButton etl = new JButton();
		etl.setOpaque(false);
		etl.setFocusPainted(false);
		etl.setBounds(52, 1, 32, 32);
		etl.setIcon(new ImageIcon("img\\etl.png"));
		optionsPanel.add(etl);

		
		JButton home = new JButton();
		home.setOpaque(false);
		home.setFocusPainted(false);
		home.setBounds(10, 1, 32, 32);
		home.setIcon(new ImageIcon("img\\home.png"));
		optionsPanel.add(home);
		
		JButton graficos = new JButton();
		graficos.setOpaque(false);
		graficos.setFocusPainted(false);
		graficos.setBounds(94, 1, 32, 32);
		graficos.setIcon(new ImageIcon("img\\graficos.png"));
		optionsPanel.add(graficos);
		
		JPanel conteudoPanel = new JPanel();
		conteudoPanel.setBounds(0, 34, 484, 277);
		frame.getContentPane().add(conteudoPanel);
		conteudoPanel.setLayout(null);
		
		JPanel etlPanel = new JPanel();
		etlPanel.setBounds(0, 0, 484, 277);
		conteudoPanel.add(etlPanel);
		etlPanel.setLayout(null);
		etlPanel.setVisible(false);
		
		JTabbedPane etlPage = new JTabbedPane(JTabbedPane.TOP);
		etlPage.setBounds(0, 0, 484, 277);
		etlPanel.add(etlPage);
		
		JPanel panel = new JPanel();
		etlPage.addTab("DBQueimadas", null, panel, null);
		panel.setLayout(null);
		
		JButton uploadForm = new JButton();
		uploadForm.setBounds(189, 74, 100, 100);
		uploadForm.setIcon(new ImageIcon("img\\form.png"));
		uploadForm.setFocusPainted(false);
		uploadForm.setBorderPainted(false);
		uploadForm.addActionListener(new UploadFormDBQueimadas());
		panel.add(uploadForm);
		
		JPanel homePanel = new JPanel();
		homePanel.setBounds(0, 0, 484, 277);
		conteudoPanel.add(homePanel);
		homePanel.setLayout(null);
		
		JLabel userLabel = new JLabel("New label");
		userLabel.setBounds(10, 11, 46, 14);
		homePanel.add(userLabel);
		
		JLabel email = new JLabel("New label");
		email.setBounds(10, 36, 46, 14);
		homePanel.add(email);
		
		JPanel graficosPanel = new JPanel();
		graficosPanel.setBounds(0, 0, 484, 277);
		conteudoPanel.add(graficosPanel);
		graficosPanel.setLayout(null);
		graficosPanel.setVisible(false);
		
		JTabbedPane graficosPage = new JTabbedPane(JTabbedPane.TOP);
		graficosPage.setBounds(0, 0, 484, 277);
		graficosPanel.add(graficosPage);
		
		JPanel panel_1 = new JPanel();
		graficosPage.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		graficosPage.addTab("New tab", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		graficosPage.addTab("New tab", null, panel_3, null);
		
		SwitchComponents switcher = new SwitchComponents(homePanel);
		
		home.addActionListener(new SwitchComponentListener(homePanel, switcher));
		graficos.addActionListener(new SwitchComponentListener(graficosPanel, switcher));
		etl.addActionListener(new SwitchComponentListener(etlPanel, switcher));

		
	}

	// Observadores
	
	// Enviar o formulario do DBQueimadas para o servidor
	private class UploadFormDBQueimadas implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			// Criando o pick chooser
			JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.dir")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("DBQueimadas(csv)", "csv");
			chooser.setFileFilter(filter);
			
			// abrindo a interface
			int returnVal = chooser.showOpenDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				
				if (path.matches("(.*).csv")) {
					JSONObject message = new JSONObject();
					
					
					message.put("logic", "UploadDBQueimadasLogic");
					message.put("token", token);
					try {
						message.put("csv", DataConveter.converterCSVToJSONObject(path));
					
					// BOTAR DIALOG
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					JSONObject response = boundary.request(message);
					
					if (response.getBoolean("code")) {
						
					} else {
						//BOTAR DIALOG
						System.out.println(response.getString("description"));
						
					}
					
					
				// BOTAR DIALOG	
				} else {
					System.out.println("Formato do arquivo invalido");
					
				}

			}
			
		}
		
	}
	
}
