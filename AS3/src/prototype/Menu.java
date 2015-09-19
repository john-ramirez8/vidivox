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

	private JPanel contentPane;
	
	
	public Menu() {
		//JFrames to open
		final VideoWindow vp = new VideoWindow();
		
		//Setting up the content pane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setSize(434, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Menu");
		
		//Component declarations
		JLabel lblVidiVox = new JLabel("VIDI VOX");
		JButton btnExit = new JButton("EXIT");
		JButton btnSelectVideo = new JButton("Select Video");
		
		//Setting up the label
		lblVidiVox.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblVidiVox.setHorizontalAlignment(SwingConstants.CENTER);
		lblVidiVox.setBounds(17, 11, 400, 78);
		
		//Setting up the exit button
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(117, 154, 200, 50);
		
		//Setting up the selectVideo button
		btnSelectVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.setVisible(true);
				setVisible(false);
			}
		});
		btnSelectVideo.setBounds(117, 90, 200, 50);
		
		//Adding all components to the panel
		contentPane.add(lblVidiVox);
		contentPane.add(btnExit);
		contentPane.add(btnSelectVideo);
	}
}
