package Models;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Supervisor extends Base implements Runnable {
    int actualImage;
    String direction;
    boolean returning;
    int step;
    Node root;
    Node nextNode;
    LinkedList<Node> nodes;
    Queue<Node> queue;

    public Supervisor() {
        super(50, 20, 56, 42, "../img/supervisor/right/superv1.png");
        this.actualImage = 1;
        this.direction = "right";
        new Thread(this).start();
        this.step = 10;
        this.returning = false;
        this.queue = new LinkedList<>();
        this.nodes = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true) {
            this.actualImage++;
            if (this.actualImage > 4) this.actualImage = 1;
            if (this.root != null) {
                if (!this.root.isVisited()) moveToRoot();
                else if (nextNode != null) moveToNode(nextNode);
            }
            this.setSprite(new ImageIcon(getClass().getResource("../img/supervisor/" + direction + "/superv" + actualImage + ".png")));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void supervisar(Node root) {
        queue.add(root);
        this.root = root;
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            nodes.add(tempNode);

            /*Enqueue left child */
            if (tempNode.getLeft() != null) {
                queue.add(tempNode.getLeft());
            }

            /*Enqueue right child */
            if (tempNode.getRight() != null) {
                queue.add(tempNode.getRight());
            }
        }
    }

    void moveToRoot() {
        if (this.getRect().intersects(this.root.getRect())) {
            this.root.setVisited(true);
            this.nextNode = nodes.get(1);
            return;
        }
        moveRight();
    }

    Node getActualParent(Node parent, Node node) {
        if (parent == null) return null;
        if (parent == node) return parent;
        if (getActualParent(parent.getLeft(), node) == node) {
            leftGoToParent(parent);
            return parent;
        }
        if (getActualParent(parent.getRight(), node) == node) {
            rightGoToParent(parent);
            return parent;
        }
        return null;
    }

    void rightGoToParent(Node parent) {
        if (this.getX() > parent.getCave().getX()) {
            moveLeft();
        }
    }

    void leftGoToParent(Node parent) {

    }

    void moveToNode(Node node) {
        if (node == null) return;
        if (node.getRect().intersects(this.getRect())) {
            node.setVisited(true);
            int nextPos = nodes.indexOf(nextNode) + 1;
            if (nextPos < nodes.size()) nextNode = nodes.get(nextPos);
            return;
        }

        if (isSub(node, nodes.get(nodes.indexOf(nextNode) - 1).getLeft())) {
            if (this.getY() < node.getCave().getY()) {
                moveDown();
                return;
            }
            if (!this.getRect().intersects(node.getRect())) {
                moveLeft();
                return;
            }
        }
        if (isSub(node, nodes.get(nodes.indexOf(nextNode) - 1).getRight())) {
            if (this.getY() < node.getCave().getY()) {
                moveDown();
                return;
            }
            if (!this.getRect().intersects(node.getRect())) {
                moveRight();
                return;
            }
        }
        Node parent = getActualParent(this.root, node);
        if (parent == null) return;
        if (parent.getCave().getX() < this.getX()) {
            moveLeft();
            return;
        }
        if (parent.getCave().getX() > this.getX()) {
            moveRight();
            return;
        }
        if (parent.getCave().getY() < this.getY()) {
            moveUp();
            return;
        }
    }

    boolean isSub(Node node, Node parent) {
        if (parent == null) return false;
        if (parent == node) return true;
        return isSub(node, parent.getLeft()) || isSub(node, parent.getRight());
    }

    void moveRight() {
        this.direction = "right";
        this.setX(this.getX() + step);
    }

    void moveDown() {
        this.setY(this.getY() + step);
    }

    void moveLeft() {
        this.direction = "left";
        this.setX(this.getX() - step);
    }

    void moveUp() {
        this.setY(this.getY() + step);
    }

    Rectangle getRect() {
        int define = -50;
        if (direction.equals("left")) define = 50;
        return new Rectangle(this.getX() + define, this.getY(), this.getWidth(), this.getHeight());
    }

    public void draw(Graphics g) {
        g.drawImage(this.getSprite().getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }
}
