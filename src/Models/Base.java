package Models;

import javax.swing.*;

public class Base {
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon sprite;

    Base(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    Base(int x, int y, int width, int height, String ruta) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = new ImageIcon(getClass().getResource(ruta));
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public ImageIcon getSprite() {
        return sprite;
    }

    void setSprite(ImageIcon sprite) {
        this.sprite = sprite;
    }
}
