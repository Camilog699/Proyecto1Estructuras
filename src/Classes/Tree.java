package Classes;

import Models.Cove;

public class Tree {
    Node root;
    int height;
    
    public void add(Cove cove) {
        if (this.root == null) {
            cove.setX(720);
            cove.setY(20);
            this.root = new Node(null, cove, null, 1);
        } else {
            addNode(this.root, cove, 2);
        }
        updateFe(this.root);
        this.height++;
    }
    
    private Node rotacionDerecha(Node padre) {
        preOrden(padre);
        Node aux = padre.getLeft();
        padre.setLeft(aux.getRight());
        aux.setRight(padre);
        padre.setFe(padre.getLeft().getFe() - padre.getRight().getFe());
        aux.setFe(aux.getLeft().getFe() - aux.getRight().getFe() + 1);
        return aux;
    }
    
    private Node rotacionDobleDerecha(Node padre) {
        Node aux;
        padre.setRight(rotacionDerecha(padre));
        aux = rotacionIzquierda(padre);
        return aux;
    }
    
    private Node rotacionDobleIzquierda(Node padre) {
        Node aux;
        padre.setLeft(rotacionIzquierda(padre.getLeft()));
        aux = rotacionDerecha(padre);
        return aux;
    }
    
    private Node rotacionIzquierda(Node padre) {
        Node aux = padre.getRight();
        padre.setRight(aux.getLeft());
        aux.setLeft(padre);
        padre.setFe(padre.getLeft().getFe() - padre.getRight().getFe());
        aux.setFe(aux.getLeft().getFe() - aux.getRight().getFe() + 1);
        return aux;
    }
    
    private void updateFe(Node parent) {
        if (parent.getLeft() == null && parent.getRight() == null) {
            parent.setFe(0);
        } else if (parent.getLeft() != null && parent.getRight() == null || parent.getLeft() == null && parent.getRight() != null) {
            parent.setFe(parent.getFe() + 1);
        } else {
            parent.setFe(Math.max(parent.getLeft().getFe(), parent.getRight().getFe()) + 1);
        }
    }
    
    private boolean addNode(Node parent, Cove cove, int level) {
        if (parent == null) return true;
        if (cove.getAmount() < parent.getCove().getAmount()) {
            if (addNode(parent.getLeft(), cove, level + 1)) {
                parent.setLeft(new Node(null, cove, null, level));
                cove.setX(parent.getCove().getX() - 100);
                cove.setY(parent.getCove().getY() + 70);
                return false;
            }
        }
        
        if (cove.getAmount() >= parent.getCove().getAmount()) {
            if (addNode(parent.getRight(), cove, level + 1)) {
                parent.setRight(new Node(null, cove, null, level));
                cove.setX(parent.getCove().getX() + 100);
                cove.setY(parent.getCove().getY() + 70);
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
        if (eliminarHoja(padre.getLeft(), cove)) padre.setLeft(null);
        if (eliminarHoja(padre.getRight(), cove)) padre.setRight(null);
        return padre.getRight() == null && padre.getLeft() == null && padre.getCove() == cove;
    }
    
    public boolean eliminarHijos(Node padre) {
        if (padre == null) return false;
        return false;
    }
    
    public Node getRoot() {
        return root;
    }
    
    public void setRoot(Node root) {
        this.root = root;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
}
