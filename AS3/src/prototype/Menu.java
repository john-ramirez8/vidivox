package prototype;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	
	public Menu() {
		//JFrames to open
		final VideoWindow vp = new VideoWindow();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		//setSize(434, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 100, 20, 100));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1,0 , 10));
		setTitle("Menu");
		
		//Component declarations
		JLabel lblVidiVox = new JLabel();
		JButton btnExit = new JButton("EXIT");
		JButton btnSelectVideo = new JButton("Select Video");
		
		//Setting up the label
		ImageIcon img = new ImageIcon("Images/logo.png");
		lblVidiVox.setIcon(img);
		lblVidiVox.setPreferredSize(new Dimension(300, 80));
		
		//Setting up the exit button
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setPreferredSize(new Dimension(200, 50));
		
		//Setting up the selectVideo button
		btnSelectVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.setVisible(true);
				setVisible(false);
			}
		});
		btnSelectVideo.setPreferredSize(new Dimension(200, 50));
		
		//Adding all components to the panel
		contentPane.add(lblVidiVox);
		contentPane.add(btnExit);
		contentPane.add(btnSelectVideo);
		pack();
	}
}
