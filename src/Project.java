import Models.Cave;
import Models.Supervisor;
import Models.Tree;
import Views.Board.Board;
import Views.Board.BoardFrame;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;

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
        index.setContentPane(new Board(loadInitialTree(), new Supervisor()).panel);
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
            for (Object cave : caves) {
                JSONObject jsonTree = (JSONObject) cave;
                String material = (String) jsonTree.get("material");
                int amount = ((Long) jsonTree.get("amount")).intValue();
                tree.add(new Cave(material, amount));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tree;
    }
}
