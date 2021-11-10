package cliente.frames;

import javax.swing.JFrame;


public class LoginFrame extends JFrame implements Runnable {

	public LoginFrame() {
		this.setTitle("Inicializador de Sistema");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);		
		this.getContentPane().setLayout(null);
		this.setResizable(false);

	}
	
	@Override
	public void run() {
		
		
		this.setVisible(true);
		
	}
	
}
