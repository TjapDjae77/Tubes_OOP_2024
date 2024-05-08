// package source.Map;

import javax.swing.*;

public class GameDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game Map GUI");
            GameMap gm = new GameMap();
            frame.getContentPane().add(gm);
            frame.setSize(100 * gm.getWidth(), 100 * gm.getHeight()); // Atur ukuran jendela sesuai dengan ukuran petak
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
