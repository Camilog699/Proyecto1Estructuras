import Classes.Tree;
import Models.Truck;
import Models.Cave;
import Views.Board.BoardFrame;
import Views.Board.Board;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.Iterator;
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
        JFrame index = new BoardFrame("The Paro");
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
        JSONParser parser = new JSONParser();
        Tree tree = new Tree();
    
        try {
            Object cavesJSON = parser.parse(new FileReader(Project.class.getResource("json/tree.json").getFile()));
        
            JSONArray caves = (JSONArray) cavesJSON;
            Iterator iterator = caves.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonTree = (JSONObject) iterator.next();
                String material = (String) jsonTree.get("material");
                int amount = ((Long) jsonTree.get("amount")).intValue();
                Cave cave = new Cave(material, amount);
                tree.add(cave);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tree;
    }
    
    private static LinkedList<Truck> loadInitialTrucks() {
        LinkedList<Truck> trucks = new LinkedList<>();
        trucks.add(new Truck(40, 30));
        return trucks;
    }
}
