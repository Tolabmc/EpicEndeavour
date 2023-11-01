import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Welcome {
        private JTextField textfield;
        public Welcome() {
            // Create a JFrame for the welcome page
            JFrame frame = new JFrame("Welcome to the Game Center");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(650, 650);
            frame.getContentPane().setBackground(new Color(99, 111, 237));
            frame.setLayout(null);
            frame.setResizable(false);

            // Create a title label
          /*  JLabel titleLabel = new JLabel("Welcome");
            titleLabel.setBounds(0, 50, 650, 50);
            titleLabel.setBackground(new Color(217, 25, 25));
            titleLabel.setForeground(new Color(151, 255, 255));
            titleLabel.setFont(new Font("Impact", Font.BOLD, 30));
            titleLabel.setBorder(BorderFactory.createBevelBorder(1));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            frame.add(titleLabel);

           */
            textfield = new JTextField();
            textfield.setBounds(0, 0, 650, 100);
            textfield.setBackground(new Color(217, 25, 25));
            textfield.setForeground(new Color(151, 255, 255));
            textfield.setFont(new Font("Impact", Font.BOLD, 30));
            textfield.setBorder(BorderFactory.createBevelBorder(1));
            textfield.setHorizontalAlignment(JTextField.CENTER);
            textfield.setEditable(false);
            textfield.setText("Welcome");
            frame.add(textfield);

            // Create a subheading label
            JLabel subheadingLabel = new JLabel("Choose your game");
            subheadingLabel.setBounds(0, 100, 650, 100);
            subheadingLabel.setBackground(new Color(217, 25, 25));
            subheadingLabel.setForeground(new Color(151, 255, 255));
            subheadingLabel.setFont(new Font("MV Boli", Font.BOLD, 20));
            subheadingLabel.setBorder(BorderFactory.createBevelBorder(1));
            subheadingLabel.setHorizontalAlignment(JLabel.CENTER);
            frame.add(subheadingLabel);

            // Create buttons for different games
            JButton randomGameButton = createGameButton("Random", "RandomQuiz.java", frame);
            JButton increasingDifficultyButton = createGameButton("Increasing Difficulty", "IncreasingDifficulty.java", frame);
            JButton timedQuizButton = createGameButton("Timed", "TimedQuiz.java", frame);

            // Position the buttons
            randomGameButton.setBounds(225, 200, 200, 50);
            increasingDifficultyButton.setBounds(225, 275, 200, 50);
            timedQuizButton.setBounds(225, 350, 200, 50);

            // Add the buttons to the frame
            frame.add(randomGameButton);
            frame.add(increasingDifficultyButton);
            frame.add(timedQuizButton);


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
                    if(buttonText == "Random"){
                        RandomQuiz randomQuiz = new RandomQuiz();
                    }
                   /* if(buttonText == "Increasing Difficulty"){
                       IncreasingDifficulty increasingDifficulty = new IncreasingDifficulty;
                    }
                   */
                    if(buttonText == "Timed"){
                        TimedQuiz timedQuiz = new TimedQuiz();
                    }
                }
            });
            return button;
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new Welcome());
        }
    }

