package swingworker;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

public class Festival extends SwingWorker<Void, Void> {

	private String commentary;
	private JButton hearBtn;
	private JButton stopBtn;
	
	//Constructor
	public Festival(String commentary, JButton hearBtn, JButton stopBtn) {
		this.commentary = commentary;
		this.hearBtn = hearBtn;
		this.stopBtn = stopBtn;
	}

	@Override
	protected Void doInBackground() throws Exception {

		//Disables the Hear button
		hearBtn.setEnabled(false);
		stopBtn.setEnabled(true);

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
		hearBtn.setEnabled(true);
		stopBtn.setEnabled(false);
	}
}
