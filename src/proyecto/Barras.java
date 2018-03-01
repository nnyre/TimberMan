/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Renny Ramirez
 * este es la clase barras
 * aca creo las barras de energia del personaje
 */
public class Barras {

    ArrayList<JLabel> barras = new ArrayList<>();
/**
 * contructor de la clase
 * doy memoria y ubicacion al vector de barras
 */
    public Barras() {
        String[] ruta = {"barra1.png", "barra2.png", "barra3.png", "barra4.png", "barra5.png", "barra6.png", "barra7.png", "barra8.png", "barra9.png", "barra10.png"};
        for (int i = 0; i < ruta.length; i++) { /** aca creo el vector de barras*/
            barras.add(new JLabel(new ImageIcon(getClass().getResource("barras/"+ruta[i]))));
            barras.get(i).setBounds(130, 650, 200, 56);
        }
        
    }

}
