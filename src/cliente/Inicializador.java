package cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.json.JSONObject;

import cliente.app.MainApp;
import cliente.app.listeners.SwitchComponentListener;
import cliente.app.tools.SwitchComponents;
import cliente.boundary.Boundary;

public class Inicializador {
	
	// Utilitarios
	private Boundary boundary;
	private SwitchComponents switchComponents;
	
	// Containers
	private JFrame app;
	private JPanel signupPanel;
	private JPanel loginPanel;

	// Components
	private JTextField emailLoginField;
	private JPasswordField passLoginField;
	private JTextField emailSignupField;
	private JTextField nomeSignupField;
	private JPasswordField passSignupField;
	private JPasswordField repassSignupField;
	private JButton singup;
	private JButton back;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Inicializador window = new Inicializador();
					window.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inicializador() {
		initialize();
		boundary = new Boundary();
	}

	// Estrutura da Interface
	private void initialize() {
		app = new JFrame("Angra");
		app.setSize(500, 350);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.getContentPane().setLayout(null);
		app.setResizable(false);
		app.setLocationRelativeTo(null);

		renderLogin();
		switchComponents = new SwitchComponents(loginPanel);
		
		renderSingup();
		
		singup.addActionListener(new SwitchComponentListener(signupPanel, switchComponents));
		back.addActionListener(new SwitchComponentListener(loginPanel, switchComponents));

		
	}

	// Estruturas
	private void renderSingup() {
		
		signupPanel = new JPanel();
		signupPanel.setLayout(null);
		signupPanel.setBounds(0, 0, 484, 311);
		app.getContentPane().add(signupPanel);
		signupPanel.setVisible(false);

		nomeSignupField = new JTextField();
		nomeSignupField.setBounds(142, 83, 200, 20);
		signupPanel.add(nomeSignupField);
		nomeSignupField.setColumns(10);

		emailSignupField = new JTextField();
		emailSignupField.setBounds(142, 114, 200, 20);
		signupPanel.add(emailSignupField);
		emailSignupField.setColumns(10);

		passSignupField = new JPasswordField();
		passSignupField.setBounds(142, 145, 200, 20);
		signupPanel.add(passSignupField);

		repassSignupField = new JPasswordField();
		repassSignupField.setBounds(142, 176, 200, 20);
		signupPanel.add(repassSignupField);

		JButton sendForm = new JButton("Enviar");
		sendForm.setBounds(142, 207, 63, 23);
		sendForm.addActionListener(new ActSignup());
		signupPanel.add(sendForm);

		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(69, 86, 54, 14);
		signupPanel.add(nomeLabel);

		JLabel emailLabel = new JLabel("E-Mail");
		emailLabel.setBounds(69, 117, 46, 14);
		signupPanel.add(emailLabel);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(69, 148, 46, 14);
		signupPanel.add(passwordLabel);

		JLabel repasswordLabel = new JLabel("Repassword");
		repasswordLabel.setBounds(69, 179, 63, 14);
		signupPanel.add(repasswordLabel);

		back = new JButton("Voltar");
		back.setBounds(279, 207, 63, 23);
		signupPanel.add(back);

		JRadioButton showpass2 = new JRadioButton("show");
		showpass2.setBounds(348, 175, 109, 23);
		signupPanel.add(showpass2);
		showpass2.addActionListener(new ShowPassword(passSignupField));
		showpass2.addActionListener(new ShowPassword(repassSignupField));

	}
	
	private void renderLogin() {
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 484, 311);
		app.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		emailLoginField = new JTextField();
		emailLoginField.setBounds(142, 145, 200, 20);
		loginPanel.add(emailLoginField);
		emailLoginField.setColumns(10);

		passLoginField = new JPasswordField();
		passLoginField.setBounds(142, 176, 200, 20);
		loginPanel.add(passLoginField);

		JButton signin = new JButton("Logar");
		signin.setBounds(142, 207, 61, 23);
		signin.addActionListener(new ActLogin());
		loginPanel.add(signin);

		singup = new JButton("Cadastrar");
		singup.setBounds(261, 207, 81, 23);
		loginPanel.add(singup);

		JRadioButton showPassword = new JRadioButton("show");
		showPassword.setBounds(348, 175, 109, 23);
		showPassword.addActionListener(new ShowPassword(passLoginField));
		loginPanel.add(showPassword);

		JLabel emailLoginLabel = new JLabel("E-Mail");
		emailLoginLabel.setBounds(78, 148, 54, 14);
		loginPanel.add(emailLoginLabel);

		JLabel passLoginLabel = new JLabel("Password");
		passLoginLabel.setBounds(78, 179, 46, 14);
		loginPanel.add(passLoginLabel);

	}
	
	// Observadores
	private class ShowPassword implements ActionListener {
		private JPasswordField field;
		private char defaultChar;

		public ShowPassword(JPasswordField field) {
			this.field = field;
			defaultChar = field.getEchoChar();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton button = (JRadioButton) e.getSource();

			if (button.isSelected()) {
				field.setEchoChar((char) 0);

			}

			else {
				field.setEchoChar(defaultChar);

			}

		}

	}

	private class ActLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JSONObject message = new JSONObject();

			try {
				String email = checkReturnEmail(emailLoginField);
				String password = checkReturnPassword(passLoginField);

				message.put("logic", "LoginLogic");
				message.put("email", email);
				message.put("password", password);

				JSONObject response = boundary.request(message);

				if (response.getBoolean("code")) {
					app.dispose();
					new MainApp(boundary, response.getString("token"));

				} else {
					System.out.println("Dialog" + response.getString("description"));

				}


			} catch(IllegalArgumentException e) {

			}

		}

	}

	private class ActSignup implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JSONObject message = new JSONObject();
			message.put("logic", "SignupLogic");

			try {
				String nome = checkReturnNome(nomeSignupField);
				String password = checkCompareReturnPassword(passSignupField, repassSignupField);
				String email = checkReturnEmail(emailSignupField);

				message.put("nome", nome);
				message.put("email", email);
				message.put("password", password);

				JSONObject response = boundary.request(message);

				if (response.getBoolean("code")) {
					String token = response.getString("token");

					app.dispose();
					new MainApp(boundary, token);

				} else {
					System.out.print(response.getString("description"));

				}

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}

	}


	// Tratar os dados para não ter problemas no servidor
	private String checkReturnNome(JTextField nome) {
		String value = nome.getText();
		
		if (value.isEmpty()) {
			throw new IllegalArgumentException("Nome em branco");
			
		} else if (value.matches("^[a-zA-Z ]*$")) {
			return value;
			
		} else {
			throw new IllegalArgumentException("Nome Invalido");
			
		}
				
	}

	private String checkReturnEmail(JTextField email) 
			throws IllegalArgumentException {

		String value = email.getText();
		IllegalArgumentException emailInvalido = new IllegalArgumentException("Email Invalido");
		
		
		if (value.isBlank()) {
			throw new IllegalArgumentException("Email em branco");

		} else if (!value.contains("@")) {
			throw emailInvalido;

		} else if (value.split("@").length != 2) {
			throw emailInvalido;
			
		}

		return value;

	}

	private String checkReturnPassword(JPasswordField password) 
			throws IllegalArgumentException {

		String value = new String(password.getPassword());

		if (value.isBlank()) {
			throw new IllegalArgumentException();

		} else if (value.length() < 4) {
			throw new IllegalArgumentException();

		}

		return value;
	}

	private String checkCompareReturnPassword(JPasswordField password1,
			JPasswordField password2) throws IllegalArgumentException {

		boolean forPerformed = true;

		char[] pass1 = password1.getPassword();
		char[] pass2 = password2.getPassword();

		if (pass1.length < 4) {
			throw new IllegalArgumentException("Senha invalida");

		} else {
			for (int i = 0 ; i < pass1.length ; i++) {
				if (pass1[i] != pass2[i]) {
					forPerformed = false;
					break;
				}

			}

			if (forPerformed) {
				return new String(pass1);

			} else {
				throw new IllegalArgumentException();

			}

		}

	}
	
}
