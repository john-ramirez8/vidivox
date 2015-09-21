package prototype;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileOpener {
	private String desiredExtension;
	private JFileChooser fc = new JFileChooser();
	private FileWriter fw;
	private File openedFile;
	private File savedFile;
	private ErrorWindow ew;
	private int errNo;
	private JFrame parentFrame;
	private String errMessage;

	public FileOpener(String extension, JFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.desiredExtension = extension;
		if (extension.equals(".avi")){
			errMessage = "Please choose a file of .avi type";
		} else if (extension.equals(".mp3")){
			errMessage = "Please choose a file of .mp3 type";
		}
		fc.setDialogTitle("Select a " + extension + "type file.");
	}
	
	public FileOpener() {
		fc.setDialogTitle("Save as mp3");
	}

	public File openFile() {
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			openedFile = fc.getSelectedFile();
			String extension = openedFile.toString().substring(openedFile.toString().length() - 4);
			if (extension.equals(desiredExtension) == false) {
				JOptionPane.showMessageDialog(parentFrame, errMessage);
				//ew = new ErrorWindow(errNo);
				//ew.setVisible(true);
				return null;
			} else {
				return openedFile;
			}
		} else {
			return null;
		}
	}
	
	public void saveFile() {
		int result = fc.showDialog(null, "Save");
		if (result == JFileChooser.APPROVE_OPTION) {
			savedFile = fc.getSelectedFile();
			try {
				fw = new FileWriter(savedFile);
				//To Do with mp3
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}