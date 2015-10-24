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
	
	public FestivalActionListener(JFrame parentFrame, JTextArea textArea, JButton hearBtn,
			JButton stopBtn, CreateMP3Panel panel) {
		this.parentFrame = parentFrame;
		this.textArea = textArea;
		this.hearBtn = hearBtn;
		this.stopBtn = stopBtn;
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		commentary = textArea.getText();
		
		if (commentary.isEmpty() || commentary.trim().length() == 0) {
			JOptionPane.showMessageDialog(parentFrame, "Text can not be blank", "Text Error", JOptionPane.ERROR_MESSAGE);
		} else {
			// Performs the festival command in the background
			panel.setVoiceTask(new Festival(commentary, hearBtn, stopBtn));
			panel.getVoiceTask().execute();
			}
	}

}
