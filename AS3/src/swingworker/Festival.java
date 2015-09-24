package swingworker;

import java.io.IOException;

import javax.swing.SwingWorker;

public class Festival extends SwingWorker<Void, Void> {

	private String commentary;
	
	// Constructor
	public Festival(String commentary) {
		this.commentary = commentary;
	}

	@Override
	protected Void doInBackground() throws Exception {

		// Festival command for playing user's commentary
		String cmd = "echo " + commentary + " | festival --tts";
		
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
		
		// Performs command in bash
		try {
			Process p = pb.start();
			p.waitFor();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
}
