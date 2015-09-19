package prototype;

import javax.swing.SwingUtilities;

//This is the class responsible for launching the application
public class Main {
	public static void main(String[] args) {
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