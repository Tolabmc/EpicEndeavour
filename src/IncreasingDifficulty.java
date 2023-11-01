package src;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncreasingDifficulty {
    private JTextField textfield;
    public IncreasingDifficulty() {
        // Create a JFrame for the welcome page
        JFrame frame = new JFrame("Increasing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(new Color(99, 111, 237));
        frame.setLayout(null);
        frame.setResizable(false);


        textfield = new JTextField();
        textfield.setBounds(0, 0, 650, 100);
        textfield.setBackground(new Color(217, 25, 25));
        textfield.setForeground(new Color(151, 255, 255));
        textfield.setFont(new Font("Impact", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        textfield.setText("Welcome to Increasing Difficulty");
        frame.add(textfield);

        // Create a subheading label
        JLabel subheadingLabel = new JLabel("Choose your topic");
        subheadingLabel.setBounds(0, 100, 650, 100);
        subheadingLabel.setBackground(new Color(217, 25, 25));
        subheadingLabel.setForeground(new Color(151, 255, 255));
        subheadingLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
        subheadingLabel.setBorder(BorderFactory.createBevelBorder(1));
        subheadingLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(subheadingLabel);

        // Create buttons for different games
        JButton discreteMathematics = createGameButton("Discrete Mathematics", "DiscreteMaths.java", frame);
        JButton compOrg = createGameButton("Computer Organisation", "CompOrg.java", frame);
        JButton compSci = createGameButton("Computer Science", "CompSci.java", frame);

        // Position the buttons
        discreteMathematics.setBounds(175, 200, 300, 50);
        compOrg.setBounds(175, 275, 300, 50);
        compSci.setBounds(175, 350, 300, 50);

        // Add the buttons to the frame
        frame.add(discreteMathematics);
        frame.add(compOrg);
        frame.add(compSci);


        // Make the welcome page visible
        frame.setVisible(true);
    }

    private JButton createGameButton(String buttonText, String className, JFrame frame) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font("MV Boli", Font.BOLD, 18));
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Launch the selected game
                JButton selectedButton = (JButton) e.getSource();
                String buttonText = selectedButton.getText();
                if(buttonText == "Discrete Mathematics"){
                    DiscreteMaths discreteMaths = new DiscreteMaths();
                }
                    if(buttonText == "Computer Organisation"){
                       CompOrg compOrg = new CompOrg();
                    }

                if(buttonText == "Computer Science"){
                    CompSci compSci = new CompSci();

                }
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IncreasingDifficulty());
    }
}
