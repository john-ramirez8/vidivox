package vidivox.swingworker;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 * This Festival class is used to use Festival to voice
 * the commentary that the user has typed in.
 * @author John Ramirez (jram948)
 * 
 */
public class Festival extends SwingWorker<Void, Void> {

	private String commentary;
	private JButton hearBtn;
	private JButton stopBtn;
	private Process _process;
	
	//Constructor
	public Festival(String commentary, JButton hearBtn, JButton stopBtn) {
		this.commentary = commentary;
		this.hearBtn = hearBtn;
		this.stopBtn = stopBtn;
	}

	@Override
	protected Void doInBackground() throws Exception {

		//Disables the Preview button and enables Stop button
		hearBtn.setEnabled(false);
		stopBtn.setEnabled(true);

		// Festival command for playing user's commentary
		String cmd = "echo \"" + commentary + "\" | festival --tts";
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
		
		// Performs command in bash
		try {
			_process = pb.start();
			_process.waitFor();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void done() {
		//Enables the Preview button and disables Stop button
		hearBtn.setEnabled(true);
		stopBtn.setEnabled(false);
	}
	
}
