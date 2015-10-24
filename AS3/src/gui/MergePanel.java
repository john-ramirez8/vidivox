package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MergePanel extends JPanel {

	private JLabel panelTitle;
	private JTable audioTable;
	private String[] columnNames = { "Audio file name", "Time to insert audio" };
	
	public MergePanel() {
		
		setLayout(new BorderLayout());

		panelTitle = new JLabel("Merge audio to video");
		audioTable = new JTable(20, 2);
		
		JScrollPane scrollPane = new JScrollPane(audioTable);
		audioTable.setFillsViewportHeight(true);
		
		JButton fileChooseBtn = new JButton("Choose audio to add");
		JButton playBtn = new JButton("Play");
	}
	
	
	
}
