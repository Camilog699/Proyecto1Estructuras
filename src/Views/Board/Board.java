package Views.Board;

import Models.Cave;
import Models.Node;
import Models.Tree;
import Models.Truck;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Board {
    Tree tree;
    LinkedList<Truck> trucks;
    LinkedList<Node> creatingList;

    public JPanel panel;
    private JButton btnAddCave;
    private JButton btnSelectWay;
    private JButton btnReady;
    private JButton btnDigBetterWay;

    public Board(Tree tree) {
        this.tree = tree;
        btnSelectWay.addActionListener(e -> {
            creatingList = new LinkedList<>();
            btnSelectWay.setVisible(false);
            btnReady.setVisible(true);
        });
        btnAddCave.addActionListener(e -> tree.add(new Cave()));
        btnReady.addActionListener(e -> {
            trucks.add(new Truck(40, 30, creatingList));
            creatingList.forEach(nodo -> nodo.getCave().setSelected(false));
            creatingList = null;
            btnSelectWay.setVisible(true);
            btnReady.setVisible(false);
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (creatingList != null) {
                    Node clickedNode = checkTreeClick(e, tree.getRoot());
                    if (clickedNode != null) {
                        clickedNode.getCave().setSelected(true);
                        creatingList.add(clickedNode);
                    }
                }
            }
        });

        btnDigBetterWay.addActionListener(e -> {
            LinkedList<Node> optimList = new LinkedList<>();
            optimPath(tree.getRoot(), optimList);
            trucks.add(new Truck(40, 30, optimList));
        });
    }

    private void optimPath(Node parent, LinkedList<Node> optimList) {
        if (parent == null) return;
        optimPath(parent.getRight(), optimList);
        optimList.add(parent);
    }

    private boolean checkNodeClick(MouseEvent e, Node parent) {
        return parent.getCave().getX() <= e.getX() && parent.getCave().getX() + parent.getCave().getWidth() >= e.getX()
                && parent.getCave().getY() <= e.getY() && parent.getCave().getY() + parent.getCave().getHeight() >= e.getY();
    }

    private Node checkTreeClick(MouseEvent e, Node parent) {
        if (parent == null) return null;
        if (checkNodeClick(e, parent)) return parent;
        Node left = checkTreeClick(e, parent.getLeft());
        Node right = checkTreeClick(e, parent.getRight());
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }

    private void createUIComponents() {
        this.trucks = new LinkedList<>();
        this.panel = new BoardPanel(tree, trucks);
    }

}