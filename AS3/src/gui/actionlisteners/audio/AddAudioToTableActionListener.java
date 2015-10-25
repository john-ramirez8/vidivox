package gui.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gui.MergePanel;
import gui.VideoWindow;
import helpers.FileOpener;

public class AddAudioToTableActionListener implements ActionListener {

	private FileOpener fo;
	private VideoWindow parentFrame;
	private JTextField minutes;
	private JTextField seconds;
	private DefaultTableModel table;
	private MergePanel parentPanel;
	
	private long videoLength;
	private String timeToAdd;
	private File fileToAdd;
	private String fileName;

	
	public AddAudioToTableActionListener(VideoWindow parentFrame, JTextField minutes,
			JTextField seconds, DefaultTableModel table, MergePanel parentPanel) {
		this.parentFrame = parentFrame;
		this.minutes = minutes;
		this.seconds = seconds;
		this.table = table;
		this.parentPanel = parentPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fo = new FileOpener(".mp3", parentFrame);
		String minutesText = minutes.getText();
		String secondsText = seconds.getText();
		videoLength = parentFrame.getVideoLength();
		
		if (!minutesText.matches("[0-9]+") || !secondsText.matches("[0-9]+")
				|| (minutesText.length() != 2) || (secondsText.length() != 2)) {
			JOptionPane.showMessageDialog(parentFrame, "Time must be in mm:ss format, digits only",
					"Input Error", JOptionPane.ERROR_MESSAGE);
		} else if ((Integer.parseInt(minutes.getText())*60 +
				Integer.parseInt(seconds.getText())) > videoLength) {
			JOptionPane.showMessageDialog(parentFrame, "Time must be less than video length",
					"Input Error", JOptionPane.ERROR_MESSAGE);
		} else {
			fileToAdd = fo.openFile();
			
			if (fileToAdd != null) {
				long time = Integer.parseInt(minutesText)*60 + Integer.parseInt(secondsText);
				fileName = fileToAdd.getName();
				timeToAdd = minutesText + ":" + secondsText;			
				Object[] rowToAdd = { fileName, timeToAdd };
				
				System.out.println("Time to add the audio is: " + time);
				parentPanel.getHashMap().put(fileToAdd.getAbsolutePath(), time);
				table.addRow(rowToAdd);
			}
		}
	}

}
