package gui.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import gui.CreateMP3Panel;
import swingworker.Festival;

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
		if (!stopFestival) {
			commentary = textArea.getText();
			System.out.println("commentary = " + commentary);
			if (commentary.isEmpty() || commentary.trim().length() == 0) {
				JOptionPane.showMessageDialog(parentFrame, "Text can't be blank", "Text Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				commentary = commentary.replace('\n', ' ');
				System.out.println("commentary after replacing newline char: " + commentary);
				// Performs the festival command in the background
				panel.setVoiceTask(new Festival(commentary, hearBtn, stopBtn));
				panel.getVoiceTask().execute();
				}
		} else {
			panel.getVoiceTask().cancel(true);
		}

	}

}
