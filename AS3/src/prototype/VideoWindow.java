package prototype;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class videoPlayerDummy extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public videoPlayerDummy() {
		final addAudio aA = new addAudio();
		final addVoice aV = new addVoice();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(10,11, 864, 449);
		contentPane.add(panel);
		
		JButton btnAddAudio = new JButton("Add Audio");
		btnAddAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aA.setVisible(true);
			}
		});
		btnAddAudio.setBounds(10, 471, 200, 30);
		contentPane.add(btnAddAudio);
		
		JButton btnAddVoice = new JButton("Add Voice");
		btnAddVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aV.setVisible(true);
			}
		});
		btnAddVoice.setBounds(10, 520, 200, 30);
		contentPane.add(btnAddVoice);
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.setBounds(402, 470, 80, 80);
		contentPane.add(btnNewButton);
		
		JButton btnRewind = new JButton("<<<");
		btnRewind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRewind.setBounds(303, 499, 89, 23);
		contentPane.add(btnRewind);
		
		JButton btnFastforward = new JButton(">>>");
		btnFastforward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFastforward.setBounds(492, 499, 89, 23);
		contentPane.add(btnFastforward);
		
		JButton btnVolUp = new JButton("Volume Up");
		btnVolUp.setBounds(674, 471, 200, 30);
		contentPane.add(btnVolUp);
		
		JButton btnVolDown = new JButton("Volume Down");
		btnVolDown.setBounds(674, 520, 200, 30);
		contentPane.add(btnVolDown);
	}
}
