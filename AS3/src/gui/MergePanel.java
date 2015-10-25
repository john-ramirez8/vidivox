package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gui.actionlisteners.audio.AddAudioToTableActionListener;
import gui.actionlisteners.audio.DeleteAudioActionListener;
import gui.actionlisteners.audio.MergeAudioActionListener;
import helpers.JTextFieldLimit;

/**
 * This MergePanel class is used to show the panel where users
 * can merge multiple audio files to the video.
 * @author John Ramirez (jram948)
 * 
 */
@SuppressWarnings("serial")
public class MergePanel extends JPanel {

	private final int MAX_INSERT_TIME_LIMIT = 2;
	
	private HashMap<String, String> audioTimes = new HashMap<String, String>();
	private HashMap<String, String> audioNames = new HashMap<String, String>();
	private ArrayList<String> listOfAudio = new ArrayList<String>();
	private JTable audioTable;
	private String[] columnNames = { "Audio file name", "Time to insert" };
	private DefaultTableModel model;
	
	public MergePanel(VideoWindow parent) {
		
		setLayout(new BorderLayout());

		//Creating the table for audio to add
		audioTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(audioTable);
		audioTable.setFillsViewportHeight(true);
		model = new DefaultTableModel();
		audioTable.setModel(model);
		model.setColumnIdentifiers(columnNames);
		
		//Creating the various panels needed
		JPanel filePanel = new JPanel();
		JButton mergeBtn = new JButton("Merge with video");
		JButton deleteBtn = new JButton("Delete");
		filePanel.add(mergeBtn);
		filePanel.add(deleteBtn);
		
		JPanel insertAudioPanel = new JPanel();
		JButton fileChooseBtn = new JButton("Add audio");
		JLabel timeLabel = new JLabel("Time:");
		JLabel colon = new JLabel(":");
		JTextField insertMinutes = new JTextField(2);
		JTextField insertSeconds = new JTextField(2);	
		insertAudioPanel.add(fileChooseBtn);
		insertAudioPanel.add(timeLabel);
		insertAudioPanel.add(insertMinutes);
		insertAudioPanel.add(colon);
		insertAudioPanel.add(insertSeconds);
		
		//Creates the panel that holds the table and corresponding buttons
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		tablePanel.add(insertAudioPanel, BorderLayout.SOUTH);
		
		//Setting up the textfield limit and tooltip
		insertMinutes.setDocument(new JTextFieldLimit(MAX_INSERT_TIME_LIMIT));
		insertSeconds.setDocument(new JTextFieldLimit(MAX_INSERT_TIME_LIMIT));
		insertMinutes.setToolTipText("Time must be in format mm:ss");
		insertSeconds.setToolTipText("Time must be in format mm:ss");
		
		//Creating action listeners for the buttons
		ActionListener addAL = new AddAudioToTableActionListener(parent, insertMinutes,
				insertSeconds, model, this);
		ActionListener mergeAL = new MergeAudioActionListener(this, parent);
		ActionListener deleteAL = new DeleteAudioActionListener(this);
		fileChooseBtn.addActionListener(addAL);
		mergeBtn.addActionListener(mergeAL);
		deleteBtn.addActionListener(deleteAL);
		
		add(tablePanel, BorderLayout.CENTER);
		add(filePanel, BorderLayout.SOUTH);
	}
	
	//Getters for the HashMaps, ArrayList, DefaultTableModel and JTable
	public HashMap<String, String> getAudioTimesHashMap() { return audioTimes; }
	public HashMap<String, String> getAudioNamesHashMap() { return audioNames; }
	public ArrayList<String> getArrayList() { return listOfAudio; }
	public DefaultTableModel getTableModel() { return model; }
	public JTable getTable() { return audioTable; }
}
