package SandB;

import java.util.Random;

public abstract class Particle {
    String name;
    int fallSpeed;
    int sandiness;
    Color color;
    protected int colorRandomness;
    Particle(int fallSpeed, int sandiness, Color color, String name, int colorRandomness) {
        this.fallSpeed = fallSpeed;
        this.sandiness = sandiness;
        this.color = color;
        this.name = name;
        this.colorRandomness = colorRandomness;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {}
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
    protected Boolean basicUpdate(Board board, int x, int y) {
        Random rand = new Random();
        if(board.isValid(x,y+1) && board.isEmpty(x, y+1)){
            if(rand.nextInt(100) < fallSpeed) {
                board.swap(x, y, x, y + 1);
            }
            return true;
        }
        return false;
    }

    public abstract Particle cloneWithRandomColor();

    protected Color generateRandomColor() {
        if (colorRandomness == 0) {
            return color;
        }
        return color.randomColor(colorRandomness);
    }
}
