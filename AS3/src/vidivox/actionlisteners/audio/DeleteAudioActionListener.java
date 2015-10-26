package vidivox.actionlisteners.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vidivox.gui.MergePanel;

/**
 * This class is used to delete the audio from the table.
 * This means that the user no longer wants to overlay that audio file
 * to the video.
 * @author John Ramirez (jram948)
 *
 */
public class DeleteAudioActionListener implements ActionListener {

	private MergePanel parentPanel;
	private JButton mergeBtn;
	
	public DeleteAudioActionListener(MergePanel parentPanel, JButton mergeBtn) {
		this.parentPanel = parentPanel;
		this.mergeBtn = mergeBtn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = parentPanel.getTable().getSelectedRow();

		//Ensures that a row is actually selected
		if (row < 0 ) {
			JOptionPane.showMessageDialog(parentPanel, "You must select an audio file from the table to delete",
					"Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String fileName = (String)parentPanel.getTable().getValueAt(row, 0);
			String filePath = parentPanel.getAudioNamesHashMap().remove(fileName);

			//Removes the associated audio file from HashMaps and ArrayList
			parentPanel.getAudioNamesHashMap().remove(fileName);
			parentPanel.getAudioTimesHashMap().remove(filePath);
			parentPanel.getArrayList().remove(filePath);
			parentPanel.getTableModel().removeRow(row);
			
			if (parentPanel.getArrayList().isEmpty()) {
				mergeBtn.setEnabled(false);
			}
			
		}
		
	}

}
