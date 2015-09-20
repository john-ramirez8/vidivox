package prototype;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorWindow extends JFrame {

	private JPanel contentPane;
	private int errorNo;
	private String error1 = "Error: The file you chose  is not a .avi file.";
	private String error11 = "Please select a .avi file.";
	private String error2 = "Error: The file you chose is not a .mp3 file. Please select a .mp3 file.";
	private String error22 = "Please select a .mp3 file.";
	
	
	public ErrorWindow(int errorNo) {
		//Setting up the contentPanel
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,1));
		setTitle("Error");
		
		//Component declarations
		JLabel errMsg = new JLabel();
		JLabel errMsg2 = new JLabel();
		JButton btnOk = new JButton("Ok");
		
		//Setting up the label
		if (errorNo == 1){
			errMsg.setText(error1);
			errMsg2.setText(error11);
		} else if (errorNo == 2){
			errMsg.setText(error2);
			errMsg2.setText(error22);
		} else {
			errMsg.setText("Error message is an error");
		}
		errMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errMsg2.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Setting up the cancel button
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnOk.setPreferredSize(new Dimension(120,23));
		
		//Adding all components to the panel
		contentPane.add(errMsg);
		contentPane.add(errMsg2);
		contentPane.add(btnOk);
		pack();
	}
}
