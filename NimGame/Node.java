package HW1;

import java.util.HashMap;
import java.util.ArrayList;

public class Node {
    public Node prev;
    public int[] curr;
    public HashMap<int[], Node> next;
    public ArrayList<int[]> nextRecord;
    public boolean printed = false;

    public Node(Node prev, int[] curr) {
        this.prev = prev;
        this.curr = curr;
        this.next = new HashMap<int[], Node>();
        this.nextRecord = new ArrayList<int[]>();
    }

    public void putNext(Node n) {
        int[] curr = n.curr;
        this.nextRecord.add(curr);
        this.next.put(curr, n);
    }

    public Node getNode(int[] n) {
        return this.next.get(n);
    }
}