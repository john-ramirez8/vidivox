package helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
	private int limit;
	
	public JTextFieldLimit(int limit) {
		this.limit = limit;
	}
	
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
		if (str.isEmpty() || (str == null)) {
			return;
		} else if ((getLength() + str.length() <= limit)) {
			str.toUpperCase();
			super.insertString(offset, str, set);
		}
	}
}