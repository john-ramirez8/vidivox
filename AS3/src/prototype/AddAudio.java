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

public class AddAudio extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public AddAudio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheName = new JLabel("Enter the name of an mp3 file");
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheName.setBounds(10, 11, 264, 25);
		contentPane.add(lblEnterTheName);
		
		textField = new JTextField();
		textField.setBounds(10, 47, 264, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnConfirm.setBounds(10, 78, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(185, 78, 89, 23);
		contentPane.add(btnCancel);
	}
}
