package Clases;

import Modelos.Cueva;

public class Nodo {
    private Nodo izq;
    private Cueva cueva;
    private Nodo der;
    private int nivel;
    private int fe;
    
    public Nodo() {
    
    }
    
    Nodo(Nodo izq, Cueva cueva, Nodo der, int nivel) {
        this.izq = izq;
        this.cueva = cueva;
        this.der = der;
        this.nivel = nivel;
        this.fe = 0;
    }
    
    
    public Nodo getIzq() {
        return izq;
    }
    
    public void setIzq(Nodo izq) {
        this.izq = izq;
    }
    
    public Cueva getCueva() {
        return cueva;
    }
    
    public void setCueva(Cueva cueva) {
        this.cueva = cueva;
    }
    
    public Nodo getDer() {
        return der;
    }
    
    public void setDer(Nodo der) {
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

