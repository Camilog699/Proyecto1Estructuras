package Models;

import java.util.Random;

public class Cave extends Base {
    private String material;
    private int amount;
    private int[] unitPrices = {5, 10, 30, 50, 150};
    private String[] materials = {"Stone", "Wood", "Bronze", "Plate", "Gold"};
    private int value;
    private boolean blocked;
    
    public Cave(String material, int amount) {
        super(0, 0, 60, 60, "../img/cave.png");
        this.material = material;
        this.amount = amount;
        calcValue();
    }

    public Cave() {
        super(0, 0, 60, 60, "../img/cave.png");
        this.material = this.materials[new Random().nextInt(materials.length)];
        this.amount = new Random().nextInt(500);
        calcValue();
    }

    private void calcValue() {
        for (int i = 0; i < this.materials.length; i++) {
            if (materials[i].equals(this.material)) {
                this.value = this.amount * unitPrices[i];
            }
        }
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
