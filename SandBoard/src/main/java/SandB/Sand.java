package SandB;
import java.util.Random;
public class Sand extends Particle {
    public Sand(int fallSpeed, int sandiness, Color color, String name, int colorRandomness) {
        super(fallSpeed, sandiness, color, name, colorRandomness);
    }
    @Override
    public void update(Board board, int x, int y) {
        if(basicUpdate(board, x, y)){
            return;
        }
        else{
            Random rand = new Random();
            int choice = 0;
            if(board.isValid(x+1,y+1)&&board.isEmpty(x+1, y+1)) {choice += 1;}
            if(board.isValid(x-1,y+1)&&board.isEmpty(x-1, y+1)) {choice += 2;}
            switch (choice) {
                case 0:
                    return;
                case 1:
                    if(rand.nextInt(100) < sandiness) {
                        board.swap(x, y, x + 1, y + 1);
                    }
                    break;
                case 2:
                    if(rand.nextInt(100) < sandiness) {
                        board.swap(x, y, x - 1, y + 1);
                    }
                    break;
                case 3:
                    board.swap(x, y, x + (rand.nextBoolean()?1:-1), y + 1);
                    break;

            }
        }

    }
    @Override
    public String toString() {
        return "S";
    }

    @Override
    public Particle cloneWithRandomColor() {
        return new Sand(fallSpeed, sandiness, generateRandomColor(), name,colorRandomness );
    }
}
