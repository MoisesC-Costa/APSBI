package cliente.frames;

import javax.swing.JFrame;


public class LoginFrame extends JFrame implements Runnable {
	
	@Override
	public void run() {
		this.setSize(800, 500);
		this.getContentPane().setLayout(null);
		this.setTitle("Inicializador de Sistema");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
	
}
