/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectmanagementics4u;  
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
public class ProjectManagementICS4U {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // ---------- READ NOTES ----------
        StringBuilder notes = new StringBuilder();
        try {
            Scanner n = new Scanner(new File("src/projectmanagementics4u/Notes.txt"));
            while (n.hasNextLine()) {
                notes.append(n.nextLine()).append("\n");
            }
            n.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "notes.txt not found!");
        }

        // ---------- READ QUIZ ----------
        String[] qs = new String[10];
        String[] optA = new String[10];
        String[] optB = new String[10];
        String[] optC = new String[10];
        String[] optD = new String[10];
        String[] rightAns = new String[10];

        try {
            Scanner s = new Scanner(new File("src/projectmanagementics4u/Quiz.txt"));
            for (int i = 0; i < 10; i++) {
                qs[i] = s.nextLine();
                optA[i] = s.nextLine();
                optB[i] = s.nextLine();
                optC[i] = s.nextLine();
                optD[i] = s.nextLine();
                rightAns[i] = s.nextLine().trim(); // A/B/C/D
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading quiz.txt");
        }

        // ---------- MAIN MENU ----------
        boolean running = true;

        while (running) {
            String choice = JOptionPane.showInputDialog(
                "SDLC Study Helper\n\n" +
                "1. View Study Notes\n" +
                "2. Take Quiz\n" +
                "3. Exit\n\n" +
                "Enter choice:"
            );

            if (choice == null) return; // Cancel exit

            switch (choice) {
                case "1":
                    JOptionPane.showMessageDialog(null, notes.toString());
                    break;

                case "2":
                    runQuiz(qs, optA, optB, optC, optD, rightAns);
                    break;

                case "3":
                    running = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }

    // ---------- QUIZ METHOD ----------
    public static void runQuiz(String[] qs, String[] A, String[] B, String[] C, String[] D, String[] ans) {

        int score = 0;

        for (int i = 0; i < 10; i++) {

            String user = JOptionPane.showInputDialog(
                "Question " + (i + 1) + ":\n\n" +
                qs[i] + "\n\n" +
                "A) " + A[i] + "\n" +
                "B) " + B[i] + "\n" +
                "C) " + C[i] + "\n" +
                "D) " + D[i] + "\n\n" +
                "Enter A/B/C/D:"
            );

            if (user == null) return; // cancel = quit quiz

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
            "Quiz Complete!\nScore: " + score + "/10\n" +
            "Percentage: " + (score * 10) + "%");
    
    }
    
}
