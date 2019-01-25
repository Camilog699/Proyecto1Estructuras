import Classes.Tree;
import Models.Truck;
import Models.Cove;
import Views.Board.BoardFrame;
import Views.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Project {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        UIManager.put("Label.font", new javax.swing.plaf.FontUIResource("Raleway", Font.PLAIN, 12));
        setUIFont(new javax.swing.plaf.FontUIResource("Raleway", Font.PLAIN, 12));
        UIManager.put("TabbedPane.contentOpaque", false);
        JFrame index = new BoardFrame("El Paro");
        index.setContentPane(new Board(loadInitialTree(), loadInitialTrucks()).panel);
        index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        index.pack();
        index.setLocationRelativeTo(null);
        index.setVisible(true);
        index.setResizable(false);
    }
    
    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
    
    private static Tree loadInitialTree() {
        Tree tree = new Tree();
        tree.add(new Cove());
        while (JOptionPane.showConfirmDialog(null, "¿Desea hacer una nueva excavación?") == JOptionPane.YES_OPTION) {
            tree.add(new Cove());
        }
        return tree;
    }
    
    private static LinkedList<Truck> loadInitialTrucks() {
        LinkedList<Truck> trucks = new LinkedList<>();
        trucks.add(new Truck(40, 30));
        return trucks;
    }
}
