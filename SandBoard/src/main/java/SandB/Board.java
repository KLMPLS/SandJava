package SandB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    List<Particle> particles;
    int width;
    int height;
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        particles = Arrays.asList(new Particle[height*width]);
        for (int i = 0; i < height*width; i++) {
            particles.set(i, new Empty());
        }

    }
    public void updateBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = height; j >= 0 ; j--) {
                get(i, j).update(this, i, j);
            }
        }
    }
    public void set(int x, int y, Particle p) {
        if (isValid(x,y)) {
        particles.set(x + y*width, p);
        }
    }
    public Particle get(int x, int y) {
        if (isValid(x,y)) {
            return particles.get(x + y * width);
        }
        return new Empty();
    }
    public void swap(int x1, int y1, int x2, int y2) {
        Particle p1 = get(x1, y1);
        Particle p2 = get(x2, y2);
        set(x1, y1, p2);
        set(x2, y2, p1);
    }

    public Boolean isEmpty(int x, int y) {
        return get(x, y).getClass() == Empty.class;
    }

    public Boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(get(j,i).toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
