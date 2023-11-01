package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompSci implements ActionListener {

    String[] questions ={
            "In Backus-Naur Form (BNF), what does the '|' symbol represent?",
            "What is the purpose of Backus-Naur Form (BNF) in computer science?",
            "Which of the following statements is true regarding semantic and syntactic equivalence in programming languages?",
            "In computer science, when are two mathematical equations considered semantically equivalent?",
            "In lattice theory, what is the infimum (infima) and supremum (suprema) of two elements a and b in a lattice?",
            "In Binary Decision Diagrams (BDDs), what is the primary purpose of variable reordering?"
            };
    String[][] options ={
            {"Alternation", "Concatenation", "Repetition", "Addition"},
            {"To describe the meaning of programming language keywords", "To specify the layout and design of a user interface", "To optimize the execution speed of computer programs", "To formally define the syntax of programming languages"},
            {"Semantic equivalence ensures that two programs have the same runtime behaviour", "Syntactic equivalence is concerned with the arrangement of code elements, while semantic equivalence is about variable naming", "Semantic equivalence can be determined by comparing the program's source code directly", "Syntactic equivalence is concerned with the execution speed of the program, while semantic equivalence is about code comments"},
            {"When they have the same variables but different constants", "When they produce the same result for all possible input values", "When they have the same structure but different variable names", "When they have the same variables but different operators"},
            {"The infimum is the greatest lower bound, and the supremum is the least upper bound of a and b", "The infimum is the least lower bound, and the supremum is the greatest upper bound of a and b", "The infimum is the greatest lower bound of a and b, and the supremum is the greatest upper bound of a and b", "The infimum is the least lower bound of a and b, and the supremum is the least upper bound of a and b"},
            {"To increase the depth of the BDD for better visualization", "To eliminate redundancy in the representation of Boolean functions", "To minimize the number of nodes in the BDD", "To reorder the variables alphabetically for clarity"}
    };
    char[] answers ={
            'A',
            'D',
            'A',
            'B',
            'A',
            'C'
    };
    private char guess;
    private char answer;
    private int index;
    int correct_answers = 0;
    private int total_questions;
    private int result;

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

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private int[] questionOrder;  // Store the order of questions
    private int currentQuestionIndex;  // Keep track of the current question index

    private Statistics statistics;

    public CompSci() {
        frame = new JFrame("Discrete Maths Quiz");
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

        // Load questions and answers from the database

        total_questions = questions.length;

        // Initialize GUI components
        textfield.setBounds(0, 0, 650, 50);
        textfield.setBackground(new Color(217, 25, 25));
        textfield.setForeground(new Color(151, 255, 255));
        textfield.setFont(new Font("Impact", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        textfield.setText("src.Welcome");

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
                new Welcome(); // Open the src.Welcome page
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

    private void printStackTrace() {
    }


    private void nextQuestion() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
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

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        if (e.getSource()==buttonA){
            answer='A';
            if (answer==answers[index]){
                correct_answers++;
            }
        }
        if (e.getSource()==buttonB){
            answer='B';
            if (answer==answers[index]){
                correct_answers++;
            }
        }
        if (e.getSource()==buttonC){
            answer='C';
            if (answer==answers[index]){
                correct_answers++;
            }
        }
        if (e.getSource()==buttonD){
            answer='D';
            if (answer==answers[index]){
                correct_answers++;
            }
        }nextQuestion();
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

        // Clear the previous quiz statistics
        number_right.setText("");
        percentage.setText("");

        // Clear the "Play Again" button
        frame.remove(playAgain);
        frame.remove(backButton);
        frame.remove(percentage);
        frame.remove(number_right);

        // Start a new quiz
        nextQuestion();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(DiscreteMaths::new);
    }
}