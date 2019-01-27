package Views.Board;

import Classes.Tree;
import Models.Truck;

import javax.swing.*;
import java.util.LinkedList;

public class Board {
    Tree tree;
    LinkedList<Truck> trucks;

    public Board(Tree tree, LinkedList<Truck> trucks) {
        this.tree = tree;
        this.trucks = trucks;
        btnAddTruck.addActionListener(e -> {
            Truck truck = new Truck(40, 30);
            trucks.add(truck);
        });
    }

    public JPanel panel;
    private JButton btnAddTruck;

    private void createUIComponents() {
        this.panel = new BoardPanel(tree, trucks);
    }

}