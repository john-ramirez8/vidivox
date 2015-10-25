package gui;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class AudioTable extends AbstractTableModel {

	private HashMap<String, String> data;
	private Object[][] table;
	
	public AudioTable() {
		super();
		//setColumnIdentifiers(columnNames);
		//this.data = data;
	}
	
	@Override
	public int getColumnCount() { return 2; }

	@Override
	public int getRowCount() { return 0; }

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

}
