

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Mock {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mock window = new Mock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 500, 650, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("图片打单系统-生产环境-7.6.1");
		//frame.setIconImage();

		
		
		
//		 DefaultTableModel model = new DefaultTableModel(new Object[][] { { "", "","","","" },     
//		      { "", "","","","" }, {"", "","","","" }, { "", "","","",""}, { "", "","","","" },     
//		      {"", "","","","" } }, new Object[] { "寄件电话", "寄件手机", "寄件公司", "寄件人姓名", "寄件地址" });       
//
//		JTable table = new JTable(model);
//
//		table.setPreferredScrollableViewportSize(new Dimension(550, 130));
//		table.setRowHeight(28);
//		table.setColumnSelectionInterval(0, 0);
//		table.setRowSelectionInterval(0, 0);
//		table.editCellAt(0, 0); // 编辑单元格
//		table.setEditingColumn(0);
//		table.setEditingRow(0);
//		
//		JScrollPane s = new JScrollPane(table);
//		frame.getContentPane().add(s, BorderLayout.CENTER);
//		
		JPanel panel = new JPanel();
      	frame.getContentPane().add(panel, BorderLayout.CENTER);
	
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);
	}

}
class MyEditor extends DefaultCellEditor {     
	  public MyEditor() {     
	    super(new JTextField());     
	  }     
	    
	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,     
	      int row, int column) {     
	    //获得默认表格单元格控件     
	    JTextField editor = (JTextField) super.getTableCellEditorComponent(table, value, isSelected,     
	        row, column);     
	    
	    if (value != null)     
	      editor.setText(value.toString());     
	    if (column == 0) {     
	      //设置对齐方式     
	      editor.setHorizontalAlignment(SwingConstants.CENTER);     
	      editor.setFont(new Font("Serif", Font.BOLD, 14));     
	    } else {     
	      editor.setHorizontalAlignment(SwingConstants.RIGHT);     
	      editor.setFont(new Font("Serif", Font.ITALIC, 12));     
	    }     
	    return editor;     
	  }     
	}   