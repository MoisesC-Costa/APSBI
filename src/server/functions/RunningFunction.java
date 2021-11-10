package server.functions;

public class RunningFunction implements Function{
	private static boolean keepRunning = true;
	
	@Override
	public void exec() {
		keepRunning = false;
	}

	public static boolean getState() {
		return keepRunning;
	}
	
}
