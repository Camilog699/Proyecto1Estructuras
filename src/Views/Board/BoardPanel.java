package Views.Board;

import Models.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;

public class BoardPanel extends javax.swing.JPanel {
    Tree tree;
    LinkedList<Truck> trucks;
    Boolean paused;
    Supervisor supervisor;

    public BoardPanel(Tree tree, LinkedList<Truck> trucks, Supervisor supervisor) {
        this.tree = tree;
        this.trucks = trucks;
        this.supervisor = supervisor;
        this.paused = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!paused) loop(g);
    }

    private void drawTree(Graphics2D g) {
        brightMaxAmount(g);
        brightMaxProfit(g);
        drawCove(tree.getRoot(), g);
    }

    private void brightMaxAmount(Graphics2D g) {
        Color actualColor = g.getColor();
        Cave cave = tree.getMaxAmount().getCave();
        g.setColor(Color.decode("#0074D9"));
        g.fillOval(cave.getX() - 5, cave.getY() - 5, cave.getWidth() + 5, cave.getHeight() + 5);
        g.setColor(actualColor);
    }

    private void brightMaxProfit(Graphics2D g) {
        Color actualColor = g.getColor();
        Cave cave = tree.getMaxProfit().getCave();
        g.setColor(Color.decode("#FF851B"));
        g.fillOval(cave.getX() - 5, cave.getY() - 5, cave.getWidth() + 5, cave.getHeight() + 5);
        g.setColor(actualColor);
    }

    private void drawCove(Node n, Graphics2D g) {
        Cave cave = n.getCave();
        if (cave.isSelected()) {
            Color actualColor = g.getColor();
            g.setColor(Color.RED);
            g.fillOval(cave.getX() - 20, cave.getY() + cave.getHeight() - 20, 20, 20);
            g.setColor(actualColor);
        }
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

    private void createInfoLabels(Cave cave, Graphics2D g) {
        g.drawString(cave.getMaterial() + ":" + cave.getAmount(), cave.getX(), cave.getY() - 7);
        g.drawString(Integer.toString(cave.getValue()), cave.getX() + cave.getWidth() + 3, cave.getY() + cave.getHeight());
    }

    private void drawTruck(Truck truck, Graphics2D g) {
        g.drawImage(truck.getSprite().getImage(), truck.getX(), truck.getY(), truck.getWidth(), truck.getHeight(), this);
    }

    private void activateAntiAliasing(Graphics2D g2d) {
        Map<?, ?> desktopHints =
                (Map<?, ?>) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");

        if (desktopHints != null) {
            g2d.setRenderingHints(desktopHints);
        }
    }

    private void loop(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        activateAntiAliasing(g2d);

        drawTree(g2d);
        trucks.forEach(truck -> drawTruck(truck, g2d));
        this.supervisor.draw(g);
        repaint();
    }
}