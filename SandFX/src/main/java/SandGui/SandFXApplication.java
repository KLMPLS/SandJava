package SandGui;

import SandB.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class SandFXApplication extends Application {
    private static final int BOARD_WIDTH = 200;
    private static final int BOARD_HEIGHT = 200;
    private static final int CELL_SIZE = 5;
    private static final long FRAME_INTERVAL = 13_000_000; // 13 milliseconds in nanoseconds
    private ParticleList particles;
    private int sliderValue = 1;
    @Override
    public void start(Stage stage) {
        setupParticles();
        SandCanvas canvas = new SandCanvas(BOARD_WIDTH, BOARD_HEIGHT, CELL_SIZE);

        ComboBox<String> particleSelector = new ComboBox<>();
        particleSelector.getItems().addAll(particles.getNames());
        particleSelector.getSelectionModel().selectFirst();
        Slider slider = new Slider(0, 100, 50);
        slider.setMin(1);
        slider.setMax(20);
        slider.setBlockIncrement(1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setValue(sliderValue);
        Text sliderValueText = new Text(String.valueOf(sliderValue));
        slider.valueProperty().addListener((obs, old, newValue) -> {
            sliderValue = newValue.intValue();
            sliderValueText.setText(String.valueOf(sliderValue));
            slider.setValue(sliderValue);
            canvas.setAddingSize(sliderValue);
        });
        String initialParticleName = particleSelector.getValue();
        canvas.setSelectedParticle(particles.getParticle(initialParticleName));

        particleSelector.setOnAction(e -> {
            String selectedName = particleSelector.getValue();
            canvas.setSelectedParticle(particles.getParticle(selectedName));
        });

        HBox controls = new HBox(10); // 10 pixels spacing
        controls.getChildren().addAll(particleSelector, slider, sliderValueText);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setTop(controls);

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
    private void setupParticles(){
        Sand sand = new Sand(100, 20, new Color(255, 206, 71), "Sand",4);
        Water water = new Water(100, 20, new Color(10, 10, 200), "Water",3);
        Sand confetti = new Sand(20,30,new Color(255,255,255),"Confetti",2000);
        Solid stone = new Solid(new Color(100,100,100),"Stone",2);
        particles = new ParticleList(Arrays.asList(sand,water,confetti,stone));
    }
    public static void main(String[] args) {
        launch();
    }
}
