package prototype;

import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

//This is the class responsible for launching the application
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Sets look and feel to the users Systems look and feel
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.put("Slider.paintValue", false);
		
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