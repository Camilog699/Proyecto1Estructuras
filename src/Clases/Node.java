package Clases;

import Modelos.Cove;

public class Node {
    private Node izq;
    private Cove cove;
    private Node der;
    private int nivel;
    private int fe;
    
    public Node() {
    
    }
    
    Node(Node izq, Cove cove, Node der, int nivel) {
        this.izq = izq;
        this.cove = cove;
        this.der = der;
        this.nivel = nivel;
        this.fe = 0;
    }
    
    
    public Node getLeft() {
        return izq;
    }
    
    public void setIzq(Node izq) {
        this.izq = izq;
    }
    
    public Cove getCove() {
        return cove;
    }
    
    public void setCove(Cove cove) {
        this.cove = cove;
    }
    
    public Node getRight() {
        return der;
    }
    
    public void setDer(Node der) {
        this.der = der;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public int getFe() {
        return fe;
    }
    
    public void setFe(int fe) {
        this.fe = fe;
    }
}

