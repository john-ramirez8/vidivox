package helpers;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is used to open the windows in their correct locations.
 * It has one field is the parent window from where the new window will be opened.
 * @author John Ramirez (jram948)
 * 
 */
public class WindowManager {
	private JPanel parentWindow;
	
	//Constructor
	public WindowManager(JPanel p){
		parentWindow = p;
	}
	
	/**
	 * This method takes a JFrame to open and opens it in 100X and 100Y from the parent window
	 * @param windowToOpen - the JFrame we wish to open
	 */
	public void openWindow(JFrame windowToOpen){
		Point location = parentWindow.getLocationOnScreen();
		location.setLocation(location.getX() + 100, location.getY() + 100);
		windowToOpen.setLocation(location);
		windowToOpen.setVisible(true);
	}
}