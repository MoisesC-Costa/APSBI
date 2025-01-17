package cliente.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.json.JSONArray;
import org.json.JSONObject;

import cliente.app.interfaces.LoaderGrafico;
import cliente.app.listeners.SwitchComponentListener;
import cliente.app.tools.SwitchComponents;
import cliente.boundary.Boundary;
import cliente.tools.DataConveter;

public class MainApp {

	private Boundary boundary;
	private String token;
	
	private JTabbedPane graficosPage;
	private JFrame frame;
	private JPanel homePanel;
	private JPanel etlPanel;
	private JPanel graficosPanel;
	private JButton home;
	private JButton graficos;
	private JButton etl;
	private JPanel pizzaPanel;
	private JPanel histogramaPanel;
	private JLabel userLabel;
	private JLabel emailLabel;
	
	private JPanel conteudoPanel;
	private JPanel barraPanel;
	
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
		
		renderOptionPanel();
		
		renderConteudoPanel();
		
		setSwitchComponents();
		
		// Para Ficar Atualizando os Graficos;
		Runnable updateGrafics = new Runnable() {
			private LoaderGrafico[] loaders = {new PizzaLoader(),
										new BarraLoader(),
										new HistogramaLoader()};
		
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				
				try {
					for (LoaderGrafico loader : loaders) {
						loader.loader();
						}
					} catch(Exception e) {}
				
				try {
					Thread.currentThread().sleep(60 * 1000);
				} catch (InterruptedException e) {}

				
				run();
				
			}

		};

		new Thread(updateGrafics).start();
		
	}

	
	// Estruturas
	private void renderOptionPanel() {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(0, 0, 484, 34);
		optionsPanel.setBackground(Color.LIGHT_GRAY);
		optionsPanel.setPreferredSize(new Dimension(0, 34));
		optionsPanel.setLayout(null);
		frame.getContentPane().add(optionsPanel);
		
		etl = new JButton();
		etl.setOpaque(false);
		etl.setFocusPainted(false);
		etl.setBounds(52, 1, 32, 32);
		etl.setIcon(new ImageIcon("img\\etl.png"));
		optionsPanel.add(etl);

		
		home = new JButton();
		home.setOpaque(false);
		home.setFocusPainted(false);
		home.setBounds(10, 1, 32, 32);
		home.setIcon(new ImageIcon("img\\home.png"));
		optionsPanel.add(home);
		
		graficos = new JButton();
		graficos.setOpaque(false);
		graficos.setFocusPainted(false);
		graficos.setBounds(94, 1, 32, 32);
		graficos.setIcon(new ImageIcon("img\\graficos.png"));
		optionsPanel.add(graficos);

	}

	private void renderConteudoPanel() {
		conteudoPanel = new JPanel();
		conteudoPanel.setBounds(0, 34, 484, 277);
		frame.getContentPane().add(conteudoPanel);
		conteudoPanel.setLayout(null);

		renderHomePage();
		
		renderGraficosPage();

		renderETLPage();		

	}
	
	public void renderGraficosPage() {
		graficosPanel = new JPanel();
		graficosPanel.setBounds(0, 0, 484, 277);
		conteudoPanel.add(graficosPanel);
		graficosPanel.setLayout(null);
		graficosPanel.setVisible(false);
		
		graficosPage = new JTabbedPane(JTabbedPane.TOP);
		graficosPage.setBounds(0, 0, 484, 277);
		graficosPanel.add(graficosPage);
		
		pizzaPanel = new JPanel();
		graficosPage.addTab("Grafico Pizza", null, pizzaPanel, null);
		pizzaPanel.setLayout(new BorderLayout(0, 0));
		
		barraPanel = new JPanel();
		graficosPage.addTab("Grafico Barras", null, barraPanel, null);
		barraPanel.setLayout(new BorderLayout(0, 0));
		
		histogramaPanel = new JPanel();
		graficosPage.addTab("Histograma", null, histogramaPanel, null);
		histogramaPanel.setLayout(new BorderLayout(0, 0));
		
		
	}

	private void renderHomePage() {
		homePanel = new JPanel();
		homePanel.setBounds(0, 0, 484, 277);
		conteudoPanel.add(homePanel);
		homePanel.setLayout(null);
		
		userLabel = new JLabel("New label");
		userLabel.setBounds(10, 11, 464, 14);
		homePanel.add(userLabel);
		
		emailLabel = new JLabel("New label");
		emailLabel.setBounds(10, 36, 464, 14);
		homePanel.add(emailLabel);

		new InfoUser().loader();
	}
	
	private void renderETLPage() {

		etlPanel = new JPanel();
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

	}
	
	private void setSwitchComponents() {
		SwitchComponents switcher = new SwitchComponents(homePanel);		
		home.addActionListener(new SwitchComponentListener(homePanel, switcher));
		graficos.addActionListener(new SwitchComponentListener(graficosPanel, switcher));
		etl.addActionListener(new SwitchComponentListener(etlPanel, switcher));
		
	}
	
	// Observadores
	
		/// Enviar o formulario do DBQueimadas para o servidor
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
	
	// Carregar os Graficos
	public class PizzaLoader implements LoaderGrafico {

		@SuppressWarnings("static-access")
		@Override
		public void loader() {
			Calendar ano = Calendar.getInstance();
			ano.set(2021, 1, 1);
			
			JSONObject message = new JSONObject();
			message.put("logic", "GetPizzaData");
			message.put("token", token);
			message.put("date", ano.getTimeInMillis());
			
			JSONObject resp = boundary.request(message);
						
			if (resp.getBoolean("code")) {
				JSONObject data = resp.getJSONObject("data");
				
				DefaultPieDataset<String> pizza = new DefaultPieDataset<String>();
				
				for (String columns : data.getNames(data)) {
					pizza.setValue(columns, data.getInt(columns));
				}
				
				JFreeChart grafico = ChartFactory.createPieChart("Focus de Incendios nos Biomas",
																	pizza, true, true, false);
				ChartPanel panel = new ChartPanel(grafico);
				
				pizzaPanel.add(panel, BorderLayout.CENTER);
				
				
			} else {
				JOptionPane.showMessageDialog(null, resp.getString("description"));

			}
			
		}
	
	}
	
	public class BarraLoader implements LoaderGrafico {
	
		@SuppressWarnings("static-access")
		@Override
		public void loader() {

			Calendar ano = Calendar.getInstance();
			ano.set(2021, 1, 1);
			
			JSONObject message = new JSONObject();
			message.put("logic", "GetBarraData");
			message.put("token", token);
			message.put("date", ano.getTimeInMillis());
			
			JSONObject resp = boundary.request(message);
						
			if (resp.getBoolean("code")) {
				JSONObject data = resp.getJSONObject("data");
				
				DefaultCategoryDataset barra = new DefaultCategoryDataset();
				
				for (String columns : data.getNames(data)) {
					barra.setValue(data.getInt(columns), columns, "");
				}
				
				JFreeChart grafico = ChartFactory.createBarChart("Focos de Incendio", null, "Casos", barra,
						PlotOrientation.VERTICAL, true, true, false);
				
				ChartPanel painel = new ChartPanel(grafico);
				
				barraPanel.add(painel, BorderLayout.CENTER);
			} else {
				JOptionPane.showMessageDialog(null, resp.getString("description"));
				
			}

		}

	}
	
	public class HistogramaLoader implements LoaderGrafico {

		@Override
		public void loader() {

			Calendar ano = Calendar.getInstance();
			ano.set(2021, 1, 1);
			
			JSONObject message = new JSONObject();
			message.put("logic", "GetHistogramaData");
			message.put("token", token);
			message.put("date", ano.getTimeInMillis());
			
			JSONObject resp = boundary.request(message);
						
			if (resp.getBoolean("code")) {
				JSONArray data = resp.getJSONArray("data");
				
				XYSeries casos = new XYSeries("Casos");
				
				for (int i = 0 ; i < data.length() ; i++) {
					casos.add(i, data.getInt(i));
				}
				
				XYSeriesCollection dataset = new XYSeriesCollection();
				dataset.addSeries(casos);
				
				JFreeChart chart = ChartFactory.createXYLineChart(
						"Evolu��o Diaria de Casos",
						"Dias", "Casos", dataset,
						PlotOrientation.VERTICAL,
						true, true, false);
				
				ChartPanel panel = new ChartPanel(chart);
				
				histogramaPanel.add(panel, BorderLayout.CENTER);
				
			} else {
				JOptionPane.showMessageDialog(null, resp.getString("description"));
			}

		}

	}

	// Carregar Informa��es do Usuario
	
	public class InfoUser {
		
		public void loader() {
			JSONObject message = new JSONObject();
			message.put("logic", "GetInfoUsuario");
			message.put("token", token);

			JSONObject response = boundary.request(message);
			
			if (response.getBoolean("code")) {
				userLabel.setText("Nome: " + response.getString("nome"));
				emailLabel.setText("Email: " + response.getString("email"));

				
			} else {
				JOptionPane.showMessageDialog(null, response.getString("description"));

			}
			
		}

	}
	
}
