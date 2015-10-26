package vidivox.swingworker;

import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 * This class is used to cancel the festival process currently playing.
 * @author John Ramirez
 *
 */
public class StopFestival extends SwingWorker<Void, Void> {

	private JButton hearBtn;
	private JButton stopBtn;
	
	public StopFestival(JButton hearBtn, JButton stopBtn) {
		this.hearBtn = hearBtn;
		this.stopBtn = stopBtn;
	}
	@Override
	protected Void doInBackground() throws Exception {
		
		String cmd = "pkill -9 aplay";
		
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
		
		//Performing the bash command to kill the process
		try {
			Process p = pb.start();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void done() {
		//Enables Preview button and disables Stop button
		hearBtn.setEnabled(true);
		stopBtn.setEnabled(false);
	}

}
