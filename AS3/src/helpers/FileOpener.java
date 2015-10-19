package helpers;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

//This class is a helper class, It is used to open and save files
public class FileOpener {
	private String desiredExtension;
	private JFileChooser fc = new JFileChooser();
	private FileNameExtensionFilter aviFilter = new FileNameExtensionFilter("avi Files", "avi");
	private FileNameExtensionFilter mp3Filter = new FileNameExtensionFilter("mp3 Files", "mp3");
	private File openedFile;
	private File savedFile;
	private JFrame parentFrame;
	private String errMessage;

	//The constructor sets the default error message and which frame is the parent frame of the file chooser
	public FileOpener(String extension, JFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.desiredExtension = extension;
		if (extension.equals(".avi")){
			errMessage = "Please specify a file of .avi type";
			fc.setFileFilter(aviFilter);
		} else if (extension.equals(".mp3")) {
			errMessage = "Please specify a file of .mp3 type";
			fc.setFileFilter(mp3Filter);
		}
	}
	
	//This function returns a variable of File type. It opens a file chooser and allows them to choose a file
	//If the file is of incorrect file type then the function calls itself recursively to allow the user to try again
	public File openFile() {
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			openedFile = fc.getSelectedFile();
			String extension = openedFile.toString().substring(openedFile.toString().length() - 4);
			if (extension.equals(desiredExtension) == false) {
				JOptionPane.showMessageDialog(parentFrame, errMessage, "File type Error", JOptionPane.ERROR_MESSAGE);
				return openFile();
			} else {
				return openedFile;
			}
		} else {
			return null;
		}
	}
	
	//This function returns a variable of String type of the path of the file chosen by the user. If the file is of 
	//incorrect file type then the function calls itself recursively to allow the user to try again.
	
	public String saveFile() {
		int result = fc.showDialog(null, "Save");
		if (result == JFileChooser.APPROVE_OPTION) {
			savedFile = fc.getSelectedFile();
			String filePath = savedFile.getAbsolutePath();
			if(!filePath.endsWith(desiredExtension)) {
			    savedFile = new File(filePath + desiredExtension);
			}
			
			if (savedFile.exists()) {
				JOptionPane.showMessageDialog(parentFrame, "This file name already exists","File type Error", JOptionPane.ERROR_MESSAGE);
				return saveFile();
			} else if (savedFile.toString().contains(" ")) {
				JOptionPane.showMessageDialog(parentFrame, "File name must not contain spaces","File type Error", JOptionPane.ERROR_MESSAGE);
				return saveFile();
			} else {
				return savedFile.getAbsolutePath();
			}
		} else {
			return null;
		}
	}
	
}