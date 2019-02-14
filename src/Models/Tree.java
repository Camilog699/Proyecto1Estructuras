package Models;

public class Tree {
    private Node root;
    private Node maxProfit;
    private Node maxAmount;

    public void add(Cave cave) {
        if (this.root == null) {
            cave.setX(720);
            cave.setY(20);
            this.root = new Node(null, cave, null);
            maxProfit = this.root;
            maxAmount = this.root;
        } else {
            addNode(this.root, cave);
        }
        updatePositions(this.root);
    }

    private void updatePositions(Node parent) {
        if (parent == null) return;
        int rootX = 720;
        int rootY = 20;
        int distanceX = 150;
        int distanceY = 70;
        if (parent == this.root) {
            parent.getCave().setX(rootX);
            parent.getCave().setY(rootY);
        }
        if (parent.getLeft() != null) {
            parent.getLeft().getCave().setX(parent.getCave().getX() - distanceX);
            parent.getLeft().getCave().setY(parent.getCave().getY() + distanceY);
        }
        if (parent.getRight() != null) {
            parent.getRight().getCave().setX(parent.getCave().getX() + distanceX);
            parent.getRight().getCave().setY(parent.getCave().getY() + distanceY);
        }
        updatePositions(parent.getLeft());
        updatePositions(parent.getRight());
    }

    public Node checkLeftBalance(Node parent, Cave cave) {
        int rightFe = (parent.getRight() == null) ? -1 : parent.getRight().getFe();
        if (parent.getLeft().getFe() - rightFe == 2) {
            if (cave.getValue() < parent.getLeft().getCave().getValue()) {
                parent = rightRotation(parent);
            } else {
                parent = doubleRightRotation(parent);
            }
        }
        return parent;
    }

    public Node checkRightBalance(Node parent, Cave cave) {
        int leftFe = (parent.getLeft() == null) ? -1 : parent.getLeft().getFe();
        if (parent.getRight().getFe() - leftFe == 2) {
            if (cave.getValue() > parent.getRight().getCave().getValue()) {
                parent = leftRotation(parent);
            } else {
                parent = doubleLeftRotation(parent);
            }
        }
        return parent;
    }

    public Node addNode(Node parent, Cave cave) {
        if (parent == null) {
            parent = new Node(null, cave, null);
            if (parent.getCave().getAmount() > maxAmount.getCave().getAmount()) this.maxAmount = parent;
            if (parent.getCave().getValue() > maxProfit.getCave().getValue()) this.maxProfit = parent;
        } else if (cave.getValue() < parent.getCave().getValue()) {
            parent.setLeft(addNode(parent.getLeft(), cave));
            parent = checkLeftBalance(parent, cave);
        } else if (cave.getValue() > parent.getCave().getValue()) {
            parent.setRight(addNode(parent.getRight(), cave));
            parent = checkRightBalance(parent, cave);
        }
        updateFe(parent);
        return parent;
    }

    private void updateFe(Node parent) {
        if (parent.getRight() != null && parent.getLeft() != null) {
            parent.setFe(Math.max(parent.getLeft().getFe(), parent.getRight().getFe()) + 1);
        } else if (parent.getLeft() != null) {
            parent.setFe(parent.getLeft().getFe() + 1);
        } else if (parent.getRight() != null) {
            parent.setFe(parent.getRight().getFe() + 1);
        } else {
            parent.setFe(0);
        }
    }

    private Node rightRotation(Node parent) {
        Node aux = parent.getLeft();
        parent.setLeft(aux.getRight());
        aux.setRight(parent);
        if (parent == this.root) this.root = aux;
        updateFe(aux);
        return aux;
    }

    private Node doubleRightRotation(Node parent) {
        Node aux;
        parent.setLeft(leftRotation(parent.getLeft()));
        aux = rightRotation(parent);
        return aux;
    }

    private Node doubleLeftRotation(Node parent) {
        Node aux;
        parent.setRight(rightRotation(parent.getRight()));
        aux = leftRotation(parent);
        return aux;
    }

    private Node leftRotation(Node parent) {
        Node aux = parent.getRight();
        parent.setRight(aux.getLeft());
        aux.setLeft(parent);
        if (parent == this.root) this.root = aux;
        updateFe(aux);
        return aux;
    }

    public boolean eliminarHoja(Node padre, Cave cave) {
        if (padre == null) return false;
        if (eliminarHoja(padre.getLeft(), cave)) padre.setLeft(null);
        if (eliminarHoja(padre.getRight(), cave)) padre.setRight(null);
        return padre.getRight() == null && padre.getLeft() == null && padre.getCave() == cave;
    }

    public boolean eliminarHijo(Node padre, Cave cave) {
        if (padre == null) return false;
        if (eliminarHijo(padre.getLeft(), cave)) {
            if (padre.getLeft().getLeft() != null) {
                padre.setLeft(padre.getLeft().getLeft());
            } else if (padre.getLeft().getRight() != null) {
                padre.setLeft(padre.getLeft().getRight());
            }
            updatePositions(this.getRoot());
        }
        if (eliminarHijo(padre.getRight(), cave)) {
            if (padre.getRight().getRight() != null) {
                padre.setRight(padre.getRight().getRight());
                padre = padre.getRight().getRight();
            } else if (padre.getRight().getLeft() != null) {
                padre.setRight(padre.getRight().getLeft());
                padre = padre.getRight().getLeft();
            }
            updatePositions(this.getRoot());
        }
        return ((padre.getRight() == null && padre.getLeft() != null) || (padre.getRight() != null && padre.getLeft() == null)) && padre.getCave() == cave;
    }

    public boolean eliminarHijos(Node padre, Cave cave) {
        if (padre == null) return false;

        if (eliminarHijos(padre.getLeft(), cave)) {
            if (padre.getLeft() != null) {
                if (padre.getRight() != null) {
                    padre = padre.getLeft();
                    padre.setLeft(null);
                    padre.setRight(padre.getRight());
                } else {
                    padre = padre.getLeft();
                    padre.setLeft(null);
                }
            }
        }
        return false;
    }

   /* public boolean eliminarHijo(Node padre, Cave cave) {
        if (padre != null) {
            if (padre.getLeft().getLeft() == null && padre.getLeft().getRight() == null) {
                return false;
            }
            if ((padre.getLeft().getLeft() != null && padre.getLeft().getRight() == null)
                    || (padre.getLeft().getLeft() == null && padre.getLeft().getRight() != null)) {
                if (padre.getLeft().getLeft() != null) {
                    padre.setLeft(padre.getLeft().getLeft());
                    eliminarHijo(padre.getLeft(), cave);
                } else if (padre.getLeft().getRight() != null) {
                    padre.setLeft(padre.getLeft().getRight());
                }
            }
            if (padre.getRight().getRight() == null && padre.getRight().getLeft() == null) {
                return false;
            } else if ((padre.getRight().getRight() != null && padre.getRight().getLeft() == null)
                    || (padre.getRight().getRight() == null && padre.getRight().getLeft() != null)) {
                if (padre.getRight().getRight() != null) {
                    padre.setRight(padre.getRight().getRight());
                    eliminarHijo(padre.getRight(), cave);
                } else if (padre.getRight().getLeft() != null) {
                    padre.setRight(padre.getRight().getLeft());
                }
            }
        }
        return false;
    }*/


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(Node maxProfit) {
        this.maxProfit = maxProfit;
    }

    public Node getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Node maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getCavesAmount(Node parent) {
        if (parent == null) return 0;
        return getCavesAmount(parent.getLeft()) + getCavesAmount(parent.getRight()) + 1;
    }

    public int getMaterialAmount(Node parent, String material) {
        if (parent == null) return 0;
        if (parent.getCave().getMaterial().equals(material)) {
            return parent.getCave().getAmount() + getMaterialAmount(parent.getLeft(), material) + getMaterialAmount(parent.getRight(), material);
        }
        return getMaterialAmount(parent.getLeft(), material) + getMaterialAmount(parent.getRight(), material);
    }
}
