package Clases;

import Modelos.Cueva;

public class Arbol {
    Nodo raiz;
    int altura;
    
    public void ingresar(Cueva cueva) {
        if (this.raiz == null) {
            cueva.setX(720);
            cueva.setY(20);
            this.raiz = new Nodo(null, cueva, null, 1);
        } else {
            ingresarArbol(this.raiz, cueva, 2);
        }
        actualizarFe(this.raiz);
        this.altura++;
    }
    
    private Nodo rotacionDerecha(Nodo padre) {
        preOrden(padre);
        Nodo aux = padre.getIzq();
        padre.setIzq(aux.getDer());
        aux.setDer(padre);
        padre.setFe(padre.getIzq().getFe() - padre.getDer().getFe());
        aux.setFe(aux.getIzq().getFe() - aux.getDer().getFe() + 1);
        return aux;
    }
    
    private Nodo rotacionDobleDerecha(Nodo padre) {
        Nodo aux;
        padre.setDer(rotacionDerecha(padre));
        aux = rotacionIzquierda(padre);
        return aux;
    }
    
    private Nodo rotacionDobleIzquierda(Nodo padre) {
        Nodo aux;
        padre.setIzq(rotacionIzquierda(padre.getIzq()));
        aux = rotacionDerecha(padre);
        return aux;
    }
    
    private Nodo rotacionIzquierda(Nodo padre) {
        Nodo aux = padre.getDer();
        padre.setDer(aux.getIzq());
        aux.setIzq(padre);
        padre.setFe(padre.getIzq().getFe() - padre.getDer().getFe());
        aux.setFe(aux.getIzq().getFe() - aux.getDer().getFe() + 1);
        return aux;
    }
    
    private void actualizarFe(Nodo padre) {
        if (padre.getIzq() == null && padre.getDer() == null) {
            padre.setFe(0);
        } else if (padre.getIzq() != null && padre.getDer() == null || padre.getIzq() == null && padre.getDer() != null) {
            padre.setFe(padre.getFe() + 1);
        } else {
            padre.setFe(Math.max(padre.getIzq().getFe(), padre.getDer().getFe()) + 1);
        }
    }
    
    private boolean ingresarArbol(Nodo padre, Cueva cueva, int nivel) {
        if (padre == null) return true;
        if (cueva.getCantidad() < padre.getCueva().getCantidad()) {
            if (ingresarArbol(padre.getIzq(), cueva, nivel + 1)) {
                padre.setIzq(new Nodo(null, cueva, null, nivel));
                cueva.setX(padre.getCueva().getX() - 100);
                cueva.setY(padre.getCueva().getY() + 70);
                return false;
            }
        }
        
        if (cueva.getCantidad() >= padre.getCueva().getCantidad()) {
            if (ingresarArbol(padre.getDer(), cueva, nivel + 1)) {
                padre.setDer(new Nodo(null, cueva, null, nivel));
                cueva.setX(padre.getCueva().getX() + 100);
                cueva.setY(padre.getCueva().getY() + 70);
                return false;
            }
        }
        return false;
    }
    
    void posOrden(Nodo padre) {
        if (padre == null) return;
        preOrden(padre.getIzq());
        preOrden(padre.getDer());
        System.out.println(padre.getCueva());
    }
    
    void preOrden(Nodo padre) {
        if (padre == null) return;
        System.out.println(padre.getCueva());
        posOrden(padre.getIzq());
        posOrden(padre.getDer());
    }
    
    void inOrden(Nodo padre) {
        if (padre == null) return;
        inOrden(padre.getIzq());
        System.out.println(padre.getCueva());
        inOrden(padre.getDer());
    }
    
    boolean eliminarHoja(Nodo padre, Cueva cueva) {
        if (padre == null) return false;
        if (eliminarHoja(padre.getIzq(), cueva)) padre.setIzq(null);
        if (eliminarHoja(padre.getDer(), cueva)) padre.setDer(null);
        return padre.getDer() == null && padre.getIzq() == null && padre.getCueva() == cueva;
    }
    
    public boolean eliminarHijos(Nodo padre) {
        if (padre == null) return false;
        return false;
    }
    
    public Nodo getRaiz() {
        return raiz;
    }
    
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public int getAltura() {
        return altura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
}
