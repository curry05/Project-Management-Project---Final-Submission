package projectmanagementics4u;

import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ProjectManagementICS4U {

    public static void main(String[] args) {
        // ---------- READ NOTES ----------
        String notes = ""; // use a plain string
        try {
            InputStream in = ProjectManagementICS4U.class.getResourceAsStream("Notes.txt");
            if (in == null) {
                JOptionPane.showMessageDialog(null, "Notes file not found inside JAR!");
                return;
            }

            Scanner n = new Scanner(in);
            while (n.hasNextLine()) {
                String line = n.nextLine();
                notes += line + "\n"; // append line manually
            }
            n.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading notes: " + e.getMessage());
        }

        // ---------- READ QUIZ ----------
        String[] qs = new String[10];
        String[] optA = new String[10];
        String[] optB = new String[10];
        String[] optC = new String[10];
        String[] optD = new String[10];
        String[] rightAns = new String[10];

        try {
            InputStream in = ProjectManagementICS4U.class.getResourceAsStream("Quiz.txt");
            if (in == null) {
                JOptionPane.showMessageDialog(null, "Quiz file not found inside JAR!");
                return;
            }

            Scanner s = new Scanner(in);
            for (int i = 0; i < 10; i++) {
                qs[i] = s.nextLine();
                optA[i] = s.nextLine();
                optB[i] = s.nextLine();
                optC[i] = s.nextLine();
                optD[i] = s.nextLine();
                rightAns[i] = s.nextLine().trim();
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading quiz: " + e.getMessage());
        }

        // ---------- MAIN MENU ----------
        boolean running = true;

        while (running) {
            String choice = JOptionPane.showInputDialog(
                    "SDLC Study Helper\n\n"
                    + "1. View Study Notes\n"
                    + "2. Take Quiz\n"
                    + "3. Exit\n\n"
                    + "Enter choice:"
            );

            if (choice == null) {
                JOptionPane.showMessageDialog(null, "Bye");
                return;
            }
            choice = choice.trim();

            if (choice.equals("1")) {
                JOptionPane.showMessageDialog(null, notes);
            } else if (choice.equals("2")) {
                runQuiz(qs, optA, optB, optC, optD, rightAns);
            } else if (choice.equals("3")) {
                running = false;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }

    public static void runQuiz(String[] qs, String[] A, String[] B, String[] C, String[] D, String[] ans) {

        int score = 0;

        for (int i = 0; i < 10; i++) {
            String user = JOptionPane.showInputDialog(
                    "Question " + (i + 1) + ":\n\n"
                    + qs[i] + "\n\n"
                    + "A) " + A[i] + "\n"
                    + "B) " + B[i] + "\n"
                    + "C) " + C[i] + "\n"
                    + "D) " + D[i] + "\n\n"
                    + "Enter A/B/C/D:"
            );

            if (user == null) return; // cancel quiz

            user = user.trim().toUpperCase();

            if (user.equals(ans[i])) {
                score++;
                JOptionPane.showMessageDialog(null, "Correct!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Incorrect.\nCorrect answer: " + ans[i]);
            }
        }

        JOptionPane.showMessageDialog(null,
                "Quiz Complete!\nScore: " + score + "/10\n"
                + "Percentage: " + (score * 10) + "%");
    }
}
