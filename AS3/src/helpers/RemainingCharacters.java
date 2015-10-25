package helpers;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * This class is used to show the number of remaining characters
 * that the user has when typing their commentary.
 */
public class RemainingCharacters implements DocumentListener {

	private JLabel label;
	private JTextArea textArea;
	
	public RemainingCharacters(JLabel label, JTextArea textArea) {
		this.label = label;
		this.textArea = textArea;
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		update();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		update();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		update();
	}

	public void update() {
		int length = 250 - textArea.getText().length();
		
		label.setText(length + " characters remaining");
	}
}
