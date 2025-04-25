package SandGui;

import SandB.Board;
import SandB.Color;
import SandB.Sand;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class SandCanvas extends Canvas {
    private final Board board;
    private final int cellSize;
    private boolean isMousePressed = false;
    private int x_mouse;
    private int y_mouse;
    public SandCanvas(int width, int height, int cellSize) {
        super(width * cellSize, height * cellSize);
        this.cellSize = cellSize;
        this.board = new Board(height, width);
        
        setupMouseHandlers();
    }

    private void setupMouseHandlers() {
        setOnMousePressed(this::handleMouse);
        setOnMouseDragged(this::handleMouse);
        setOnMouseReleased(e -> isMousePressed = false);
    }

    private void handleMouse(MouseEvent e) {
        isMousePressed = true;
        x_mouse = (int) (e.getX() / cellSize);
        y_mouse = (int) (e.getY() / cellSize);
    }

    public void update() {
        board.updateBoard();
        draw();
        if (isMousePressed) {
            addParticle(x_mouse, y_mouse);
        }
    }
    public void addParticle(int x, int y) {
        if (board.isValid(x, y)) {
            board.set(x, y, new Sand(100, 60, new Color(255, 206, 71)));
        }
    }
    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = board.getHeight(); y >= 0; y--) {
                Color particleColor = board.get(x, y).getColor();
                gc.setFill(Paint.valueOf(String.format("#%02X%02X%02X",
                    particleColor.getRed(),
                    particleColor.getGreen(),
                    particleColor.getBlue())));
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }

}