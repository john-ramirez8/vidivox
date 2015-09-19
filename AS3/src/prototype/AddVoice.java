package prototype;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddVoice extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public AddVoice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheName = new JLabel("Enter commentary text");
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheName.setBounds(10, 11, 414, 25);
		contentPane.add(lblEnterTheName);
		
		textField = new JTextField();
		textField.setBounds(10, 47, 414, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnHear = new JButton("Play");
		btnHear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHear.setBounds(10, 78, 110, 23);
		contentPane.add(btnHear);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(314, 78, 110, 23);
		contentPane.add(btnCancel);
		
		JButton btnCreateMp = new JButton("Create MP3");
		btnCreateMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateMp.setBounds(162, 78, 110, 23);
		contentPane.add(btnCreateMp);
	}
}
