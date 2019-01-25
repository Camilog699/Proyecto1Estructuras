package Vistas.Board;

import Clases.Tree;
import Modelos.Truck;

import javax.swing.*;
import java.util.LinkedList;

public class Board {
    Tree tree;
    LinkedList<Truck> trucks;
    
    public Board(Tree tree, LinkedList<Truck> trucks) {
        this.tree = tree;
        this.trucks = trucks;
        btnAddTruck.addActionListener(e -> trucks.add(new Truck(40, 30)));
    }
    
    public JPanel panel;
    private JButton btnAddTruck;
    
    
    private void createUIComponents() {
        this.panel = new BoardPanel(tree, trucks);
    }
}