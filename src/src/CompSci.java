package src;

import javax.swing.*;


public class CompSci extends Quiz {
    public CompSci(){
        super();
        initialiseCommonComponents();
        initializeQuiz();
    }
    @Override
    protected void initializeQuiz() {
        questions = new String[]{
                "In Backus-Naur Form (BNF), what does the '|' symbol represent?",
                "What is the purpose of Backus-Naur Form (BNF) in computer science?",
                "Which of the following statements is true regarding semantic and syntactic equivalence in programming languages?",
                "In computer science, when are two mathematical equations considered semantically equivalent?",
                "In lattice theory, what is the infimum (infima) and supremum (suprema) of two elements a and b in a lattice?",
                "In Binary Decision Diagrams (BDDs), what is the primary purpose of variable reordering?"
        };
        options = new String[][]{
                {"Alternation", "Concatenation", "Repetition", "Addition"},
                {"To describe the meaning of programming language keywords", "To specify the layout and design of a user interface", "To optimize the execution speed of computer programs", "To formally define the syntax of programming languages"},
                {"Semantic equivalence ensures that two programs have the same runtime behaviour", "Syntactic equivalence is concerned with the arrangement of code elements, while semantic equivalence is about variable naming", "Semantic equivalence can be determined by comparing the program's source code directly", "Syntactic equivalence is concerned with the execution speed of the program, while semantic equivalence is about code comments"},
                {"When they have the same variables but different constants", "When they produce the same result for all possible input values", "When they have the same structure but different variable names", "When they have the same variables but different operators"},
                {"The infimum is the greatest lower bound, and the supremum is the least upper bound of a and b", "The infimum is the least lower bound, and the supremum is the greatest upper bound of a and b", "The infimum is the greatest lower bound of a and b, and the supremum is the greatest upper bound of a and b", "The infimum is the least lower bound of a and b, and the supremum is the least upper bound of a and b"},
                {"To increase the depth of the BDD for better visualization", "To eliminate redundancy in the representation of Boolean functions", "To minimize the number of nodes in the BDD", "To reorder the variables alphabetically for clarity"}
        };
        answers = new char[]{
                'A',
                'D',
                'A',
                'B',
                'A',
                'C'
        };
        super.initializeQuiz();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(CompSci::new);
    }
}