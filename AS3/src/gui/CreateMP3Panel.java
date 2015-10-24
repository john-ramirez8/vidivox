package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.actionlisteners.SaveMP3ActionListener;
import helpers.FileOpener;
import helpers.JTextFieldLimit;
import swingworker.CreateMP3;
import swingworker.Festival;

@SuppressWarnings("serial")
public class CreateMP3Panel extends JPanel {
	
	private final int MAX_CHAR_LIMIT = 160;
	private final int MAX_INSERT_TIME_LIMIT = 5;
	
	private JLabel panelTitle;
	private JTextArea textArea;
	private String commentary;
	private CreateMP3 mp3Task;
	private JPanel contentPane;
	private JFrame parent;
	
	public CreateMP3Panel(JFrame parent) {
		
		this.parent = parent;
		contentPane = this;
		final FileOpener fo = new FileOpener(".mp3", parent);
		setLayout(new BorderLayout());
		
		//Creating the main components to be added
		panelTitle = new JLabel("Create commentary (max 160 words)");
		textArea = new JTextArea(10, 25);
		JPanel buttonsPanel = new JPanel(new BorderLayout());

		//Modifying textArea to get max character limit
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setDocument(new JTextFieldLimit(MAX_CHAR_LIMIT));
		
		//Creating the buttons panel to hear/stop festival
		JPanel festivalButtonsPanel = new JPanel();		
		JButton hearBtn = new JButton("Play");
		JButton stopBtn = new JButton("Stop");	
		festivalButtonsPanel.add(hearBtn);
		festivalButtonsPanel.add(stopBtn);
		
		//Creating the panel for inserting audio at a certain time
		JPanel timePanel = new JPanel();
		JLabel titleLabel = new JLabel("Insert at time:");
		JTextField insertTime = new JTextField(5);
		insertTime.setDocument(new JTextFieldLimit(MAX_INSERT_TIME_LIMIT));
		
		timePanel.add(titleLabel);
		timePanel.add(insertTime);
		
		//Creating the panel to save/merge audio
		JPanel fileButtonsPanel = new JPanel();
		JButton saveBtn = new JButton("Save as MP3");
		JButton mergeBtn = new JButton("Merge with video");
		fileButtonsPanel.add(saveBtn);
		fileButtonsPanel.add(mergeBtn);
		
		//Creating ActionListeners for the buttons
		SaveMP3ActionListener saveMP3AL = new SaveMP3ActionListener(parent,
				this, textArea, fo);
		
		//ActionListeners for the save/merge audio buttons
		saveBtn.addActionListener(saveMP3AL);
		
		//Creating nested panels
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(festivalButtonsPanel, BorderLayout.NORTH);
		topPanel.add(timePanel, BorderLayout.SOUTH);
		
		buttonsPanel.add(fileButtonsPanel, BorderLayout.SOUTH);
		buttonsPanel.add(topPanel, BorderLayout.CENTER);
		
		add(panelTitle, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public CreateMP3 getMp3Task() { return mp3Task; }
	public void setMp3Task(CreateMP3 mp3Task) {
		this.mp3Task = mp3Task;
		
	}
	
}
