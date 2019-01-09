package Vistas.Tablero;

import Clases.Arbol;
import Modelos.Camion;

import javax.swing.*;
import java.util.LinkedList;

public class Tablero {
    Arbol arbol;
    LinkedList<Camion> camiones;
    
    public Tablero(Arbol arbol, LinkedList<Camion> camiones) {
        this.arbol = arbol;
        this.camiones = camiones;
        btnAgregarCamion.addActionListener(e -> camiones.add(new Camion(40, 30)));
    }
    
    public JPanel panel;
    private JButton btnAgregarCamion;
    
    
    private void createUIComponents() {
        this.panel = new TableroPanel(arbol, camiones);
    }
}