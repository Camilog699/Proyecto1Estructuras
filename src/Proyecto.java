import Clases.Arbol;
import Modelos.Camion;
import Modelos.Cueva;
import Vistas.Tablero.FrameTablero;
import Vistas.Tablero.Tablero;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Proyecto {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        UIManager.put("Label.font", new javax.swing.plaf.FontUIResource("Raleway", Font.PLAIN, 12));
        setUIFont(new javax.swing.plaf.FontUIResource("Raleway", Font.PLAIN, 12));
        UIManager.put("TabbedPane.contentOpaque", false);
        JFrame inicio = new FrameTablero("El Paro");
        inicio.setContentPane(new Tablero(cargarArbolInicial(), cargarCamionesIniciales()).panel);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.pack();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        inicio.setResizable(false);
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
    
    private static Arbol cargarArbolInicial() {
        Arbol arbol = new Arbol();
        arbol.ingresar(new Cueva());
        while (JOptionPane.showConfirmDialog(null, "¿Desea hacer una nueva excavación?") == JOptionPane.YES_OPTION) {
            arbol.ingresar(new Cueva());
        }
        return arbol;
    }
    
    private static LinkedList<Camion> cargarCamionesIniciales() {
        LinkedList<Camion> camiones = new LinkedList<>();
        camiones.add(new Camion(40, 30));
        return camiones;
    }
}
