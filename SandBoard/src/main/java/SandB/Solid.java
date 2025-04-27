package SandB;

public class Solid extends Particle {
    public Solid(Color color, String name, int colorRandomness) {
        super(0,0,color,name,colorRandomness);
    }
    @Override
    public void update(Board board, int x, int y) {
        return;
    }
    @Override
    public String toString() {
        return "S";
    }
    @Override
    public Particle cloneWithRandomColor() {
        return new Solid(generateRandomColor(), name,colorRandomness);
    }
}
