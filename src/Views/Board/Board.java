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
    private JButton btnRemoveCave;
    private JButton btnReadyToRemove;
    private JButton btnReadyToRemove2;
    private JButton btnRenoveDaughterCave;
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
            trucks.forEach(truck -> {
                if (truck.isOptim()) {
                    LinkedList<Node> optimList = new LinkedList<>();
                    optimPath(tree.getRoot(), optimList);
                    truck.setNodes(optimList);
                }
            });
            loadReport();
        });
        btnReady.addActionListener(e -> {
            LinkedList<Node> list = new LinkedList<>();
            creatingList.forEach(nodo -> {
                nodo.getCave().setSelected(false);
                pathToNode(this.tree.getRoot(), list, nodo);
            });
            if (tree.getRoot() != list.get(0)) {
                list.addFirst(tree.getRoot());
            }
            trucks.add(new Truck(40, 30, list));
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
                        clickedNode.getCave().setSelected(!clickedNode.getCave().isSelected());
                        if (creatingList.indexOf(clickedNode) > -1) creatingList.remove(clickedNode);
                        else creatingList.add(clickedNode);
                    }
                }
            }
        });

        btnDigBetterWay.addActionListener(e -> {
            LinkedList<Node> optimList = new LinkedList<>();
            optimPath(tree.getRoot(), optimList);
            Truck truck = new Truck(40, 20, optimList);
            truck.setOptim(true);
            trucks.add(truck);
        });
    }

    private boolean pathToNode(Node parent, LinkedList<Node> list, Node node) {
        if (parent == null) return false;
        if (parent == node) {
            return true;
        }
        if (pathToNode(parent.getLeft(), list, node)) {
            list.add(parent);
            list.add(parent.getLeft());
            return true;
        }
        if (pathToNode(parent.getRight(), list, node)) {
            list.add(parent);
            list.add(parent.getRight());
            return true;
        }
        return false;
    }

    private void optimPath(Node parent, LinkedList<Node> optimList) {
        if (parent == null) return;
        optimList.add(parent);
        optimPath(parent.getRight(), optimList);
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