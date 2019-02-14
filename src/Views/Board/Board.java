package Views.Board;

import Models.Cave;
import Models.Node;
import Models.Tree;
import Models.Truck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Board {
    Tree tree;
    LinkedList<Truck> trucks;
    LinkedList<Node> creatingList;
    Node node;
    Node nodeD;
    boolean remove;
    boolean removeD;

    public JPanel panel;
    private JButton btnAddCave;
    private JButton btnSelectWay;
    private JButton btnReady;
    private JButton btnDigBetterWay;
    private JButton btnRemoveCave;
    private JButton btnReadyToRemove;
    private JButton btnReadyToRemove2;
    private JButton btnRemoveDaughterCave;
    private JLabel lablCavesAmount;
    private JLabel lablGold;
    private JLabel lablSilver;
    private JLabel lablBronze;
    private JLabel lablWood;
    private JLabel lablStone;

    public Board(Tree tree) {
        this.tree = tree;
        loadReport();

        btnSelectWay.addActionListener(e -> {
            creatingList = new LinkedList<>();
            btnSelectWay.setVisible(false);
            btnReady.setVisible(true);
        });
        btnAddCave.addActionListener(e -> {
            tree.add(new Cave());
            loadReport();
        });
        btnReady.addActionListener(e -> {
            trucks.add(new Truck(40, 30, creatingList));
            creatingList.forEach(nodo -> nodo.getCave().setSelected(false));
            creatingList = null;
            btnSelectWay.setVisible(true);
            btnReady.setVisible(false);
        });

        btnRemoveCave.addActionListener(e -> {
            remove = true;
            btnRemoveCave.setVisible(false);
            btnReadyToRemove.setVisible(true);
        });

        btnReadyToRemove.addActionListener(e -> {
            tree.eliminarHoja(tree.getRoot(), node.getCave());
            loadReport();
            node.getCave().setSelectedToRemove(false);
            btnReadyToRemove.setVisible(false);
            btnRemoveCave.setVisible(true);
        });

        btnRemoveDaughterCave.addActionListener(e -> {
            removeD = true;
            btnRemoveDaughterCave.setVisible(false);
            btnReadyToRemove2.setVisible(true);
        });

        btnReadyToRemove2.addActionListener(e -> {
            tree.eliminarHijo(tree.getRoot(), nodeD.getCave());
            loadReport();
            nodeD.getCave().setSelectedToRemoveDaughter(false);
            btnReadyToRemove2.setVisible(false);
            btnRemoveDaughterCave.setVisible(true);
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
                if (remove) {
                    Node clickedNode = checkTreeClick(e, tree.getRoot());
                    if (clickedNode != null) {
                        clickedNode.getCave().setSelectedToRemove(true);
                        node = clickedNode;
                        remove = false;
                    }
                }
                if (removeD) {
                    Node clickedNode = checkTreeClick(e, tree.getRoot());
                    if (clickedNode != null) {
                        clickedNode.getCave().setSelectedToRemoveDaughter(true);
                        nodeD = clickedNode;
                        removeD = false;
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
        Rectangle caveRect = new Rectangle(parent.getCave().getX(), parent.getCave().getY(), parent.getCave().getWidth(), parent.getCave().getHeight());
        Rectangle mouseRect = new Rectangle(e.getX(), e.getY(), 1, 1);
        return caveRect.intersects(mouseRect);
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

    private void loadReport() {
        lablCavesAmount.setText("Caves amount: " + tree.getCavesAmount(tree.getRoot()));
        lablGold.setText("Gold amount: " + tree.getMaterialAmount(tree.getRoot(), "Gold"));
        lablSilver.setText("Silver amount: " + tree.getMaterialAmount(tree.getRoot(), "Silver"));
        lablBronze.setText("Bronze amount: " + tree.getMaterialAmount(tree.getRoot(), "Bronze"));
        lablWood.setText("Wood amount: " + tree.getMaterialAmount(tree.getRoot(), "Wood"));
        lablStone.setText("Stone amount: " + tree.getMaterialAmount(tree.getRoot(), "Stone"));
    }

    private void createUIComponents() {
        this.trucks = new LinkedList<>();
        this.panel = new BoardPanel(tree, trucks);
    }

}