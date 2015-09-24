package swingworker;

import java.io.IOException;

import javax.swing.SwingWorker;

public class Festival extends SwingWorker<Void, Void> {

	private String commentary;
	
	public Festival(String commentary) {
		this.commentary = commentary;
	}

	@Override
	protected Void doInBackground() throws Exception {

		String cmd = "echo " + commentary + " | festival --tts";
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process p = pb.start();
			p.waitFor();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
}
