package cliente.frames.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import cliente.frames.tools.SwitchComponents;

public class SwitchComponentListener implements ActionListener {
	private JComponent component;
	private SwitchComponents switchComponets;

	public SwitchComponentListener(JComponent component, SwitchComponents switchComponets) {
		this.component = component;
		this.switchComponets = switchComponets;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switchComponets.updateViwer(component);
	}


}
