package prototype;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddVoice extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	public AddVoice() {
		//Setting up the contentPanel
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(3,1));
		setTitle("Add Voice");
		
		//Component declarations
		textArea = new JTextArea(5, 20);
		JLabel lblEnterTheName = new JLabel("Enter commentary text (160 char limit)");
		JPanel buttonPane = new JPanel();
		JScrollPane scrollPane = new JScrollPane(textArea);
		JButton btnHear = new JButton("Hear");
		JButton btnCancel = new JButton("Cancel");
		JButton btnCreateMp3 = new JButton("Create MP3");
		
		final FileOpener fo = new FileOpener();
		
		//Setting up the label
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Setting up the textArea
		textArea.setColumns(10);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setDocument(new JTextFieldLimit(160)); //adding text limit
		
		
		//Setting up the hear button
		btnHear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = textArea.getText();
				if (message.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Text can't be empty");
				} else {
					String cmd = "echo " + message + " | festival --tts";
					ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
					
					try {
						pb.start();
					} catch (IOException e1) {
					}
				}
			}
		});
		btnHear.setPreferredSize(new Dimension(120, 23));
		
		//Setting up the cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setPreferredSize(new Dimension(120, 23));
		
		//Setting up the createMP3 button
		btnCreateMp3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fo.saveFile();
				//possibly use text2wave to create the audio file? not sure
			}
		});
		btnCreateMp3.setPreferredSize(new Dimension(120, 23));
		
		//Adding all components to the panel
		contentPane.add(lblEnterTheName, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		buttonPane.add(btnHear);
		buttonPane.add(btnCreateMp3);
		buttonPane.add(btnCancel);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		pack();
	}
}
