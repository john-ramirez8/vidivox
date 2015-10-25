package vidivox.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class sets the max character limit for JTextFields, JTextAreas etc
 * @author John Ramirez (jram948)
 * 
 */
@SuppressWarnings("serial")
public class JTextFieldLimit extends PlainDocument {
	private int limit;
	
	public JTextFieldLimit(int limit) {
		this.limit = limit;
	}
	
	//Creates the text character limit
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
		if (str.isEmpty() || (str == null)) {
			return;
		} else if ((getLength() + str.length() <= limit)) {
			str.toUpperCase();
			super.insertString(offset, str, set);
		}
	}
}
