import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TimedQuiz implements ActionListener {

    private String[] questions;
    private String[][] options;
    private char[] answers;
    private char guess;
    private char answer;
    private int index;
    int correct_answers = 0;
    private int total_questions;
    private int result;
    int seconds = 10;

    private char[] userAnswers;

    private JFrame frame;
    private JTextField textfield;
    private JTextArea textarea;
    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;
    private JButton buttonD;
    private JButton playAgain;
    private JButton backButton;
    private JLabel answer_labelA;
    private JLabel answer_labelB;
    private JLabel answer_labelC;
    private JLabel answer_labelD;
    private JTextField number_right;
    private JTextField percentage;
    private JLabel time_label = new JLabel();
    private JLabel seconds_left = new JLabel();



    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private int[] questionOrder;  // Store the order of questions
    private int currentQuestionIndex;  // Keep track of the current question index

    Timer timer = new Timer(1000, new ActionListener() {    //Sets 1 second timer
        @Override
        public void actionPerformed(ActionEvent e) { //After every second elapses the action is performe
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if (seconds <= 0) {
                displayAnswer();
            }
        }
    });


    private Statistics statistics;

    public TimedQuiz() {
        frame = new JFrame("TimedQuiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(new Color(99, 111, 237));
        frame.setLayout(null);
        frame.setResizable(false);

        textfield = new JTextField();
        textarea = new JTextArea();
        buttonA = new JButton();
        buttonB = new JButton();
        buttonC = new JButton();
        buttonD = new JButton();
        answer_labelA = new JLabel();
        answer_labelB = new JLabel();
        answer_labelC = new JLabel();
        answer_labelD = new JLabel();
        number_right = new JTextField();
        percentage = new JTextField();
        playAgain = new JButton();
        backButton = new JButton();

        // Initialize the database connection
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:epicdatabase.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load questions and answers from the database
        loadQuestionsFromDatabase();

        total_questions = questions.length;

        // Initialize GUI components
        textfield.setBounds(0, 0, 650, 50);
        textfield.setBackground(new Color(217, 25, 25));
        textfield.setForeground(new Color(151, 255, 255));
        textfield.setFont(new Font("Impact", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        textfield.setText("Welcome");

        textarea.setBounds(0, 50, 650, 120);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(217, 25, 25));
        textarea.setForeground(new Color(151, 255, 255));
        textarea.setFont(new Font("MV Boli", Font.BOLD, 18));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);
        textarea.setText("Sample text");

        buttonA.setBounds(0, 175, 100, 100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 25));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 275, 100, 100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 25));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 375, 100, 100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 25));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 475, 100, 100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 25));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");


        playAgain.setBounds(225, 425, 200, 50);
        playAgain.setFont(new Font("MV Boli", Font.BOLD, 20));
        playAgain.setFocusable(false);
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the quiz and start a new one
                resetQuiz();
            }
        });
        backButton.setBounds(225, 480, 200, 50);
        backButton.setBackground(new Color(217, 25, 25));
        backButton.setFont(new Font("MV Boli", Font.BOLD, 20));
        backButton.setFocusable(false);
        backButton.setText("Back to Home");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current quiz frame
                new Welcome(); // Open the Welcome page
            }
        });




        answer_labelA.setBounds(125, 175, 600, 100);
        answer_labelA.setBackground(new Color(99, 111, 237));
        answer_labelA.setForeground(new Color(151, 255, 255));
        answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 10));
        answer_labelA.setText("yes");

        answer_labelB.setBounds(125, 275, 600, 100);
        answer_labelB.setBackground(new Color(99, 111, 237));
        answer_labelB.setForeground(new Color(151, 255, 255));
        answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 10));
        answer_labelB.setText("yes");

        answer_labelC.setBounds(125, 375, 600, 100);
        answer_labelC.setBackground(new Color(99, 111, 237));
        answer_labelC.setForeground(new Color(151, 255, 255));
        answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 10));
        answer_labelC.setText("yes");

        answer_labelD.setBounds(125, 475, 600, 100);
        answer_labelD.setBackground(new Color(99, 111, 237));
        answer_labelD.setForeground(new Color(151, 255, 255));
        answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 10));
        answer_labelD.setText("yes");

        seconds_left.setBounds(535, 510, 100, 100);
        seconds_left.setBackground(new Color(99, 111, 237));
        seconds_left.setForeground(new Color(25, 25, 25));
        seconds_left.setFont(new Font("Impact", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(2));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535, 475, 100, 25);
        time_label.setBackground(new Color(99, 111, 237));
        time_label.setForeground(new Color(25, 25, 25));
        time_label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Timer! 😈");

        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(99, 111, 237));
        number_right.setForeground(new Color(25, 25, 25));
        number_right.setFont(new Font("Impact", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(99, 111, 237));
        percentage.setForeground(new Color(25, 25, 25));
        percentage.setFont(new Font("Impact", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);

        questionOrder = new int[total_questions];
        for (int i = 0; i < total_questions; i++) {
            questionOrder[i] = i;
        }
        statistics = new Statistics();



        nextQuestion();
    }



    private void loadQuestionsFromDatabase() {
        questions = new String[18];
        options = new String[18][4];
        answers = new char[18];
        try {
            String query = "SELECT question_text, option_a, option_b, option_c, option_d, correct_option FROM MultipleChoiceQandA";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            int questionCount = 0;
            while (resultSet.next() && questionCount <= 18) {
                questions[questionCount] = resultSet.getString("question_text");
                options[questionCount][0] = resultSet.getString("option_a");
                options[questionCount][1] = resultSet.getString("option_b");
                options[questionCount][2] = resultSet.getString("option_c");
                options[questionCount][3] = resultSet.getString("option_d");
                answers[questionCount] = resultSet.getString("correct_option").charAt(1);

                questionCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void nextQuestion() {
        if (currentQuestionIndex >= total_questions) {
            results();
        } else {
            int currentQuestionID = questionOrder[currentQuestionIndex];
            textfield.setText("Question " + (currentQuestionIndex + 1));
            textarea.setText(questions[currentQuestionID]);

            // Set the answer labels
            answer_labelA.setText(options[currentQuestionID][0]);
            answer_labelB.setText(options[currentQuestionID][1]);
            answer_labelC.setText(options[currentQuestionID][2]);
            answer_labelD.setText(options[currentQuestionID][3]);

            currentQuestionIndex++;
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (index < answers.length) {

            JButton selectedButton = (JButton) e.getSource();
            char buttonText = selectedButton.getText().charAt(0);

            // System.out.println(buttonText + " and correct ans: " + Character.toUpperCase((answers[index]))); Debugging check

            if (buttonText == Character.toUpperCase((answers[index]))) {
                correct_answers++;
                //  System.out.println("incremented");    Debugging
            }


            if (currentQuestionIndex > total_questions) {
                results();
            } else {
                displayAnswer();
            }
        }
    }





    public void displayAnswer() {

        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'a')
            answer_labelA.setForeground(new Color(255, 0, 0));
        if (answers[index] != 'b')
            answer_labelB.setForeground(new Color(255, 0, 0));

        if (answers[index] != 'c')
            answer_labelC.setForeground(new Color(255, 0, 0));

        if (answers[index] != 'd')
            answer_labelD.setForeground(new Color(255, 0, 0));
        //The answer label is set to red if the answer is incorrect

        Timer pause = new Timer(1000, new ActionListener() {    //Sets 1 second timer
            @Override
            public void actionPerformed(ActionEvent e) { //After 1 seconds elapses colours change back to original
                answer_labelA.setForeground(new Color(151, 255, 255));
                answer_labelB.setForeground(new Color(151, 255, 255));
                answer_labelC.setForeground(new Color(151, 255, 255));
                answer_labelD.setForeground(new Color(151, 255, 255));

                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();

    }



    private void results() {
        result = (int) ((correct_answers / (double) total_questions) * 100);
        textfield.setText("Result!");
        textarea.setText("");
        answer_labelA.setText(" ");
        answer_labelB.setText(" ");
        answer_labelC.setText(" ");
        answer_labelD.setText(" ");
        number_right.setText("[" + correct_answers + "/" + total_questions + "]");
        percentage.setText(result + "%");
        playAgain.setText("Play Again!");
        frame.add(percentage);
        frame.add(number_right);
        frame.add(playAgain);
        frame.add(backButton);

        int score = (int) ((correct_answers / (double) total_questions) * 100);
        statistics.addQuizScore(score);

        // Display user statistics
        textfield.setText("Result!");
        textarea.setText("Your Score: " + score + "%\n");
        textarea.append("Mean Score: " + statistics.getMeanScore() + "%\n");
        textarea.append("Median Score: " + statistics.getMedianScore() + "%\n");
        textarea.append("Standard Deviation: " + statistics.getStandardDeviation() + "\n");
        frame.add(percentage);
        frame.add(number_right);
    }
    private void resetQuiz() {
        correct_answers = 0;
        currentQuestionIndex = 0;
        index = 0;
        result = 0;
        seconds = 10;

        // Clear the previous quiz statistics
        number_right.setText("");
        percentage.setText("");

        // Clear the "Play Again" button
        frame.remove(playAgain);
        frame.remove(backButton);
        frame.remove(percentage);
        frame.remove(number_right);

        // Start a new quiz
        loadQuestionsFromDatabase();
        nextQuestion();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimedQuiz());
    }
}