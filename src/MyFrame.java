import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    private JButton button;
    private static MyFrame frame = new MyFrame();

    public MyFrame() {
        button = new JButton("show");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 打开另一个窗口
                new AnotherFrame();
                frame.setVisible(false);
            }
        });
        this.add(button);
    }
 
    public static void main(String[] args) {
        frame.setTitle("Test");
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
 
// 另一个窗口
class AnotherFrame extends JFrame {
    public AnotherFrame() {
        setSize(400, 300);
        setVisible(true);
    }
}
