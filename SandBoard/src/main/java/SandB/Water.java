package SandB;

public class Water extends Particle {
    public Water(int fallSpeed, int wateriness, Color color, String name, int colorRandomness) {
        super(fallSpeed,wateriness,color,name,colorRandomness);
    }

    @Override
    public void update(Board board, int x, int y) {
        if(basicUpdate(board, x, y)){
            return;
        }
    }

    @Override
    public Particle cloneWithRandomColor() {
        return new Water(fallSpeed, sandiness, generateRandomColor(), name,colorRandomness);
    }
}
