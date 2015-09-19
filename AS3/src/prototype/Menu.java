package prototype;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel mainMenu;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		final VideoWindow vp = new VideoWindow();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		mainMenu = new JPanel();
		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainMenu);
		mainMenu.setLayout(null);
		
		JLabel lblVidiVox = new JLabel("VIDI VOX");
		lblVidiVox.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblVidiVox.setHorizontalAlignment(SwingConstants.CENTER);
		lblVidiVox.setBounds(17, 11, 400, 78);
		mainMenu.add(lblVidiVox);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(117, 164, 200, 50);
		mainMenu.add(btnExit);
		
		JButton btnSelectVideo = new JButton("Select Video");
		btnSelectVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.setVisible(true);
				setVisible(false);
			}
		});
		btnSelectVideo.setBounds(117, 100, 200, 50);
		mainMenu.add(btnSelectVideo);
	}
}
