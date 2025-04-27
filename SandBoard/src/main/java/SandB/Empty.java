package SandB;

public class Empty extends Particle {
    public Empty() {
        super(0, 0, new Color(0, 0, 0), "Empty",0);
    }

    @Override
    public void update(Board board, int x, int y) {
        return;
    }
    @Override
    public String toString() {
        return "E";
    }

    @Override
    public Particle cloneWithRandomColor() {
        return new Empty(); // Empty particles are always black
    }
}
