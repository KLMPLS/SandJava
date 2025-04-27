package SandGui;

import SandB.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class SandCanvas extends Canvas {
    private final Board board;
    private final int cellSize;
    private boolean isLeftMousePressed = false;
    private boolean isRightMousePressed = false;
    private int x_mouse;
    private int y_mouse;
    private Particle selectedParticle;
    private int addingSize = 1;
    private Empty emptyParticle = new Empty();
    public void setAddingSize(int addingSize) {
        this.addingSize = addingSize;
    }
    public SandCanvas(int width, int height, int cellSize) {
        super(width * cellSize, height * cellSize);
        this.cellSize = cellSize;
        this.board = new Board(height, width);
        setupMouseHandlers();
    }
    public void setSelectedParticle(Particle selectedParticle) {
        this.selectedParticle = selectedParticle;
    }
    private void setupMouseHandlers() {
        setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                isLeftMousePressed = true;
            }
            if (e.isSecondaryButtonDown()) {
                isRightMousePressed = true;
            }
        });
        setOnMouseDragged(this::handleMouse);
        setOnMouseMoved(this::handleMouse);
        setOnMouseReleased(e -> {
            isLeftMousePressed = false;
            isRightMousePressed = false;
        });
    }

    private void handleMouse(MouseEvent e) {
        x_mouse = (int) (e.getX() / cellSize);
        y_mouse = (int) (e.getY() / cellSize);

    }

    public void update() {
        board.updateBoard();
        if (isLeftMousePressed) {
            addParticle(x_mouse, y_mouse);
        }
        if (isRightMousePressed) {
            addEmptyParticle(x_mouse, y_mouse);
        }
        draw();
    }

    public void addEmptyParticle(int x, int y) {
        board.addParticles(emptyParticle, addingSize, x, y);
    }
    public void addParticle(int x, int y) {
        board.addParticles(selectedParticle, addingSize, x, y);
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
        drawCursor(gc);
    }
    private void drawCursor(GraphicsContext gc){
        Color particleColor = selectedParticle.getColor();
        gc.setFill(Paint.valueOf(String.format("#%02X%02X%02X",
            (int) (particleColor.getRed()/1.2),
            (int) (particleColor.getGreen()/1.2),
            (int) (particleColor.getBlue()/1.2))));

        if (addingSize == 1) {
            gc.fillRect(x_mouse * cellSize, y_mouse * cellSize, cellSize, cellSize);
        } else {
            drawCursorPattern(gc, x_mouse, y_mouse, addingSize);
        }
    }

    private void drawCursorPattern(GraphicsContext gc, int x, int y, int size) {
        if (size == 1) {
            gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            return;
        } else {
            int s = size - 1;
            for (int i = 0; i < s; i++) {
                if (board.isValid(x + s - i, y - i)) {
                    gc.fillRect((x + s - i) * cellSize, (y - i) * cellSize, cellSize, cellSize);
                }
                if (board.isValid(x - i, y - s + i)) {
                    gc.fillRect((x - i) * cellSize, (y - s + i) * cellSize, cellSize, cellSize);
                }
                if (board.isValid(x - s + i, y + i)) {
                    gc.fillRect((x - s + i) * cellSize, (y + i) * cellSize, cellSize, cellSize);
                }
                if (board.isValid(x + i, y + s - i)) {
                    gc.fillRect((x + i) * cellSize, (y + s - i) * cellSize, cellSize, cellSize);
                }
            }
            drawCursorPattern(gc, x, y, s);
        }
    }

}
