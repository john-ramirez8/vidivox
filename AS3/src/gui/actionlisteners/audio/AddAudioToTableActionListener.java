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

/**
 * This class is used to add data to the audio table, so users can see
 * what audio files they have chosen to add.
 * @author John Ramirez (jram948)
 *
 */
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
		videoLength = parentFrame.getVideo().getLength();
		
		//Checks that the text entered is only numbers, and is of length 2 (2 digits)
		if (!minutesText.matches("[0-9]+") || !secondsText.matches("[0-9]+")
				|| (minutesText.length() != 2) || (secondsText.length() != 2)) {
			JOptionPane.showMessageDialog(parentFrame, "Time must be in mm:ss format, digits only",
					"Input Error", JOptionPane.ERROR_MESSAGE);
		//Checks that entered time isn't past the length of the video
		} else if ((Integer.parseInt(minutes.getText())*60 +
				Integer.parseInt(seconds.getText())) > videoLength) {
			JOptionPane.showMessageDialog(parentFrame, "Time must be less than video length",
					"Input Error", JOptionPane.ERROR_MESSAGE);
		} else {
			fileToAdd = fo.openFile();
			
			if (fileToAdd != null) {
				String time = "00:" + minutesText + ":" + secondsText;
				fileName = fileToAdd.getName();
				timeToAdd = minutesText + ":" + secondsText;			
				Object[] rowToAdd = { fileName, timeToAdd };
				
				//Adds file path to arraylist and associated insert time to hashmap
				parentPanel.getArrayList().add(fileToAdd.getAbsolutePath());
				parentPanel.getHashMap().put(fileToAdd.getAbsolutePath(), time);
				
				//Adds data to the table
				table.addRow(rowToAdd);
			}
		}
	}

}
