package vidivox.helpers;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class is used to show the number of remaining characters
 * that the user has when typing their commentary.
 * @author John Ramirez (jram948)
 * 
 */
public class RemainingCharacters implements DocumentListener {

	private JLabel label;
	private JTextArea textArea;
	
	public RemainingCharacters(JLabel label, JTextArea textArea) {
		this.label = label;
		this.textArea = textArea;
	}
	
	//These methods update whenever the user types in the text area.
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

	//Displays how many characters the user has left to type.
	public void update() {
		int length = 160 - textArea.getText().length();
		
		label.setText(length + " characters remaining");
	}
}
