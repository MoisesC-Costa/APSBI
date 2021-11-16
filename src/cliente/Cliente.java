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

import cliente.boundary.Boundary;
import cliente.frames.MainApp;
import cliente.frames.listeners.SwitchComponentListener;
import cliente.frames.tools.SwitchComponents;

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
		app.setResizable(false);
		app.setLocationRelativeTo(null);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 484, 311);
		app.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		userLoginField = new JTextField();
		userLoginField.setBounds(142, 145, 200, 20);
		loginPanel.add(userLoginField);
		userLoginField.setColumns(10);

		passLoginField = new JPasswordField();
		passLoginField.setBounds(142, 176, 200, 20);
		loginPanel.add(passLoginField);

		JButton signin = new JButton("Logar");
		signin.setBounds(142, 207, 61, 23);
		signin.addActionListener(new ActLogin());
		loginPanel.add(signin);

		JButton singup = new JButton("Cadastrar");
		singup.setBounds(261, 207, 81, 23);
		loginPanel.add(singup);

		JRadioButton showPassword = new JRadioButton("show");
		showPassword.setBounds(348, 175, 109, 23);
		showPassword.addActionListener(new ShowPassword(passLoginField));
		loginPanel.add(showPassword);

		JLabel userLoginLabel = new JLabel("Username");
		userLoginLabel.setBounds(78, 148, 54, 14);
		loginPanel.add(userLoginLabel);

		JLabel passLoginLabel = new JLabel("Password");
		passLoginLabel.setBounds(78, 179, 46, 14);
		loginPanel.add(passLoginLabel);

		SwitchComponents switchComponents = new SwitchComponents(loginPanel);

		JPanel signupPanel = new JPanel();
		signupPanel.setLayout(null);
		signupPanel.setBounds(0, 0, 484, 311);
		app.getContentPane().add(signupPanel);
		signupPanel.setVisible(false);

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

		JButton sendForm = new JButton("Enviar");
		sendForm.setBounds(142, 238, 63, 23);
		sendForm.addActionListener(new ActSignup());
		signupPanel.add(sendForm);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(69, 117, 54, 14);
		signupPanel.add(usernameLabel);

		JLabel emailLabel = new JLabel("E-Mail");
		emailLabel.setBounds(69, 148, 46, 14);
		signupPanel.add(emailLabel);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(69, 179, 46, 14);
		signupPanel.add(passwordLabel);

		JLabel repasswordLabel = new JLabel("Repassword");
		repasswordLabel.setBounds(69, 210, 63, 14);
		signupPanel.add(repasswordLabel);

		JButton back = new JButton("Voltar");
		back.setBounds(279, 238, 63, 23);
		signupPanel.add(back);

		JRadioButton showpass2 = new JRadioButton("show");
		showpass2.setBounds(348, 175, 109, 23);
		signupPanel.add(showpass2);
		showpass2.addActionListener(new ShowPassword(passSignField));
		showpass2.addActionListener(new ShowPassword(repassSignField));
		singup.addActionListener(new SwitchComponentListener(signupPanel, switchComponents));
		back.addActionListener(new SwitchComponentListener(loginPanel, switchComponents));


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
				String username = checkReturnUsername(userLoginField);
				String password = checkReturnPassword(passLoginField);

				message.put("logic", "LoginLogic");
				message.put("username", username);
				message.put("password", password);

				JSONObject response = boundary.request(message);

				app.dispose();
				new MainApp(boundary, response.getString("token"));

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
				String username = checkReturnUsername(userSignupField);
				String password = checkCompareReturnPassword(passSignField, repassSignField);
				String email = checkReturnEmail(emailSignupField);

				message.put("username", username);
				message.put("password", password);
				message.put("email", email);

				JSONObject response = boundary.request(message);

				if (response.getBoolean("code")) {
					String token = response.getString("token");

					app.dispose();
					new MainApp(boundary, token);

				}

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	// Tratar os dados para não haver problemas no servidor

	private String checkReturnUsername(JTextField username) 
			throws IllegalArgumentException {
		String value = username.getText();

		if (value.isBlank()) {
			throw new IllegalArgumentException();

		} else if (value.length() > 15) {
			throw new IllegalArgumentException();

		}

		return value;
	}

	private String checkReturnEmail(JTextField email) 
			throws IllegalArgumentException {

		String value = email.getText();

		if (value.isBlank()) {
			throw new IllegalArgumentException();

		} else if (!value.contains("@")) {
			throw new IllegalArgumentException();

		}

		String[] list = value.split("@");

		for (String part : list) {
			if (part.isBlank()) {
				throw new IllegalArgumentException();
			}

		}

		return value;

	}

	private String checkReturnPassword(JPasswordField password) 
			throws IllegalArgumentException {

		String value = new String(password.getPassword());

		if (value.isBlank()) {
			throw new IllegalArgumentException();

		} else if (value.length() < 8) {
			throw new IllegalArgumentException();

		}

		return value;
	}

	private String checkCompareReturnPassword(JPasswordField password1,
			JPasswordField password2) throws IllegalArgumentException {

		boolean forPerformed = true;

		char[] pass1 = password1.getPassword();
		char[] pass2 = password2.getPassword();

		if (pass1.length < 8) {
			throw new IllegalArgumentException();

		} else {
			for (int i = 0 ; i < pass1.length ; i++) {
				if (pass1[i] == pass2[i]) {
					// Gambiarra para tentar mais desempenho;
				} else {
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
