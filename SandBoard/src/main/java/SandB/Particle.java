package SandB;

public abstract class Particle {
    int fallSpeed;
    int sandiness;
    Color color;
    Particle(int fallSpeed, int sandiness, Color color) {
        this.fallSpeed = fallSpeed;
        this.sandiness = sandiness;
        this.color = color;
    }
    public int getFallSpeed() {
        return fallSpeed;
    }
    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }
    public int getSandiness() {
        return sandiness;
    }
    public void setSandiness(int sandiness) {
        this.sandiness = sandiness;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public abstract void update(Board board, int x, int y);

}
