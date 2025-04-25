module SandFX {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires SandBoard;

    exports SandGui to javafx.graphics;

}