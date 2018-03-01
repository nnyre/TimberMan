/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Renny Ramirez
 * clase menu donde elijo entre las opciones jugar, creditos, instrucciones o salir
 */
public class Menu {

    JFrame menu;
    JButton jugar;
    JButton instrucciones;
    JButton creditos;
    JButton salir;
    JLabel fondo;
    JLabel piePag;
    public Clip reprod;
/**
 * constructor donde llamo los metodos para dar vida al menu
 */
    public Menu() {
        inicializar();
        locaclizar();
        asociar();
        eventos();
        sonido();
    }
/**
 * en este metodo sonido monto el intro del menu que suena al uniciar el frame del menir
 */
    public void sonido() {
        AudioInputStream flujo = null;
        try {
            URL url = getClass().getResource("audio/intro.wav");
            flujo = AudioSystem.getAudioInputStream(url);
            AudioFormat format = flujo.getFormat();
            DataLine.Info info;
            info = new DataLine.Info(Clip.class, format, (int) (flujo.getFrameLength() * format.getFrameSize()));
            reprod = (Clip) AudioSystem.getLine(info);
            reprod.open(flujo);
            reprod.start();
            // reprod.loop(Clip.LOOP_CONTINUOUSLY);
            // System.in.read();
            //reprod.stop();
            
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(timberMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(timberMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(timberMan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                flujo.close();
            } catch (IOException ex) {
                Logger.getLogger(timberMan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
/**
 * aqui instancio los atributos de la clase
 */
    public void inicializar() {
        menu = new JFrame("TimberMan v6.66");
        jugar = new JButton("Jugar.");
        instrucciones = new JButton("Instrucciones.");
        creditos = new JButton("Creditos.");
        salir = new JButton("Salir.");
        piePag = new JLabel("Desarrollado por Renny Ramírez @renramirez93");
        fondo = new JLabel(new ImageIcon(getClass().getResource("pics/menuFondo.jpg")));
    }
/**
 * aqui ubico a los atributos en el fram del menu y doy tamaño al menu
 */
    public void locaclizar() {
        menu.setSize(500, 600);
        jugar.setBounds(160, 100, 150, 50);
        instrucciones.setBounds(160, 200, 150, 50);
        creditos.setBounds(160, 300, 150, 50);
        salir.setBounds(160, 400, 150, 50);
        piePag.setBounds(0 , 520, 400 , 50);
        fondo.setBounds(0, -200, 564, 912);
    }
/**
 * aqui agrego los elementos al frame y cambio las fuentes de las etiquetas
 */
    public void asociar() {
        menu.setResizable(false);
        menu.setLayout(null);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        menu.add(piePag);
        piePag.setForeground(Color.yellow);
        Font auxFont = piePag.getFont();
        piePag.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 10));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.add(jugar);
        menu.add(instrucciones);
        menu.add(creditos);
        menu.add(salir);
        menu.add(fondo);
    }
/**
 * aqui creo los botones del frame 
 * ya sea para jugar o mostrar los mensajes de creditos o instruciones
 */
    public void eventos() {
        jugar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reprod.stop();
                //timberMan timber = new timberMan();
                Inicio inicio = new Inicio();
                // menu.setVisible(false);
            }
        });
        salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        creditos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Desarrollado por Renny Ramírez\nV-21.416.562\nUNET\nIng. Informática");
            }
        });
        instrucciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Debes de golpear el arbol con las flechas.\nAsegurate que las ramas no te caigan encima!\nmuevete a los lados\nNo dejes que la barra de vida llegue a cero");
            }
        });
    }

}
