package cliente.frames.tools;

import javax.swing.JComponent;
import javax.swing.JPanel;

// Classe para ficar mudando os componentes que estão sobrespostos;
public class SwitchComponents {
	JComponent activeComponent;
	
	public SwitchComponents(JComponent main) {
		activeComponent = main;
	}
	
	public void updateViwer(JComponent component) {
		activeComponent.setVisible(false);
		activeComponent = component;
		activeComponent.setVisible(true);
	}
}
