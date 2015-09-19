package prototype;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	public AddAudio() {
		//Setting up the contentPanel
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(100, 100);
		setSize(284, 111);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(30, 0));
		setTitle("Add Audio");
		
		//Component declarations
		textField = new JTextField();
		JLabel lblEnterTheName = new JLabel("Enter the name of an mp3 file");
		JButton btnConfirm = new JButton("Confirm");
		JButton btnCancel = new JButton("Cancel");
		
		//Setting up the label
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheName.setBounds(10, 11, 264, 25);
		
		//Setting up the textField
		//textField.setBounds(10, 47, 264, 20);
		textField.setColumns(10);
		textField.setPreferredSize(new Dimension(264,20));
		
		//Setting up the confirm button
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		//btnConfirm.setBounds(10, 78, 89, 23);
		btnConfirm.setPreferredSize(new Dimension(89,23));
		
		//Setting up the cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		//btnCancel.setBounds(185, 78, 89, 23);
		btnConfirm.setPreferredSize(new Dimension(89,23));
		
		//Adding all components to the panel
		contentPane.add(lblEnterTheName, BorderLayout.NORTH);
		contentPane.add(textField, BorderLayout.CENTER);
		contentPane.add(btnConfirm, BorderLayout.SOUTH);
		contentPane.add(btnCancel, BorderLayout.SOUTH);
	}
}
