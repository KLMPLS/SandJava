package SandB;

import java.util.Random;

public class Color {
    int r, g, b;
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public int getRed() {
        return r;
    }
    public int getGreen() {
        return g;
    }
    public int getBlue() {
        return b;
    }
    public Color randomColor(int howRandom) {
        Random rand = new Random();
        int r = rand.nextInt(howRandom)* (rand.nextBoolean()?-1:1) + this.r;
        int g = rand.nextInt(howRandom)* (rand.nextBoolean()?-1:1) + this.g;
        int b = rand.nextInt(howRandom)* (rand.nextBoolean()?-1:1) + this.b;
        if(r < 0) {r = 0;}
        if(g < 0) {g = 0;}
        if(b < 0) {b = 0;}
        if(r > 255) {r = 255;}
        if(g > 255) {g = 255;}
        if(b > 255) {b = 255;}

        return new Color(r, g, b);
    }
}
