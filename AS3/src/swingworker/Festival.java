package swingworker;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

public class Festival extends SwingWorker<Void, Void> {

	private String commentary;
	private JButton btnHear;
	
	// Constructor
	public Festival(String commentary, JButton btnHear) {
		this.commentary = commentary;
		this.btnHear = btnHear;
	}

	@Override
	protected Void doInBackground() throws Exception {

		//Disables the Hear button
		btnHear.setEnabled(false);

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
	
	@Override
	protected void done() {
		//Enables the Hear button again
		btnHear.setEnabled(true);
	}
}
