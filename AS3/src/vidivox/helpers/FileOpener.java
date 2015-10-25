package vidivox.helpers;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class is a helper class that is used to open and save files (such as opening
 * .mp3 or .avi files that will be used).
 * @author John Ramirez (jram948)
 * 
 */
public class FileOpener {
	private String desiredExtension;
	private JFileChooser fc = new JFileChooser();
	private FileNameExtensionFilter aviFilter = new FileNameExtensionFilter("avi Files (*.avi)", "avi");
	private FileNameExtensionFilter mp3Filter = new FileNameExtensionFilter("mp3 Files (*.mp3)", "mp3");
	private File openedFile;
	private File savedFile;
	private JFrame parentFrame;
	private String errMessage;

	//Constructor sets the default error message and the parent frame of the file chooser.
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
	
	/**
	 * This method opens a file chooser and allows the user to choose a file. If the file is
	 * of incorrect file type, then the function calls itself recursively to allow the user to try again.
	 * @return the file that's going to be opened by the users, or null otherwise.
	 */
	public File openFile() {
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			openedFile = fc.getSelectedFile();
			String extension = openedFile.toString().substring(openedFile.toString().length() - 4);
			if (!extension.equals(desiredExtension)) {
				JOptionPane.showMessageDialog(parentFrame, errMessage, "File type Error", JOptionPane.ERROR_MESSAGE);
				return openFile();
			} else {
				return openedFile;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * This method determines where to save the file by finding the path of it.
	 * If the file is of incorrect file type then an error message is shown and the
	 * method recursively calls itself to allow the user to try again.
	 * @return a string containing the path of the file that is to be saved.
	 */
	public String saveFile() {
		int result = fc.showDialog(null, "Save");
		
		if (result == JFileChooser.APPROVE_OPTION) {
			savedFile = fc.getSelectedFile();
			String filePath = savedFile.getAbsolutePath();
			if(!filePath.endsWith(desiredExtension)) {
			    savedFile = new File(filePath + desiredExtension);
			}
			
			if (savedFile.exists()) {
				JOptionPane.showMessageDialog(parentFrame, "This file name already exists",
						"File type Error", JOptionPane.ERROR_MESSAGE);
				return saveFile();
			} else if (savedFile.toString().contains(" ")) {
				JOptionPane.showMessageDialog(parentFrame, "File name must not contain spaces",
						"File type Error", JOptionPane.ERROR_MESSAGE);
				return saveFile();
			} else {
				return savedFile.getAbsolutePath();
			}
		} else {
			return null;
		}
	}
	
}