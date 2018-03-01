/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Renny Ramirez
 * 
 */
public class Inicio {

    JFrame vent;
    JButton guardar;
    JTextField nombre;
    JLabel mensaje;
    Player jugadorNom;
    String jugador;
    int limite = 8;
    /**
     * constructor de la clase donde 
     * doy memoria y ubico los atributos del panel
     */
    public Inicio() {
        vent = new JFrame("Inicio");
        guardar = new JButton("Guardar");
        mensaje = new JLabel("Su nombre (8 caracteres máximo)");
        nombre = new JTextField("Nombre aquí...");
        vent.setSize(300, 300);
        vent.getContentPane().setBackground(Color.white);
        vent.setLocationRelativeTo(null);
        vent.setResizable(false);
        vent.setLayout(null);
        guardar.setBounds(50, 160, 200, 50);
        nombre.setBounds(50, 100, 200, 50);
        mensaje.setBounds(50, 50, 300, 50);
        vent.setVisible(true);
        vent.add(guardar);
        vent.add(nombre);
        vent.add(mensaje);
        eventos();
    }
/**
 * en el metodo eventos limito el campo de texto a solo 8 caracteres
 * y finalmente cuando el usuario presiona guardar empieza el juego
 */
    public void eventos() {
        nombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nombre.setText("");
            }
        });
        guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vent.setVisible(false);
                jugador = nombre.getText();
                timberMan tim = new timberMan(jugador);                           
            }
        });
        nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (nombre.getText().length() == limite) {
                    e.consume();
                }
            }      
        });
    }

}
