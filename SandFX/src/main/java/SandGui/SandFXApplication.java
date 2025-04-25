package SandGui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SandFXApplication extends Application {
    private static final int BOARD_WIDTH = 50;
    private static final int BOARD_HEIGHT = 50;
    private static final int CELL_SIZE = 10;
    private static final long FRAME_INTERVAL = 13_000_000; // 13 milliseconds in nanoseconds

    @Override
    public void start(Stage stage) {
        SandCanvas canvas = new SandCanvas(BOARD_WIDTH, BOARD_HEIGHT, CELL_SIZE);
        StackPane root = new StackPane(canvas);
        
        Scene scene = new Scene(root);
        stage.setTitle("Sand Simulation");
        stage.setScene(scene);
        stage.setResizable(false);
        
        final long[] lastUpdate = {0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((now - lastUpdate[0]) >= FRAME_INTERVAL) {
                    canvas.update();
                    lastUpdate[0] = now;
                }
            }
        };
        timer.start();
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}