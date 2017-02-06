import javax.swing.table.AbstractTableModel;


public class Table  extends AbstractTableModel {


	private static final long serialVersionUID = 567249425190497308L;

	Object[][] p = null;

	String[] n = { "寄件电话", "寄件手机", "寄件公司", "寄件人姓名", "寄件地址" };
	
	public Table(int row){
		p = new Object[row][5];
		for(int index1 =0 ; index1 <row ;index1++ ){
			p[index1] = new Object[]{"","","","",""};
		}
	}

	public int getColumnCount() {
		return n.length;
	}

	public int getRowCount() {
		return p.length;
	}

	public String getColumnName(int col) {
		return n[col];
	}

	public Object getValueAt(int row, int col) {
		return p[row][col];
	}


	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public void setValueAt(Object value, int row, int col) {
		p[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public void mySetValueAt(Object value, int row, int col) {
		p[row][col] = value;
	}
}
