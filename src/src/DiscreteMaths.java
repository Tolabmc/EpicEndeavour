package src;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class DiscreteMaths extends Quiz {
    public DiscreteMaths() {
        super();
        initialiseCommonComponents();  // Call this first
        initializeQuiz();
    }
        @Override
        protected void initializeQuiz() {

            questions = new String[]{
                    "Consider the sets: A = {1,2,3,4,5} B = {3,4, 5,6,7}. Which of the following represents the intersection of A and B?",
                    "Given the function f(x) = 2x + 3, what is the value of f(4)?",
                    "Given the propositions: P: The sun is shining. Q: It's a warm day.R: The ice cream truck is here. Which of the following statements is equivalent to \"If the sun is shining, then it's a warm day?\"",
                    "Consider the propositions: A: The car is red.B: The car is expensive. C: The car is fast. Which of the following statements is equivalent to \"The car is either red and expensive or fast\"?",
                    "What is the result of multiplying a 3x2 matrix by a 2x4 matrix?",
                    "Given the propositions: P: The cake is chocolate.Q: The cake is delicious. R: The cake is moist. S: The cake is homemade. Which of the following expressions represents \"The cake is not chocolate, and it is delicious and moist, but not homemade\" in propositional logic?",
            };
            options = new String[][]{
                    {"{1, 2}", "{3, 4, 5}", "{6, 7}", "{1, 5, 7}"},
                    {"8", "11", "19", "5"},
                    {"P ∧ Q", "P → Q", "Q → P", "L"},
                    {"A ∨ B", "(A ∧ B) ∨ C", "A ↔ (B ∨ C)", "m"},
                    {"It is not possible to multiply these matrices", "A 3x4 matrix", "A 2x2 matrix", "A 2x3 matrix"},
                    {"¬P ∧ (Q ∧ R) ∧ ¬S", "¬P ∧ (Q ∨ R) ∧ ¬S", "P ∨ (¬Q ∧ ¬R) ∧S", "¬P ∧ (Q ∧ R) ∧ S"}
            };
            answers = new char[]{
                    'B',
                    'B',
                    'B',
                    'B',
                    'A',
                    'A'
            };
            super.initializeQuiz();
        }
   public static void main (String[]args){
            SwingUtilities.invokeLater(DiscreteMaths::new);

        }
    }
