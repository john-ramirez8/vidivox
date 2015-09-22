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
	private JFrame parentFrame;
	private String errMessage;

	public FileOpener(String extension, JFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.desiredExtension = extension;
		if (extension.equals(".avi")){
			errMessage = "Please specify a file of .avi type";
		} else if (extension.equals(".mp3")){
			errMessage = "Please specify a file of .mp3 type";
		}
	}

	public File openFile() {
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			openedFile = fc.getSelectedFile();
			String extension = openedFile.toString().substring(openedFile.toString().length() - 4);
			if (extension.equals(desiredExtension) == false) {
				JOptionPane.showMessageDialog(parentFrame, errMessage);
				return null;
			} else {
				return openedFile;
			}
		} else {
			return null;
		}
	}
	
	public String saveFile() {
		int result = fc.showDialog(null, "Save");
		if (result == JFileChooser.APPROVE_OPTION) {
			savedFile = fc.getSelectedFile();
			String extension = savedFile.toString().substring(savedFile.toString().length() - 4);
			if (extension.equals(desiredExtension) == false) {
				JOptionPane.showMessageDialog(parentFrame, errMessage);
				return null;
			} else {
				return savedFile.getAbsolutePath();
			}
		} else {
			return null;
		}
	}
}