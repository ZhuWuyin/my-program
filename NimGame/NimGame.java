package HW1;

import javax.swing.JTextArea;

public class NimGame {

    public static int[][] board = { { 1, 1, 1, 0, 0, 0, 0 },
            { 1, 1, 0, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 0 } };

    public static int playerID = 0;

    /**
     * Count how many one in the given row
     * 
     * @param row Which row you want to check(from 0 to 2)
     * @return
     */
    public static int countOnes(int row) {
        int result = 0;
        for (int i = 0; i < 7; i++) {
            result += board[row][i];
        }
        return result;
    }

    /**
     * Convert board to {a,b,c} format
     * 
     * @return
     */
    public static int[] convertBoard() {
        int[] board = new int[3];
        for (int i = 0; i < 3; i++) {
            board[i] = countOnes(i);
        }
        return board;
    }

    /**
     * To do the press for bot
     * 
     * @param solution Solution from bot
     * @return
     */
    public static boolean press(int[] solution) {
        for (int i = 0; i < 3; i++) {
            int[] row = board[i];
            for (int j = 0; j < 7; j++) {
                if (row[j] == 1 && solution[i] != 0) {
                    row[j] = 0;
                    solution[i] -= 1;
                } else if (solution[i] == 0) {
                    break;
                }
            }
        }

        for (int i : solution) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    public static void isLegalMove(int row, int col, int num) throws InputError {
        if (num == 0) {
            throw new InputError("You have to press a button");
        }
        try {
            for (int i = 0; i < num; i++) {
                int state = board[row][col + i];
                if (state != 1) {
                    throw new InputError("You are doing a illegal movement");
                }
            }
        } catch (Exception e) {
            throw new InputError("You are doing a illegal movement");
        }
    }

    /**
     * To do the press for human
     * 
     * @param row Which row you want to press(start from 0 to 2)
     * @param col Which col you want to press(start from 0)
     * @param num How many button you want to press
     * @return
     * @throws InputError
     */
    public static void press(int row, int col, int num) throws InputError {
        isLegalMove(row, col, num);
        for (int i = 0; i < num; i++) {
            board[row][col + i] = 0;
        }
    }

    public static void printRow(int index, JTextArea textArea) {
        textArea.append("{");
        for (int i = 0; i < 7; i++) {
            if (i != 6) {
                textArea.append(board[index][i] + ",");
            } else {
                textArea.append(board[index][i] + "");
            }
        }
        textArea.append("}");
    }

    public static void printBoard(JTextArea textArea, ColorPanel colorPanel) {
        for (int i = 0; i < 3; i++) {
            printRow(i, textArea);
            textArea.append("\n");
        }
        colorPanel.addColorBlock(board);
    }

    // Helper function for resetGame
    public static void resetRow(int index, int num) {
        for (int i = 0; i < num; i++) {
            board[index][i] = 1;
        }
    }

    public static void resetGame() {
        resetRow(0, 3);
        resetRow(1, 7);
        resetRow(2, 5);
    }

    /**
     * Determine if the game is over
     * 
     * @return
     */
    public static boolean gameEnd() {
        for (int[] i : board) {
            for (int j : i) {
                if (j != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * To handle the input from player
     * 
     * @param playerIn
     * @return
     * @throws InputError
     */
    public static void player(String playerIn, JTextArea textArea) throws InputError {
        int[] playerPosition;
        try {
            playerPosition = Input.getDigits(playerIn);
            press(playerPosition[0], playerPosition[1], playerPosition[2]);
        } catch (InputError e) {
            throw e;
        }
    }

    /**
     * To handle the input from bot
     */
    public static void bot(JTextArea textArea) {
        textArea.append("\n------\nBot round\n");
        int[] botMove = Bot.bot(convertBoard());
        press(botMove);
    }

    public static void printWinner(JTextArea textArea, int player) throws Winner {
        boolean end = gameEnd();

        // 0 is human, 1 is bot
        if (player == 0 && end) {
            throw new Winner("\nPlayer Win");
        } else if (end) {
            throw new Winner("\nBot Win");
        }
    }

    public static void game(String playerIn, JTextArea textArea, ColorPanel colorPanel) throws Winner {

        if (playerID == 1) {
            try {
                player(playerIn, textArea);
                textArea.append("\n");
            } catch (InputError e) {
                textArea.append("------" + e.getMessage() + "\n");
                return;
            }
            playerID = 0;
        } else {
            bot(textArea);
            playerID = 1;
        }

        printBoard(textArea, colorPanel);
        printWinner(textArea, playerID);
        if (playerID != 1) {
            textArea.append("Type enter to proceed to the next step\n");
        }
        if (playerID == 1) {
            textArea.append("\n------\nPlayer round(row, col, #'s you want to press')\n");
        }
    }
}