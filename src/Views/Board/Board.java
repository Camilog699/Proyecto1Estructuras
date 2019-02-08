package Views.Board;

import Models.Cave;
import Models.Tree;
import Models.Truck;

import javax.swing.*;
import java.util.LinkedList;

public class Board {
    Tree tree;
    LinkedList<Truck> trucks;

    public JPanel panel;
    private JButton btnAddCave;
    private JButton btnAddTruck;
    public Board(Tree tree, LinkedList<Truck> trucks) {
        this.tree = tree;
        this.trucks = trucks;
        btnAddTruck.addActionListener(e -> trucks.add(new Truck(40, 30)));
        btnAddCave.addActionListener(e -> tree.add(new Cave()));
    }

    private void createUIComponents() {
        this.panel = new BoardPanel(tree, trucks);
    }

}