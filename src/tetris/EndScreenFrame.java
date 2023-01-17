package tetris;

import javax.swing.*;

public class EndScreenFrame extends JFrame {
    public EndScreenFrame(int score) {
        generateFrame(score);
    }

    public void generateFrame(int score) {
        setTitle("Tetris");
        setSize(650, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel scoreLabel = new JLabel(String.format("Score: %d", score));
        JLabel gameOverLabel = new JLabel("Game Over!");
        JPanel gameOverPanel = new JPanel();
        JButton restartButton = new JButton("Press to Restart");
        restartButton.addActionListener(e -> {
            TetrisFrame tetris = new TetrisFrame();
            tetris.setVisible(true);
        });

        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(scoreLabel);
        gameOverPanel.add(restartButton);
        add(gameOverPanel);
    }
}
