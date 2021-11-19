package cliente.app.tools;

import cliente.app.interfaces.LoaderGrafico;

public class RerenderGraficos implements Runnable {
	private LoaderGrafico loader;
	
	public RerenderGraficos(LoaderGrafico loader) {
		this.loader = loader;
		
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		loader.loader();
		
		try {
			Thread.currentThread().sleep(30 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		run();
	}
	
}
