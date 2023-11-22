package HW1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {
    public static JFrame frame = new JFrame("Nim Game");
    public static JTextArea textArea = new JTextArea(20, 40);
    public static JScrollPane scrollPane = new JScrollPane(textArea);
    public static boolean win = false;
    public static boolean start = false;
    public static JPanel panel = new JPanel();
    public static ColorPanel colorPanel = new ColorPanel();

    public static void resetTextArea(JPanel panel) {
        textArea.setText("");
        textArea.setText("Welcome To Nim Game\n\n");
        textArea.append("Type 0 for robot first, 1 for player first\n\n");
        panel.add(scrollPane);
    }

    public static void inputHandler(String text) {
        if (!start) {
            int firstChar = text.length() != 1 ? '3' : text.charAt(0);
            if (firstChar != '0' && firstChar != '1') {
                textArea.append("Please input 1 or 0\n\n");
            } else {
                NimGame.playerID = text.charAt(0) - '0';
                initializeGame();
                start = true;
            }
        } else if (win && text.equals("reset")) {
            resetTextArea(panel);
            win = false;
            start = false;
        } else if (!win) {
            try {
                NimGame.game(text, textArea, colorPanel);
            } catch (Winner winner) {
                win = true;
                textArea.append(winner.getMessage() + "\nType 'reset' to reset the game");
            }
        }
    }

    public static void playerText(JPanel panel) {
        JTextField playerText = new JTextField(20);
        playerText.setBounds(200, 40, 150, 25);
        panel.add(playerText);
        playerText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = playerText.getText();
                inputHandler(text);
            }
        });
    }

    public static void playerLable(JPanel panel) {
        JLabel playerLabel = new JLabel("Player:");
        playerLabel.setBounds(130, 40, 80, 25);
        panel.add(playerLabel);
    }

    private static void placeComponents(JPanel panel) {
        playerLable(panel);
        playerText(panel);
        resetTextArea(panel);
    }

    public static void initializeGame() {
        NimGame.resetGame();
        NimGame.printBoard(textArea, colorPanel);
        if (NimGame.playerID != 1) {
            textArea.append("Type enter to proceed to the next step\n");
        } else {
            textArea.append("\n------\nPlayer round(row, col, #'s you want to press')\n");
        }
        FindAll.findAll(NimGame.convertBoard(), null);
    }

    public static void initializeWindow() {
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 400, 10));
        frame.add(GUI.panel);
        placeComponents(GUI.panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        initializeWindow();
    }
}