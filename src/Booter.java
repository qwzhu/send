

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class Booter  {

	private JFrame frame;
	
	private JniDll dll;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booter window = new Booter();
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
	public Booter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("测试第一版功能");
		String[] columnNames = {"寄件电话",
                "寄件手机",
                "寄件公司",
                "寄件人姓名",
                "寄件地址"};
		String[][] data = {
			    {"", "",
			     "", "", ""}
			  };
		final JTable table = new JTable(data, columnNames);
		table.setSelectionBackground(Color.WHITE);
		table.setRowHeight(28);
		
		
	      table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("key event 1:"+e.getKeyCode());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("key event w:"+e.getKeyCode());

			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				table.repaint();
				int row = table.getSelectedRow(); //第几列
				int column = table.getSelectedColumn(); //第几列
				int code = e.getKeyCode();
				if(column == 0){ //电话号码
					if(code == 10){
						String ss = (String) table.getModel().getValueAt(row, column);
						System.out.println("ss:"+ss);
					}
				}else if(column == 1){ //手机号码
					
				}else if(column == 4){ //寄件地址，最后一个
					
				}

			}
		});
		table.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				System.out.println("修改字段位置：" + (row + 1) + " 行 " + (column + 1) + " 列");
//				boolean cheat = ((Boolean) (mt.getValueAt(row, 6))).booleanValue();
//				int grade1 = ((Integer) (mt.getValueAt(row, 2))).intValue();
//				int grade2 = ((Integer) (mt.getValueAt(row, 3))).intValue();
//				int total = grade1 + grade2;
//				if (cheat) {
//					if (total > 120)
//						mt.mySetValueAt(new Integer(119), row, 4);
//					else
//						mt.mySetValueAt(new Integer(total), row, 4);
//					mt.mySetValueAt(new Boolean(false), row, 5);
//				} else {
//					if (total > 120)
//						mt.mySetValueAt(new Boolean(true), row, 5);
//					else
//						mt.mySetValueAt(new Boolean(false), row, 5);
//
//					mt.mySetValueAt(new Integer(total), row, 4);
//				}
			table.repaint();
				
			}
		});
	    
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		 
	}
	
	

}
