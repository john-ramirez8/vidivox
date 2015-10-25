package vidivox;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import vidivox.gui.Menu;

/**
 * This main class is used to instantiate and run the program
 * @author John Ramirez (jram948)
 * 
 */
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		/**
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
		    //If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {}
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