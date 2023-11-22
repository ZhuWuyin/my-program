package HW1;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

public class ColorPanel {
    public HashMap<Integer, Color> colors = new HashMap<>();
    public JPanel[] colorRow = new JPanel[3];

    public void initializeColors() {
        colors.put(0, Color.BLACK);
        colors.put(1, Color.GREEN);
        colors.put(2, Color.YELLOW);
        colors.put(3, Color.RED);
    }

    public void initializeBlocks() {
        for (int i = 0; i < 3; i++) {
            colorRow[i] = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            GUI.panel.add(colorRow[i]);
        }
    }

    public JPanel createColorBlock(int row, int num) {
        JPanel colorBlock = new JPanel();
        colorBlock.setPreferredSize(new Dimension(30, 30));
        colorBlock.setBackground(colors.get((row + num) * num));
        return colorBlock;
    }

    public void addColorBlock(int[][] board) {
        for (int i = 0; i < 3; i++) {
            JPanel row = colorRow[i];
            row.removeAll();
            for (int j = 0; j < 7; j++) {
                row.add(createColorBlock(i, board[i][j]));
            }

            row.repaint();
            row.revalidate();
        }
    }

    public ColorPanel() {
        initializeColors();
        initializeBlocks();
    }
}
