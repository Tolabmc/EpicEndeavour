package src;

import javax.swing.*;



public class CompOrg extends Quiz {
public CompOrg(){
    super();
    initialiseCommonComponents();
    initializeQuiz();
}
    @Override
    protected void initializeQuiz() {
        questions = new String[]{
                "In a multi-level machine, what is the primary advantage of having multiple levels of memory hierarchy (such as cache, main memory, and disk storage)?",
                "In the von Neumann architecture, what is the primary role of the Control Unit?",
                "Convert the binary number 110110101011 to its hexadecimal equivalent.",
                "In computer memory bus architecture, what is the purpose of the data bus?",
                "What is the octal representation of the hexadecimal number \"1F7\"?",
                "In the context of computer architecture, which of the following components specifies the set of instructions that a computer's central processing unit (CPU) can execute?"
        };
        options = new String[][]{
                {"Increased processing speed", "Lower cost of memory", "Improved memory capacity and access time", "Reduced energy consumption"},
                {"Performing arithmetic operations", "Managing input and output devices", "Managing the execution of instructions and data transfer", "Storing data temporarily"},
                {"3B4", "6B5", "1B5", "DAB"},
                {"To carry memory addresses", "To provide a pathway for the control unit", "C) To transfer data between the CPU and memory", "To control the temperature of the CPU and other components"},
                {"347", "367", "767", "747"},
                {"ALU (Arithmetic Logic Unit)", "ISA (Instruction Set Architecture) ", "FPU (Floating-Point Unit)", "GPU ( Grraphic Processing Unit)"}
        };
        answers = new char[]{
                'C',
                'C',
                'D',
                'C',
                'C',
                'B'
        };
        super.initializeQuiz();
    }


    public static void main (String[]args){
        SwingUtilities.invokeLater(CompOrg::new);

    }
}
