package HW1;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.time.LocalDateTime;

public class Bot {
    public static HashMap<Integer, Integer> boardToLength = new HashMap<>();

    /**
     * Generate the solution from the given possible board
     * 
     * @param board Current board
     * @param next  Predicted board
     * @return
     */
    public static int[] getDistance(int[] board, int[] next) {
        int[] result = new int[3];
        for (int i = 0; i < board.length; i++) {
            result[i] = board[i] - next[i];
        }
        return result;
    }

    public static int randomMove(int size) {
        return new Random(LocalDateTime.now().getSecond()).nextInt(size);
    }

    public static int countNum(int[] board, int num) {
        int numCount = 0;
        for (int i : board) {
            if (i == num) {
                numCount++;
            }
        }

        return numCount;
    }

    /**
     * Find the shortest way to win based on given number of zeros
     * 
     * @param nexts
     * @param minArr
     * @param zeros
     * @return
     */
    public static int findShortWin(ArrayList<int[]> nexts, int zeros) {
        int min = Integer.MAX_VALUE;
        for (int[] i : nexts) {
            int[] length = { Integer.MAX_VALUE };
            if (countNum(i, 0) == zeros) {
                FindAll.findShortWin(i, 0, length);
            }
            if (length[0] <= min) {
                min = length[0];
                boardToLength.put(Arrays.hashCode(i), min);
            }
        }

        return min;
    }

    public static ArrayList<int[]> getAllValidMove(ArrayList<int[]> nexts, int min) {
        ArrayList<int[]> allValidMove = new ArrayList<>();
        for (int[] i : nexts) {
            int hash = Arrays.hashCode(i);
            if (boardToLength.get(hash) != null && boardToLength.get(hash) == min) {
                allValidMove.add(i);
            }
        }

        return allValidMove;
    }

    public static int[] generateNextBoard(ArrayList<int[]> nexts) {
        ArrayList<int[]> allValidMove = new ArrayList<>();
        for (int[] i : nexts) {
            int countOne = countNum(i, 1);
            int countZero = countNum(i, 0);
            if (countOne == 2 && countZero == 1) {
                return i;
            }
            if (countOne != 2) {
                allValidMove.add(i);
            }
        }

        if (allValidMove.size() != 0) {
            return allValidMove.get(randomMove(allValidMove.size()));
        } else {
            return nexts.get(randomMove(nexts.size()));
        }
    }

    /**
     * Generate the movement of bot
     * 
     * @param board
     * @return Movement from the bot
     */
    public static int[] bot(int[] board) {
        int boardHash = Arrays.hashCode(board);
        ArrayList<int[]> nexts = FindAll.all.get(boardHash).nextRecord;
        int[] minArr = new int[3];
        int min = Integer.MAX_VALUE;

        for (int[] i : nexts) {
            int countOne = countNum(i, 1);
            int countZero = countNum(i, 0);
            if (countOne == 2 && countZero == 1) {
                minArr = i;
                min = -1;
            }
            if (countZero == 3) {
                minArr = i;
                min = -1;
            }
        }

        if (min != -1) {
            for (int zeros = 0; zeros <= 3; zeros++) {
                if (zeros == 2) {
                    zeros++;
                }
                min = findShortWin(nexts, zeros);
                if (min != Integer.MAX_VALUE) {
                    minArr = generateNextBoard(getAllValidMove(nexts, min));
                    break;
                }
            }

            if (min == Integer.MAX_VALUE) {
                minArr = nexts.get(randomMove(nexts.size()));
            }
        }

        return getDistance(board, minArr);
    }
}