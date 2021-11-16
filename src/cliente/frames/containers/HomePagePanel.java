package cliente.frames.containers;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class HomePagePanel extends JPanel {

	public HomePagePanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 46, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(20, 36, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(30, 61, 46, 14);
		add(lblNewLabel_2);

	}
}
