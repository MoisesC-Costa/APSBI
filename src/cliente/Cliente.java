package cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.json.JSONObject;

import cliente.boundary.Boundary;
import javax.swing.JLabel;

public class Cliente {
	
	private Boundary boundary;
	private JFrame app;
	private JTextField userLoginField;
	private JPasswordField passLoginField;
	private JTextField emailSignupField;
	private JTextField userSignupField;
	private JPasswordField passSignField;
	private JPasswordField repassSignField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Cliente window = new Cliente();
					window.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cliente() {
		initialize();
		boundary = new Boundary();
	}
	
	
	// Estrutura
	
	private void initialize() {
		app = new JFrame("Angra");
		app.setSize(500, 350);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.getContentPane().setLayout(null);
		
		JPanel signupPanel = new JPanel();
		signupPanel.setLayout(null);
		signupPanel.setBounds(0, 0, 484, 311);
		app.getContentPane().add(signupPanel);
		
		userSignupField = new JTextField();
		userSignupField.setBounds(142, 114, 200, 20);
		signupPanel.add(userSignupField);
		userSignupField.setColumns(10);
		
		emailSignupField = new JTextField();
		emailSignupField.setBounds(142, 145, 200, 20);
		signupPanel.add(emailSignupField);
		emailSignupField.setColumns(10);
		
		passSignField = new JPasswordField();
		passSignField.setBounds(142, 176, 200, 20);
		signupPanel.add(passSignField);
		
		repassSignField = new JPasswordField();
		repassSignField.setBounds(142, 207, 200, 20);
		signupPanel.add(repassSignField);
		
		JButton EnviarForm = new JButton("Enviar");
		EnviarForm.setBounds(142, 238, 63, 23);
		signupPanel.add(EnviarForm);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 484, 311);
		app.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		
		userLoginField = new JTextField();
		userLoginField.setBounds(142, 160, 200, 20);
		loginPanel.add(userLoginField);
		userLoginField.setColumns(10);
		
		passLoginField = new JPasswordField();
		passLoginField.setBounds(142, 191, 200, 20);
		loginPanel.add(passLoginField);
		
		JButton signin = new JButton("Logar");
		signin.setBounds(142, 222, 61, 23);
		signin.addActionListener(new ActLogin());
		loginPanel.add(signin);
		
		JButton singup = new JButton("Cadastrar");
		singup.setBounds(261, 222, 81, 23);
		loginPanel.add(singup);
		
		JRadioButton showPassword = new JRadioButton("show");
		showPassword.setBounds(348, 190, 109, 23);
		showPassword.addActionListener(new ShowPassword());
		loginPanel.add(showPassword);
		
		JLabel userLoginLabel = new JLabel("Username");
		userLoginLabel.setBounds(78, 163, 54, 14);
		loginPanel.add(userLoginLabel);
		
		JLabel passLoginLabel = new JLabel("Password");
		passLoginLabel.setBounds(78, 194, 46, 14);
		loginPanel.add(passLoginLabel);
		
	}
	
	// Observadores
	
	private class ShowPassword implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton button = (JRadioButton) e.getSource();
			
			if (button.isSelected()) {
				passLoginField.setEchoChar((char) 0);
				
			}
			
			else {
				passLoginField.setEchoChar('*');
				
			}
			
		}
		
	}
	
	private class ActLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JSONObject message = new JSONObject();
			message.put("logic", "LoginLogic");
			message.put("username", userLoginField.getText());
			message.put("password", new String(passLoginField.getPassword()));
			
			JSONObject response = boundary.request(message);
			
			if (response.getBoolean("code")) {
				System.out.println(response.getString("token"));

			} else {
				System.out.println(response.getString("description"));
				
			}
			
		}
		
	}
}
