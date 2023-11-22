package HW1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class FindAll {
    public static HashMap<Integer, Node> all = new HashMap<>(); // All possibilities

    /**
     * Minus the diff from the board in given index
     * i.e. board={1,1,1}, index=1, diff=1, then this function will return {1,0,1}
     * 
     * @param board
     * @param index
     * @param diff
     * @return
     */
    public static int[] reduceBoard(int[] board, int index, int diff) {
        int[] temp = new int[3];
        for (int k = 0; k < 3; k++) {
            if (k == index) {
                temp[k] = board[k] - diff;
            } else {
                temp[k] = board[k];
            }
        }

        return temp;
    }

    /**
     * Find all possible combinations of the array board
     * 
     * @param board
     * @param prev
     */
    public static void findAll(int[] board, Node prev) {
        if (prev == null) {
            prev = new Node(null, null);
        }
        Integer boardHash = Arrays.hashCode(board);
        all.put(boardHash, prev);

        for (int i = 0; i < 3; i++) {
            for (int j = 1; j <= board[i]; j++) {
                int[] currArr = reduceBoard(board, i, j);
                int currHash = Arrays.hashCode(currArr);

                // If current array is not in the map,
                // then find it's all possible combinations
                if (all.get(currHash) == null) {
                    Node curr = new Node(prev, currArr);
                    prev.putNext(curr);
                    findAll(currArr, curr);
                } else {
                    prev.putNext(all.get(currHash));
                }
            }
        }
    }

    /**
     * Find the shortest way to win
     * 
     * @param board
     * @param pathCount
     * @param length
     */
    public static void findShortWin(int[] board, int pathCount, int[] length) {
        // If the boare is all zeros inside, then we reached the final round
        if (board[0] == 0 && board[1] == 0 && board[2] == 0 && pathCount < length[0] && pathCount % 2 == 0) {
            length[0] = pathCount;
            return;
        }

        int boardHash = Arrays.hashCode(board);
        ArrayList<int[]> nexts = all.get(boardHash).nextRecord;
        for (int[] i : nexts) {
            pathCount++;
            findShortWin(i, pathCount, length);
            pathCount--;
        }
    }
}