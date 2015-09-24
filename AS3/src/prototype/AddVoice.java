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

@SuppressWarnings("serial")
public class AddVoice extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private String commentary;
	private CreateMP3 mp3Task;

	public AddVoice() {
		// Setting up the contentPanel
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocation(100, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(3, 1));
		setTitle("Add Voice");
		setResizable(false);

		// Component declarations
		textArea = new JTextArea(5, 20);
		JLabel lblEnterTheName = new JLabel("Enter commentary text (160 char limit)");
		JPanel buttonPane = new JPanel();
		JScrollPane scrollPane = new JScrollPane(textArea);
		JButton btnHear = new JButton("Hear");
		JButton btnCancel = new JButton("Cancel");
		JButton btnCreateMp3 = new JButton("Create MP3");

		final FileOpener fo = new FileOpener(".mp3", this);

		// Setting up the label
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.CENTER);

		// Setting up the textArea
		textArea.setColumns(10);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setDocument(new JTextFieldLimit(160)); // adding text limit

		// Setting up the hear button
		btnHear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commentary = textArea.getText();
				if (commentary.isEmpty() || commentary.trim().length() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Text can not be blank");
				} else {
					String cmd = "echo " + commentary + " | festival --tts";
					ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", cmd);
					try {
						Process p = pb.start();
					} catch (IOException e1) {
					}
				}
			}
		});
		btnHear.setPreferredSize(new Dimension(120, 23));

		// Setting up the cancel button
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setPreferredSize(new Dimension(120, 23));

		// Setting up the createMP3 button
		btnCreateMp3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mp3 = fo.saveFile();
				if (mp3 != null) {
					mp3 = mp3.substring(0, mp3.length() - 4);
					commentary = textArea.getText();
					
					mp3Task = new CreateMP3(commentary, mp3, contentPane);
					mp3Task.execute();
				}

			}
		});
		btnCreateMp3.setPreferredSize(new Dimension(120, 23));

		// Adding all components to the panel
		contentPane.add(lblEnterTheName, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		buttonPane.add(btnHear);
		buttonPane.add(btnCreateMp3);
		buttonPane.add(btnCancel);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		pack();
	}
}
