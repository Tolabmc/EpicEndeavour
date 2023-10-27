import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class MyFrame extends JFrame implements ActionListener{

    JButton button;
    JLabel label;
    MyFrame() {

        label = new JLabel();
        label.setBounds(150,250,250,150);
        label.setVisible(false);
        label.setText("Look out! David is behind you");
        button = new JButton();
        button.setBounds(100, 100, 250, 100);
        button.addActionListener(this);
        button.setText("I'm a button!");
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans",Font.BOLD,25));
        button.setForeground(Color.cyan);
        button.setBackground(Color.lightGray);
        button.setBorder(BorderFactory.createBevelBorder(1,Color.cyan, Color.blue));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.add(button);
        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button){
            System.out.println("warned u");
            label.setVisible(true);
        }
    }
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
