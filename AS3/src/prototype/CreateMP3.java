package prototype;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class CreateMP3 extends SwingWorker<Void, Void> {
	private String commentary;
	private String mp3Name;
	private JPanel parentPanel;
	
	public CreateMP3(String commentary, String mp3Name, JPanel parentPanel) {
		this.commentary = commentary;
		this.mp3Name = mp3Name;
		this.parentPanel = parentPanel;
	}
	
	protected Void doInBackground() {
		
		String cmd = "echo " + "\"" + commentary + "\"" + " | text2wave -o " + mp3Name + ".wav";
		String cmd2 = "ffmpeg -i " + mp3Name + ".wav" + " -f mp3 " + mp3Name + ".mp3";
		String cmd3 = "rm " + mp3Name + ".wav";
		
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
		ProcessBuilder pb2 = new ProcessBuilder("/bin/bash", "-c", cmd2);
		ProcessBuilder pb3 = new ProcessBuilder("/bin/bash", "-c", cmd3);
		
		try {
			Process p = pb.start();
			p.waitFor();
			Process p2 = pb2.start();
			p2.waitFor();
			Process p3 = pb3.start();
			p3.waitFor();
			
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		return null;
	}
	
	protected void done() {
		JOptionPane.showMessageDialog(parentPanel, "Successfully saved " + mp3Name + ".mp3");
	}
}
