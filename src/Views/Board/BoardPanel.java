package Views.Board;

import Classes.Node;
import Classes.Tree;
import Models.Cave;
import Models.HPath;
import Models.Truck;
import Models.VPath;

import java.awt.*;
import java.util.LinkedList;

public class BoardPanel extends javax.swing.JPanel {
    Tree tree;
    LinkedList<Truck> trucks;
    Boolean paused;
    
    public BoardPanel(Tree tree, LinkedList<Truck> trucks) {
        this.tree = tree;
        this.trucks = trucks;
        this.paused = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!paused) loop(g);
    }
    
    private void drawTree(Graphics g) {
        drawCove(tree.getRoot(), g);
    }
    
    private void drawCove(Node n, Graphics g) {
        Cave cave = n.getCave();
        g.drawImage(cave.getSprite().getImage(), cave.getX(), cave.getY(), cave.getWidth(), cave.getHeight(), this);
        createInfoLabels(cave, g);
        if (n.getLeft() != null || n.getRight() != null) {
            VPath vPath = new VPath(cave.getX() - 12 + cave.getWidth() / 2, cave.getY() + cave.getHeight());
            g.fillRect(vPath.getX(), vPath.getY(), vPath.getWidth(), vPath.getHeight());
            if (n.getLeft() != null) {
                HPath hPath = new HPath(vPath.getX() - vPath.getHeight(), vPath.getY() + vPath.getHeight() / 2);
                g.fillRect(hPath.getX(), hPath.getY(), hPath.getWidth(), hPath.getHeight());
                drawCove(n.getLeft(), g);
            }
            if (n.getRight() != null) {
                HPath hPath = new HPath(vPath.getX() + vPath.getWidth(), vPath.getY() + vPath.getHeight() / 2);
                g.fillRect(hPath.getX(), hPath.getY(), hPath.getWidth(), hPath.getHeight());
                drawCove(n.getRight(), g);
            }
        }
    }
    
    private void createInfoLabels(Cave c, Graphics g) {
        g.drawString(c.getMaterial(), c.getX(), c.getY() - 7);
        g.drawString(Integer.toString(c.getAmount()), c.getX() + c.getWidth() + 3, c.getY() + c.getHeight());
    }
    
    private void drawTruck(Truck truck, Graphics g) {
        g.drawImage(truck.getSprite().getImage(), truck.getX(), truck.getY(), truck.getWidth(), truck.getHeight(), this);
    }
    
    private void loop(Graphics g) {
        drawTree(g);
        trucks.forEach(truck -> drawTruck(truck, g));
        repaint();
    }
}