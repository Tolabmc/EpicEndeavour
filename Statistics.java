import java.util.ArrayList;
import java.util.Collections;

public class Statistics {
    private ArrayList<Integer> quizScores;

    public Statistics() {
        quizScores = new ArrayList<>();
    }

    public void addQuizScore(int score) {
        quizScores.add(score);
    }

    public int getMeanScore() {
        int sum = 0;
        for (int score : quizScores) {
            sum += score;
        }
        return quizScores.isEmpty() ? 0 : sum / quizScores.size();
    }

    public int getMedianScore() {
        Collections.sort(quizScores);
        int size = quizScores.size();
        if (size % 2 == 0) {
            int mid1 = quizScores.get(size / 2 - 1);
            int mid2 = quizScores.get(size / 2);
            return (mid1 + mid2) / 2;
        } else {
            return quizScores.get(size / 2);
        }
    }

    public double getStandardDeviation() {
        double mean = getMeanScore();
        double sumOfSquares = 0;
        for (int score : quizScores) {
            sumOfSquares += Math.pow(score - mean, 2);
        }
        double variance = sumOfSquares / quizScores.size();
        return Math.sqrt(variance);
    }

    public int getNumberOfQuizzesTaken() {
        return quizScores.size();
    }
}




