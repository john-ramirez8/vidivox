package helpers;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
//This class is used to open windows in their correct locations
//It has 1 field which is the parent window from where the new window will be opened
public class WindowManager {
	private JPanel parentWindow;
	
	public WindowManager(JPanel p){
		parentWindow = p;
	}
	
	//This function takes as input a JFrame to open and opens in 100 X and 100 Y in from the parent window
	public void openWindow(JFrame windowToOpen){
		Point location = parentWindow.getLocationOnScreen();
		location.setLocation(location.getX() + 100, location.getY() + 100);
		windowToOpen.setLocation(location);
		windowToOpen.setVisible(true);
	}
}