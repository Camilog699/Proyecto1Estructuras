package Vistas.Tablero;

import Clases.Arbol;
import Clases.Nodo;
import Modelos.CaminoH;
import Modelos.CaminoV;
import Modelos.Camion;
import Modelos.Cueva;

import java.awt.*;
import java.util.LinkedList;

public class TableroPanel extends javax.swing.JPanel {
    Arbol arbol;
    LinkedList<Camion> camiones;
    Boolean pausado;
    
    public TableroPanel(Arbol arbol, LinkedList<Camion> camiones) {
        this.arbol = arbol;
        this.camiones = camiones;
        this.pausado = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!pausado) loop(g);
    }
    
    private void dibujarArbol(Graphics g) {
        dibujarCueva(arbol.getRaiz(), g);
    }
    
    private void dibujarCueva(Nodo n, Graphics g) {
        Cueva cueva = n.getCueva();
        g.drawImage(cueva.getSprite().getImage(), cueva.getX(), cueva.getY(), cueva.getWidth(), cueva.getHeight(), this);
        crearLabelsInfo(cueva, g);
        if (n.getIzq() != null || n.getDer() != null) {
            CaminoV caminoV = new CaminoV(cueva.getX() - 12 + cueva.getWidth() / 2, cueva.getY() + cueva.getHeight());
            g.fillRect(caminoV.getX(), caminoV.getY(), caminoV.getWidth(), caminoV.getHeight());
            if (n.getIzq() != null) {
                CaminoH caminoH = new CaminoH(caminoV.getX() - caminoV.getHeight(), caminoV.getY() + caminoV.getHeight() / 2);
                g.fillRect(caminoH.getX(), caminoH.getY(), caminoH.getWidth(), caminoH.getHeight());
                dibujarCueva(n.getIzq(), g);
            }
            if (n.getDer() != null) {
                CaminoH caminoH = new CaminoH(caminoV.getX() + caminoV.getWidth(), caminoV.getY() + caminoV.getHeight() / 2);
                g.fillRect(caminoH.getX(), caminoH.getY(), caminoH.getWidth(), caminoH.getHeight());
                dibujarCueva(n.getDer(), g);
            }
        }
    }
    
    private void crearLabelsInfo(Cueva c, Graphics g) {
        g.drawRect(c.getX() - 2, c.getY() - 19, c.getMaterial().length() * 8, 15);
        g.drawRect(c.getX() + c.getWidth() + 1, c.getY() + c.getHeight() - 12, Integer.toString(c.getCantidad()).length() * 8, 15);
        g.drawString(c.getMaterial(), c.getX(), c.getY() - 7);
        g.drawString(Integer.toString(c.getCantidad()), c.getX() + c.getWidth() + 3, c.getY() + c.getHeight());
    }
    
    private void dibujarCamion(Camion camion, Graphics g) {
        g.drawImage(camion.getSprite().getImage(), camion.getX(), camion.getY(), camion.getWidth(), camion.getHeight(), this);
    }
    
    private void loop(Graphics g) {
        dibujarArbol(g);
        camiones.forEach(camion -> dibujarCamion(camion, g));
        repaint();
    }
}