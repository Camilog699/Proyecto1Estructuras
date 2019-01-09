package Modelos;

import java.util.Random;

public class Cueva extends Base {
    private String materiales[] = {"Madera", "Oro", "Plata", "Bronce", "Piedra"};
    private String material;
    private int cantidad;
    private boolean bloqueada;
    
    public Cueva() {
        super(0, 0, 60, 60, "../img/cueva.png");
        this.material = materiales[new Random().nextInt(materiales.length)];
        this.cantidad = new Random().nextInt(500);
    }
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public boolean isBloqueada() {
        return bloqueada;
    }
    
    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }
}
