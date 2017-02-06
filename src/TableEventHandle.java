

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import bean.SenderInfo;
import util.DBUtil;
public class TableEventHandle implements TableModelListener {

	JTable table = null;
	Table mt = null;
	JLabel label = null; // 显示修改字段位置
	 
	public TableEventHandle() {

		JFrame f = new JFrame();
		mt = new Table(1);
		mt.addTableModelListener(this);

		table = new JTable(mt);

		table.setPreferredScrollableViewportSize(new Dimension(550, 30));
		table.setRowHeight(28);
		// table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//
		// table.setColumnSelectionAllowed(true);
		// table.setRowSelectionAllowed(true);
		////
		//// //将此 Component 的焦点状态设置为指定值。
		// table.setFocusable(true);
		// table.setCellSelectionEnabled(false);

		table.setColumnSelectionInterval(0, 0);
		table.setRowSelectionInterval(0, 0);
		table.editCellAt(0, 0); // 编辑单元格
		table.setEditingColumn(0);
		table.setEditingRow(0);

		table.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int row = table.getSelectedRow(); // 第几列
				int column = table.getSelectedColumn(); // 第几列
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ESCAPE) { // 【esc】 清除单元格
					mt.setValueAt("", row, column);
				}else if (code == KeyEvent.VK_DOWN) { // 【down】打开顺丰界面，上屏，保存，当前界面弹出，回到行首，全行清空
					String phone = (String) mt.getValueAt(0, 0);
					String mobile =   (String)mt.getValueAt(0,1);
					String company =  (String) mt.getValueAt(0, 2);
					String name =   (String)mt.getValueAt(0, 3);
					String address =  (String) mt.getValueAt(0, 4);
					try {
						int n = JniDll.upload(phone, mobile, company, name, address);
						label.setText("是否找到窗口："+n);
						//(new DBUtil<SenderInfo>()).insert(new SenderInfo(name, mobile, phone, address, company));
					} catch (InterruptedException e1) {
						
					} 
					for(int i=0;i<5;i++){
						mt.setValueAt("", 0, i);
					}
					table.setColumnSelectionInterval(0, 0);
					table.setRowSelectionInterval(0, 0);
					table.editCellAt(0, 0); // 编辑单元格
					table.setEditingColumn(0);
					table.setEditingRow(0);
					JniDll.sendDown();
				}
				else if (code == KeyEvent.VK_UP) { // 【up】打开顺丰界面，向上一行
					JniDll.sendUp();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int row = table.getSelectedRow(); // 第几列
				int column = table.getSelectedColumn(); // 第几列
				int code = e.getKeyCode();

				// 单元格shift + home 全选
				if (((e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0) && e.getKeyCode() == KeyEvent.VK_HOME) {
					table.editCellAt(row, column);
					JTextField editor = ((JTextField) table.getCellEditor().getTableCellEditorComponent(table,
							table.getValueAt(row, column), true, row, column));
					editor.selectAll();
				}else if (code == KeyEvent.VK_PAGE_DOWN) { // 【esc】 清除单元格
					System.out.println("开始上屏1");
				}
			}
		});

		JScrollPane s = new JScrollPane(table);

		label = new JLabel("修改字段位置：");
		f.getContentPane().add(s, BorderLayout.CENTER);
		f.getContentPane().add(label, BorderLayout.SOUTH);
		f.setTitle("TableEventHandle");
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		//label.setText("修改字段位置：" + (row + 1) + " 行 " + (column + 1) + " 列");
		String value = (String) mt.getValueAt(row, column);
		System.out.println("修改值为:" + value);
		if (column == 0 && value.length() == 11) { // 电话号码,填充完整
			// TODO 不到11 带出其他电话号码
			SenderInfo findOne = null;
			try {
				List<SenderInfo> results = new DBUtil().find(new SenderInfo(value));
				if (results != null && results.size() > 0) {
					findOne = results.get(0);
					mt.setValueAt(findOne.getMobile(), row, 1);
					mt.setValueAt(findOne.getCompany(), row, 2);
					mt.setValueAt(findOne.getName(), row, 3);
					mt.setValueAt(findOne.getAddress(), row, 4);
					table.setColumnSelectionInterval(0, 4);
				}
			} catch (SQLException e1) {

			}
			if (findOne == null && value.length() == 11) {
				mt.setValueAt(value, row, 1);
				table.setColumnSelectionInterval(0, 1);
			}

		}
		table.repaint();
	}

	public static void main(String args[]) {

		new TableEventHandle();
		// Toolkit toolkit = Toolkit.getDefaultToolkit();
		// // 注册应用程序全局键盘事件, 所有的键盘事件都会被此事件监听器处理.
		// toolkit.addAWTEventListener(
		// new java.awt.event.AWTEventListener() {
		// public void eventDispatched(AWTEvent event) {
		// if (event.getClass() == KeyEvent.class) {
		// KeyEvent kE = ((KeyEvent) event);
		// // 处理按键事件 Ctrl+Enter
		// if ((kE.getKeyCode() == KeyEvent.VK_ENTER)
		// && (((InputEvent) event)
		// .isControlDown())) {
		//
		//
		// } /* page down */else if (kE.getKeyCode() == KeyEvent.VK_PAGE_DOWN
		// && kE.getID() == KeyEvent.KEY_PRESSED) {
		// // System.out.println("page down:"
		// // + kE.getID());
		// System.out.println("page down");
		//
		//
		// } /* page up */else if (kE.getKeyCode() == KeyEvent.VK_PAGE_UP
		// && kE.getID() == KeyEvent.KEY_PRESSED) {
		// // System.out.println("page down:"
		// // + kE.getID());
		// System.out.println("page up");
		//
		//
		// }/* Ctrl +K */else if (kE.getKeyCode() == KeyEvent.VK_K
		// && kE.isControlDown()
		// && kE.getID() == KeyEvent.KEY_PRESSED) {
		// System.out.println("Ctrl +K");
		// }
		// /* home */else if (kE.getKeyCode() == KeyEvent.VK_HOME
		// && kE.getID() == KeyEvent.KEY_PRESSED) {
		// System.out.println("home");
		// }
		// /* end */else if (kE.getKeyCode() == KeyEvent.VK_END
		// && kE.getID() == KeyEvent.KEY_PRESSED) {
		// System.out.println("end");
		// }
		// }
		// }
		// }, java.awt.AWTEvent.KEY_EVENT_MASK);
	}

	

}
