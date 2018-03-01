/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javax.swing.Timer;

/**
 *
 * @author Renny Ramirez el frame del juego
 */
public class timberMan {

    boolean golpe = false;
    public JFrame ventana;
    Player player;
    JButton saludo;
    JLabel fondo;
    JLabel fondo2;
    public JLabel[] ramaIzq = new JLabel[2];
    public JLabel[] ramaDer = new JLabel[2];
    public Clip reprod;
    Barras barra;
    Timer time;
    int i = 0;
    int reloj = 0;
    Inicio ini;
    Inicio inicio;
    String jugador;
    JLabel troncos;
    int contTroncos = 0;
    JLabel boom;
    Random rand = new Random();
    Timer explota;
    int seg = 0;

    /**
     * constructor de la clase donde doy vida a los metodos de la clase
     *
     * @param jugador
     */
    public timberMan(String jugador) {
        this.jugador = jugador;
        inicializar();
        localizar();
        asociar();
        eventos();
        sonidoFondo();
    }

    /**
     * en inicializar instanceo los atributos
     */
    public void inicializar() {
        ventana = new JFrame("Timber Man");
        player = new Player();
        player.nombre.setText(jugador);
        barra = new Barras();
        saludo = new JButton("saluda!");
        for (int j = 0; j < 2; j++) {
            ramaIzq[j] = new JLabel(new ImageIcon(getClass().getResource("pics/ramaI.png")));
            ramaDer[j] = new JLabel(new ImageIcon(getClass().getResource("pics/ramaDer.png")));
        }
        boom = new JLabel(new ImageIcon(getClass().getResource("pics/explocion.png")));
        troncos = new JLabel(String.valueOf(contTroncos));
        fondo = new JLabel(new ImageIcon(getClass().getResource("pics/arbol.jpg")));
        fondo2 = new JLabel(new ImageIcon(getClass().getResource("pics/arbolmalo.png")));

    }

    /**
     * metodo donde le doy posiciones a todo lo del fram
     */
    public void localizar() {

        ventana.setSize(500, 750);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        troncos.setBounds(235, 0, 300, 200);
        saludo.setBounds(50, 50, 100, 50);
        ventana.add(boom);
        for (int j = 0; j < 2; j++) {
            int x = rand.nextInt(450);
            int x2 = rand.nextInt(300) + 400;
            ramaIzq[j].setBounds(-100, -x, 300, 70);
            ramaDer[j].setBounds(300, -x2, 300, 70);

        }

        fondo.setBounds(0, 0, 500, 750);
        fondo2.setBounds(0, 0, 500, 750);

    }

    /**
     * metodo asociar donde le paso los atributos al jframe
     *
     */
    public void asociar() {
        ventana.getContentPane().setBackground(Color.white);
        ventana.setFocusable(true);
        for (int j = 0; j < 2; j++) {
            ventana.add(ramaIzq[j]);
            ventana.add(ramaDer[j]);
        }
        boom.setVisible(false);
        ventana.add(saludo);
        saludo.setVisible(false);
        saludo.setFocusable(false);
        ventana.add(player.nombre);
        ventana.add(troncos);
        troncos.setForeground(Color.white);
        Font auxFont = troncos.getFont();
        troncos.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
        troncos.setVisible(true);
        for (int i = 0; i < player.figura.size(); i++) {
            ventana.add(player.figura.get(i));
        }
        for (int i = 0; i < barra.barras.size(); i++) {
            ventana.add(barra.barras.get(i));
        }
        barra.barras.get(0).setVisible(true);
        ventana.setVisible(true);
        ventana.add(fondo);
        ventana.add(fondo2);
        fondo2.setVisible(false);
    }

    /**
     * aqui agrego el sonido del fondo mientras se está jugando
     */
    public void sonidoFondo() {
        AudioInputStream flujo = null;
        try {
            URL url = getClass().getResource("audio/beat.wav");
            flujo = AudioSystem.getAudioInputStream(url);
            AudioFormat format = flujo.getFormat();
            DataLine.Info info;
            info = new DataLine.Info(Clip.class, format, (int) (flujo.getFrameLength() * format.getFrameSize()));
            reprod = (Clip) AudioSystem.getLine(info);
            reprod.open(flujo);
            reprod.start();

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
     * aqui creo el sonido de las ramas cuando desaparecen
     */
    public void sonidoExplota() {
        AudioInputStream flujo = null;
        try {
            URL url = getClass().getResource("audio/explocion.wav");
            flujo = AudioSystem.getAudioInputStream(url);
            AudioFormat format = flujo.getFormat();
            DataLine.Info info;
            info = new DataLine.Info(Clip.class, format, (int) (flujo.getFrameLength() * format.getFrameSize()));
            reprod = (Clip) AudioSystem.getLine(info);
            reprod.open(flujo);
            reprod.start();

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
     * aqui creo el sonido cada vez que el personaje golpea al arbol
     */
    public void sonidoGolpe() {
        AudioInputStream flujo = null;
        try {
            URL url = getClass().getResource("audio/golpe.wav");
            flujo = AudioSystem.getAudioInputStream(url);
            AudioFormat format = flujo.getFormat();
            DataLine.Info info;
            info = new DataLine.Info(Clip.class, format, (int) (flujo.getFrameLength() * format.getFrameSize()));
            reprod = (Clip) AudioSystem.getLine(info);
            reprod.open(flujo);
            reprod.start();

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
     * aqui reseteo los setBounds de la rama y muestro una animacion de una
     * pequeña exploción cuando desaparecen
     */
    public void resetearRama() {

        for (int j = 0; j < 2; j++) {
            if (ramaIzq[j].getY() >= 412) {
                golpe = true;
                boom.setBounds(90, 420, 80, 80);
                int x = rand.nextInt(450);
                ramaIzq[j].setBounds(-100, -x, 300, 70);
                sonidoExplota();
            }
            if (ramaDer[j].getY() >= 412) {
                golpe = true;
                boom.setBounds(300, 420, 80, 80);
                int x2 = rand.nextInt(700) + 400;
                ramaDer[j].setBounds(300, -x2, 300, 70);;
                sonidoExplota();
            }
            if (golpe) {
                boom.setVisible(true);
                // golpe = false;
            } else if (!golpe) {
                boom.setVisible(false);
            }

        }
    }
/**
 * en los eventos tengo el timer de la barra 
 * los listener del teclaro para mover el personaje 
 * las validaciones del mismo 
 * validacion de perder
 */
    public void eventos() {

        time = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloj++;
                if (reloj >= 15) {
                    if (i < 9) {
                        i++;
                        golpe = false;
                    }

                    reloj = 0;
                    barra.barras.get(i - 1).setVisible(false);
                    barra.barras.get(i).setVisible(true);
                    if (i == 9) {
                        JOptionPane.showMessageDialog(null, "Perdiste, intentalo de nuevo más tarde");
                        System.exit(0);
                    }
                    resetearRama();
                }
            }
        });
        time.start();
        ventana.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Point point = player.figura.get(0).getLocation();
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    sonidoGolpe();
                    player.figura.get(0).setBounds(-1000, 0, 100, 186);
                    player.figura.get(0).setVisible(false);
                    player.figura.get(1).setBounds(20, 480, 100, 115);
                    player.figura.get(2).setBounds(-1000, 480, 100, 115);
                    player.figura.get(3).setBounds(-1000, 480, 100, 115);
                    player.figura.get(1).setVisible(true);
                    player.figura.get(2).setVisible(false);
                    player.figura.get(3).setVisible(false);
                    fondo.setVisible(false);
                    fondo2.setVisible(true);
                    contTroncos++;
                    troncos.setText(String.valueOf(contTroncos));

                    for (int j = 0; j < 2; j++) {
                        ramaIzq[j].setLocation(ramaIzq[j].getX(), ramaIzq[j].getY() + 10);
                        ramaDer[j].setLocation(ramaDer[j].getX(), ramaDer[j].getY() + 10);

                    }

                    for (int j = 0; j < player.figura.size(); j++) {
                        for (int k = 0; k < 2; k++) {
                            if (ramaDer[k].getBounds().intersects(player.figura.get(j).getBounds()) || ramaIzq[k].getBounds().intersects(player.figura.get(j).getBounds())) {
                                System.out.println("murio");
                                int p = ramaIzq[k].getY();
                                System.out.println(p);
                                JOptionPane.showMessageDialog(null, "Perdiste, intentalo de nuevo más tarde");
                                System.exit(0);
                            }
                        }
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    sonidoGolpe();
                    player.figura.get(0).setBounds(-1000, 0, 100, 186);
                    player.figura.get(1).setBounds(-1000, 480, 100, 115);
                    player.figura.get(1).setVisible(false);
                    player.figura.get(0).setVisible(false);

                    player.figura.get(2).setBounds(-1000, 480, 100, 186);
                    player.figura.get(3).setBounds(300, 480, 100, 186);
                    player.figura.get(2).setVisible(false);
                    player.figura.get(3).setVisible(true);

                    fondo.setVisible(false);
                    fondo2.setVisible(true);
                    contTroncos++;
                    troncos.setText(String.valueOf(contTroncos));

                    for (int j = 0; j < 2; j++) {
                        ramaDer[j].setLocation(ramaDer[j].getX(), ramaDer[j].getY() + 10);
                        ramaIzq[j].setLocation(ramaIzq[j].getX(), ramaIzq[j].getY() + 10);
                    }

                    for (int j = 0; j < player.figura.size(); j++) {
                        for (int k = 0; k < 2; k++) {
                            if (ramaDer[k].getBounds().intersects(player.figura.get(j).getBounds()) || ramaIzq[k].getBounds().intersects(player.figura.get(j).getBounds())) {
                                System.out.println("murio");
                                JOptionPane.showMessageDialog(null, "Perdiste, intentalo de nuevo más tarde");
                                System.exit(0);
                            }
                        }
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.figura.get(0).setBounds(15, 480, 100, 186);
                    player.figura.get(0).setVisible(true);
                    player.figura.get(1).setBounds(-1000, 480, 100, 115);
                    player.figura.get(1).setVisible(false);
                    fondo.setVisible(true);
                    fondo2.setVisible(false);
                    player.figura.get(2).setBounds(-1000, 480, 100, 186);
                    player.figura.get(3).setBounds(-1000, 480, 100, 186);
                    player.figura.get(2).setVisible(false);
                    player.figura.get(3).setVisible(false);
                    if (i > 0) {
                        i--;
                    }
                    barra.barras.get(i).setVisible(true);
                    golpe = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                    player.figura.get(2).setBounds(350, 480, 100, 186);
                    player.figura.get(2).setVisible(true);
                    player.figura.get(3).setVisible(false);
                    player.figura.get(3).setBounds(-1000, 480, 100, 186);
                    player.figura.get(0).setBounds(-1000, 480, 100, 186);
                    player.figura.get(1).setBounds(-1000, 480, 100, 186);
                    player.figura.get(0).setVisible(false);
                    player.figura.get(1).setVisible(false);
                    if (i > 0) {
                        i--;
                    }
                    barra.barras.get(i).setVisible(true);
                    fondo.setVisible(true);
                    fondo2.setVisible(false);
                    golpe = false;
                }
            }

        });

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        saludo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream flujo = null;
                try {
                    URL url = getClass().getResource("audio/tripolar.wav");
                    flujo = AudioSystem.getAudioInputStream(url);
                    AudioFormat format = flujo.getFormat();
                    DataLine.Info info;
                    info = new DataLine.Info(Clip.class, format, (int) (flujo.getFrameLength() * format.getFrameSize()));
                    reprod = (Clip) AudioSystem.getLine(info);
                    reprod.open(flujo);
                    reprod.start();
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
        });
    }
}
