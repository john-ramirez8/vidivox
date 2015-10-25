package swingworker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.SwingWorker;

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

		//Disables the Hear button
		hearBtn.setEnabled(false);
		stopBtn.setEnabled(true);

		// Festival command for playing user's commentary
		String cmd = "echo \"" + commentary + "\" | festival --tts";
		System.out.println("the command within Festival class = " + cmd);
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
		//Enables the Hear button again
		hearBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		
		if (isCancelled()) {
			if(_process.getClass().getName().equals("java.lang.UNIXProcess")){
				
				try {
					Field f = _process.getClass().getDeclaredField("pid");
					f.setAccessible(true);
					// pid is private in UNIXProcess
					int pid = f.getInt(_process);
					
					ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "pstree -lp | grep " + pid);
					Process p = pb.start();
			
					InputStream stdout = p.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
					String line = br.readLine();
				
					if (line.contains("play")) {
						String processPID = line.substring(line.indexOf("play("));
						processPID = processPID.substring(5, processPID.indexOf(")"));
						String cmd = "kill -9 " + processPID;
						
						System.out.println("command to kill festival = " + cmd);
						ProcessBuilder processKiller = new ProcessBuilder("/bin/bash", "-c", cmd);
						processKiller.start();
					}
							
					} catch (NoSuchFieldException | SecurityException | IOException
	                        | IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
			}
		}
	}
}
