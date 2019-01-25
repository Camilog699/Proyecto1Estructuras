package Classes;

import Models.Cove;

public class Tree {
    Node raiz;
    int altura;
    
    public void add(Cove cove) {
        if (this.raiz == null) {
            cove.setX(720);
            cove.setY(20);
            this.raiz = new Node(null, cove, null, 1);
        } else {
            ingresarArbol(this.raiz, cove, 2);
        }
        actualizarFe(this.raiz);
        this.altura++;
    }
    
    private Node rotacionDerecha(Node padre) {
        preOrden(padre);
        Node aux = padre.getLeft();
        padre.setIzq(aux.getRight());
        aux.setDer(padre);
        padre.setFe(padre.getLeft().getFe() - padre.getRight().getFe());
        aux.setFe(aux.getLeft().getFe() - aux.getRight().getFe() + 1);
        return aux;
    }
    
    private Node rotacionDobleDerecha(Node padre) {
        Node aux;
        padre.setDer(rotacionDerecha(padre));
        aux = rotacionIzquierda(padre);
        return aux;
    }
    
    private Node rotacionDobleIzquierda(Node padre) {
        Node aux;
        padre.setIzq(rotacionIzquierda(padre.getLeft()));
        aux = rotacionDerecha(padre);
        return aux;
    }
    
    private Node rotacionIzquierda(Node padre) {
        Node aux = padre.getRight();
        padre.setDer(aux.getLeft());
        aux.setIzq(padre);
        padre.setFe(padre.getLeft().getFe() - padre.getRight().getFe());
        aux.setFe(aux.getLeft().getFe() - aux.getRight().getFe() + 1);
        return aux;
    }
    
    private void actualizarFe(Node padre) {
        if (padre.getLeft() == null && padre.getRight() == null) {
            padre.setFe(0);
        } else if (padre.getLeft() != null && padre.getRight() == null || padre.getLeft() == null && padre.getRight() != null) {
            padre.setFe(padre.getFe() + 1);
        } else {
            padre.setFe(Math.max(padre.getLeft().getFe(), padre.getRight().getFe()) + 1);
        }
    }
    
    private boolean ingresarArbol(Node padre, Cove cove, int nivel) {
        if (padre == null) return true;
        if (cove.getAmount() < padre.getCove().getAmount()) {
            if (ingresarArbol(padre.getLeft(), cove, nivel + 1)) {
                padre.setIzq(new Node(null, cove, null, nivel));
                cove.setX(padre.getCove().getX() - 100);
                cove.setY(padre.getCove().getY() + 70);
                return false;
            }
        }
        
        if (cove.getAmount() >= padre.getCove().getAmount()) {
            if (ingresarArbol(padre.getRight(), cove, nivel + 1)) {
                padre.setDer(new Node(null, cove, null, nivel));
                cove.setX(padre.getCove().getX() + 100);
                cove.setY(padre.getCove().getY() + 70);
                return false;
            }
        }
        return false;
    }
    
    void posOrden(Node padre) {
        if (padre == null) return;
        preOrden(padre.getLeft());
        preOrden(padre.getRight());
        System.out.println(padre.getCove());
    }
    
    void preOrden(Node padre) {
        if (padre == null) return;
        System.out.println(padre.getCove());
        posOrden(padre.getLeft());
        posOrden(padre.getRight());
    }
    
    void inOrden(Node padre) {
        if (padre == null) return;
        inOrden(padre.getLeft());
        System.out.println(padre.getCove());
        inOrden(padre.getRight());
    }
    
    boolean eliminarHoja(Node padre, Cove cove) {
        if (padre == null) return false;
        if (eliminarHoja(padre.getLeft(), cove)) padre.setIzq(null);
        if (eliminarHoja(padre.getRight(), cove)) padre.setDer(null);
        return padre.getRight() == null && padre.getLeft() == null && padre.getCove() == cove;
    }
    
    public boolean eliminarHijos(Node padre) {
        if (padre == null) return false;
        return false;
    }
    
    public Node getRoot() {
        return raiz;
    }
    
    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }
    
    public int getAltura() {
        return altura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
}
