package vidivox.swingworker;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * This CreateMP3 class is used to create an mp3 file out of the
 * commentary that the user has typed.
 * @author John Ramirez (jram948)
 * 
 */
public class CreateMP3 extends SwingWorker<Void, Void> {
	private String commentary;
	private String mp3Name;
	private JFrame parentFrame;
	
	//Constructor
	public CreateMP3(String commentary, String mp3Name, JFrame parentFrame) {
		this.commentary = commentary;
		this.mp3Name = mp3Name;
		this.parentFrame = parentFrame;
	}
	
	@Override
	protected Void doInBackground() {
		
		//Creating a .wav file from text, converting .wav file to .mp3 file, removing .wav file
		String cmd = "echo " + "\"" + commentary + "\"" + " | text2wave -o " + mp3Name + ".wav";
		String cmd2 = "ffmpeg -i " + mp3Name + ".wav" + " -f mp3 " + mp3Name + ".mp3";
		String cmd3 = "rm " + mp3Name + ".wav";
		
		ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
		ProcessBuilder pb2 = new ProcessBuilder("/bin/bash", "-c", cmd2);
		ProcessBuilder pb3 = new ProcessBuilder("/bin/bash", "-c", cmd3);
		
		// Performs the previous commands in bash
		try {
			Process p = pb.start();
			p.waitFor();
			Process p2 = pb2.start();
			p2.waitFor();
			Process p3 = pb3.start();
			p3.waitFor();
			
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}		
		return null;
	}
	
	//Shows a completion message to the user
	@Override
	protected void done() {
		JOptionPane.showMessageDialog(parentFrame, "Successfully saved " + mp3Name + ".mp3");
	}
}
