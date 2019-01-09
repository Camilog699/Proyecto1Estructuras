package Modelos;

import javax.swing.*;

public class Camion extends Base implements Runnable {
    private Thread hilo;
    int image;
    
    public Camion(int x, int y) {
        super(x, y, 73, 43, "../img/camion/camion1.png");
        this.image = 1;
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while (true) {
            this.image++;
            if (this.image > 4) this.image = 1;
            this.setSprite(new ImageIcon(getClass().getResource("../img/camion/camion" + image + ".png")));
            try {
                Thread.sleep(166);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
