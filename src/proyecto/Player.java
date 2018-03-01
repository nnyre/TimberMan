/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Renny Ramirez
 * en la clase creo el jugador
 */
public class Player {
    
    public int x = 250;
    public int y = 250;
    public int ancho = 100;
    public int alto = 115;
    //public JLabel [] figura;
    ArrayList<JLabel> figura = new ArrayList<>();
    public JLabel nombre;
    /**
     * en el contrctor doy memoria a los atributos de la clase player
     * cambio la fuente del nombre que se muestra en el juego y cargo las imagenes del vector figura del jugador
     */
    public Player() {
        String[] ruta = {"parado1.png", "goku1.png", "parado2.png", "goku2.png"};
        for (int i = 0; i < ruta.length; i++) {
            figura.add(new JLabel(new ImageIcon(getClass().getResource("pics/" + ruta[i]))));
        }
        //figura = new JLabel(new ImageIcon(getClass().getResource("pics/goku.png")));
        nombre = new JLabel("Renny");
        nombre.setBounds(15, 0, 300, 100);
        nombre.setBackground(Color.WHITE);
        nombre.setForeground(Color.GREEN);
        Font auxFont = nombre.getFont();
        nombre.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 20));
        figura.get(0).setBounds(15, 480, 100, 186);
        figura.get(0).setVisible(true);
        
    }
      
}
