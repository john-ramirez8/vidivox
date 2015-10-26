package vidivox.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import vidivox.gui.CreateMP3Panel;
import vidivox.swingworker.Festival;
import vidivox.swingworker.StopFestival;

/**
 * This class is used to invoke the festival command in a swingworker thread.
 * It is also used to cancel the festival process that could be currently playing.
 * @author John Ramirez (jram948)
 *
 */
public class FestivalActionListener implements ActionListener {

	private String commentary;
	private JFrame parentFrame;
	private JTextArea textArea;
	private JButton hearBtn;
	private JButton stopBtn;
	private CreateMP3Panel panel;
	private boolean stopFestival;
	
	public FestivalActionListener(JFrame parentFrame, JTextArea textArea, JButton hearBtn,
			JButton stopBtn, CreateMP3Panel panel, boolean stopFestival) {
		this.parentFrame = parentFrame;
		this.textArea = textArea;
		this.hearBtn = hearBtn;
		this.stopBtn = stopBtn;
		this.panel = panel;
		this.stopFestival = stopFestival;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//Creates a festival process or cancels it
		if (!stopFestival) {
			commentary = textArea.getText();
			
			//Ensures text isn't empty
			if (commentary.isEmpty() || commentary.trim().length() == 0) {
				JOptionPane.showMessageDialog(parentFrame, "Text can't be blank", "Text Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				//Replaces newline characters with spaces
				commentary = commentary.replace('\n', ' ');
				commentary = commentary.toLowerCase();
				
				// Performs the festival command in the background
				panel.setVoiceTask(new Festival(commentary, hearBtn, stopBtn));
				panel.getVoiceTask().execute();
				}
		} else {
			//Cancels current festival process
			panel.setStopVoiceTask(new StopFestival(hearBtn, stopBtn));
			panel.getStopVoiceTask().execute();
		}

	}

}
