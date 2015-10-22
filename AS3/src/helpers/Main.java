package helpers;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import gui.Menu;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

//This is the class responsible for launching the application
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Sets look and feel to the users Systems look and feel
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		//UIManager.put("Slider.paintValue", false);
		
		/*
		 * This code sets the look and feel of the GUI to Nimbus
		 * Code taken from http://stackoverflow.com/questions/4617615/how-to-set-nimbus-look-and-feel-in-main
		 */
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		    	//do nothing
		    }
		}
		
		//Finding the users vlcj library
		new NativeDiscovery().discover();
		
		//Declares main menu and launches it
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
        		Menu menu = new Menu();
        		menu.setVisible(true);
            }
        });
	}
}