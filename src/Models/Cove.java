package Models;

import java.util.Random;

public class Cove extends Base {
    private String materials[] = {"Madera", "Oro", "Plata", "Bronce", "Piedra"};
    private String material;
    private int amount;
    private boolean blocked;
    
    public Cove() {
        super(0, 0, 60, 60, "../img/cove.png");
        this.material = materials[new Random().nextInt(materials.length)];
        this.amount = new Random().nextInt(500);
    }
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public boolean isBlocked() {
        return blocked;
    }
    
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
