package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisFrame extends JFrame {
    private final int LEVEL = 5;
    private final int GAME_SPEED = 5 * (12 - LEVEL);
    private final static int SQUARE_SIZE = 20;
    private char[][] board;
    private char[][] temp;
    private Piece current;
    private Piece next;
    private Timer timer;
    private int rows = 0;
    private static final int BOARD_X_SIZE = Tetris.BOARD_X_SIZE;
    private static final int BOARD_Y_SIZE = Tetris.BOARD_Y_SIZE;
    private static int counter = 0;
    public static final int GAME_TICK_LENGTH = 10;


    public TetrisFrame()  {
        initUI();
    }

    private void initUI() {
        board = Tetris.initBoard();
        current = new Piece();
        next = new Piece();
        temp = Tetris.addToBoard(board, current);
        counter = 0;

        setTitle("Tetris");
        setSize(650, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel startGamePanel = new JPanel();

        JPanel gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                color(temp, g, BOARD_X_SIZE, BOARD_Y_SIZE, 0, 0);
                color(next.getShape(), g, next.getShape().length, next.getShape()[0].length, 265, 40);
            }
        };
        gamePanel.setBackground(Color.white);

        JButton startButton = new JButton("Press to Start New Game");
        startButton.addActionListener(e -> {
            startButton.setVisible(false);
            add(gamePanel);
            timer.start();
        });

        JLabel scoreLabel = new JLabel(String.format("Score: %d", rows));
        JLabel nextPieceLabel = new JLabel("Next piece:");

        gamePanel.add(nextPieceLabel);
        gamePanel.add(scoreLabel);

        startGamePanel.add(startButton);
        add(startGamePanel);

        timer = new Timer(GAME_TICK_LENGTH, e -> {
            temp = Tetris.addToBoard(board, current);
            if (counter % GAME_SPEED == 0 && counter != 0) {
                rows += Tetris.clearRows(board);
                scoreLabel.setText(String.format("Score: %d", rows));
                boolean collides = Tetris.tick(board, current);

                if (collides && current.getY() == 1) {
                    timer.stop();
                    System.out.println(scoreLabel.getText());
                    EndScreenFrame endScreen = new EndScreenFrame(rows);
                    endScreen.setVisible(true);
                    dispose();
                } else if (collides) {
                    board = Tetris.addToBoard(board, current);
                    current = next;
                    next = new Piece();
                    counter = GAME_SPEED - 1;
                }
                if (!(collides && current.getY() == 1)) gamePanel.repaint();
            }
            counter++;
        });


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> Tetris.moveLeft(board, current);
                    case KeyEvent.VK_RIGHT -> Tetris.moveRight(board, current);
                    case KeyEvent.VK_UP -> Tetris.rotate(board, current);
                    case KeyEvent.VK_DOWN -> Tetris.tick(board, current);
                    case KeyEvent.VK_SPACE -> {
                        boolean end = Tetris.tick(board, current);
                        while (!end) {
                            end = Tetris.tick(board, current);
                        }
                    }
                    case KeyEvent.VK_P -> {
                        if (timer.isRunning()) {
                            timer.stop();
                        }
                        else {
                            timer.start();
                        }
                    }
                }
                gamePanel.repaint();
            }
        });

        setFocusable(true);
    }

    private static void color(char[][] temp, Graphics g, int xSize, int ySize, int offSetX, int offSetY) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                switch (temp[i][j]){
                    case 'X' -> g.setColor(Color.BLACK);
                    case '1' -> g.setColor(Color.YELLOW);
                    case '2' -> g.setColor(Color.CYAN);
                    case '3' -> g.setColor(Color.BLUE);
                    case '4' -> g.setColor(Color.ORANGE);
                    case '5' -> g.setColor(Color.GREEN);
                    case '6' -> g.setColor(Color.RED);
                    case '7' -> g.setColor(Color.MAGENTA);
                    default -> g.setColor(Color.WHITE);
                }
                g.fillRect(i * SQUARE_SIZE + offSetX, j * SQUARE_SIZE + offSetY, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public static void main(String[] args) {
        TetrisFrame tetris = new TetrisFrame();
        tetris.setVisible(true);
    }
}

