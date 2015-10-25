package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.actionlisteners.audio.FestivalActionListener;
import gui.actionlisteners.audio.SaveMP3ActionListener;
import helpers.FileOpener;
import helpers.JTextFieldLimit;
import helpers.RemainingCharacters;
import swingworker.CreateMP3;
import swingworker.Festival;

@SuppressWarnings("serial")
public class CreateMP3Panel extends JPanel {
	
	private final int MAX_CHAR_LIMIT = 250;
	
	private JLabel panelTitle;
	private JPanel textPanel;
	private JTextArea textArea;
	private CreateMP3 mp3Task;
	private Festival voiceTask;
	
	public CreateMP3Panel(JFrame parent) {
		
		final FileOpener fo = new FileOpener(".mp3", parent);
		setLayout(new BorderLayout());
		
		panelTitle = new JLabel("Create commentary");
		
		//Creating the text panel
		textPanel = new JPanel(new BorderLayout());
		textArea = new JTextArea(10, 25);
		JLabel textTitle = new JLabel("250 characters remaining");

		//Modifying textArea to get max character limit
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setDocument(new JTextFieldLimit(MAX_CHAR_LIMIT));
		textArea.getDocument().addDocumentListener(new RemainingCharacters(textTitle, textArea));
		
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(textTitle, BorderLayout.SOUTH);
		
		//Creating the buttons panel to hear/stop festival
		JPanel festivalButtonsPanel = new JPanel();
		JButton hearBtn = new JButton("Preview");
		JButton stopBtn = new JButton("Stop");	
		festivalButtonsPanel.add(hearBtn);
		festivalButtonsPanel.add(stopBtn);
		
		stopBtn.setEnabled(false); //Originally disable stop button
		
		//Creating the panel to save audio
		JPanel savePanel = new JPanel();
		JButton saveBtn = new JButton("Save as MP3");
		savePanel.add(saveBtn);
		
		//Creating ActionListeners for the buttons
		FestivalActionListener previewAudioAL = new FestivalActionListener(parent,
				textArea, hearBtn, stopBtn, this, false);
		FestivalActionListener stopAudioAL = new FestivalActionListener(parent,
				textArea, hearBtn, stopBtn, this, true);
		SaveMP3ActionListener saveMP3AL = new SaveMP3ActionListener(parent,
				this, textArea, fo);
		
		//ActionListeners for the buttons
		hearBtn.addActionListener(previewAudioAL);
		stopBtn.addActionListener(stopAudioAL);
		saveBtn.addActionListener(saveMP3AL);

		
		//Creating nested panels
		JPanel buttonsPanel = new JPanel(new BorderLayout());
		buttonsPanel.add(festivalButtonsPanel, BorderLayout.CENTER);
		buttonsPanel.add(savePanel, BorderLayout.SOUTH);
		
		add(panelTitle, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public CreateMP3 getMp3Task() { return mp3Task; }
	public Festival getVoiceTask() { return voiceTask; }
	public void setMp3Task(CreateMP3 mp3Task) {
		this.mp3Task = mp3Task;
	}
	public void setVoiceTask(Festival voiceTask) {
		this.voiceTask = voiceTask;
	}
	
}
