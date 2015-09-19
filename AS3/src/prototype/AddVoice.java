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
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddVoice extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public AddVoice() {
		//Setting up the contentPanel
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocation(100, 100);
		setSize(434, 111);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Add Voice");
		
		//Component declarations
		textField = new JTextField();
		JLabel lblEnterTheName = new JLabel("Enter commentary text");
		JButton btnHear = new JButton("Hear");
		JButton btnCancel = new JButton("Cancel");
		JButton btnCreateMp3 = new JButton("Create MP3");
		
		//Setting up the label
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheName.setBounds(10, 11, 414, 25);
		
		//Setting up the textField
		textField.setBounds(10, 47, 414, 20);
		textField.setColumns(10);
		
		//Setting up the hear button
		btnHear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle r = getBounds();
				double h = r.height;
				double w = r.width;
				
				System.out.println("Dimensions are " + h +" high and " + w + " wide. Fuck my life man" );
			}
		});
		btnHear.setBounds(10, 78, 110, 23);
		
		//Setting up the cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(314, 78, 110, 23);
		
		//Setting up the createMP3 button
		btnCreateMp3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateMp3.setBounds(162, 78, 110, 23);
		btnCreateMp3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		//Adding all components to the panel
		contentPane.add(lblEnterTheName);
		contentPane.add(textField);
		contentPane.add(btnHear);
		contentPane.add(btnCancel);
		contentPane.add(btnCreateMp3);
	}
}
