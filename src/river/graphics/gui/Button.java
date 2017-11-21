package river.graphics.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import river.Credits;
import river.Game;
import river.Instructions;
import river.TopTen;

/**
 * Clase Botones del Menu
 * @author MauricioDaza
 */
public class Button implements Serializable {

    public int x, y, width, height;
    public String label, ruta;
    public Image button;
    public Random random = new Random();

    /**
     * Constructor Parametrico de la Clase Botones
     * @param x
     * @param y
     * @param width
     * @param height
     * @param label
     * @param ruta
     */
    public Button(int x, int y, int width, int height, String label, String ruta) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.ruta = ruta;
        button = new ImageIcon(getClass().getResource(ruta)).getImage();
    }

    /**
     * Metodo que dibuja los Botones en el Menu
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(button, x, y, null);
        g.setColor(Color.black);
        g.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 22));
        g.drawString(label, x + 4, y + 28);
    }

    /**
     * Metodo que Dibuja el Boton del Top10
     * @param g
     */
    public void renderTop(Graphics g) {
        g.drawImage(button, x, y, null);
        g.setColor(new Color(250, 210, 60));
        g.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 14));
        g.drawString(label, x + 16, y + 16);
    }

    /**
     * Metodo que maneja los Eventos de Click en los Botones
     */
    public void triggerEvent() {
        if (getLabel().toUpperCase().contains("EMPEZAR")) {
            do{
            Game.name = JOptionPane.showInputDialog(null, "Ingresa tu Nombre");
                Game.playing = false;
            }while(Game.name.equalsIgnoreCase("") || Game.name.length() > 8);
            Game.playing = true;
        } else if (getLabel().toUpperCase().contains("SALIR")) {
            if (JOptionPane.showConfirmDialog(null, "Desea Realmente Salir?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
                Game.playing = false;
            }
        } else if (getLabel().toUpperCase().contains("CARGAR")) {
            if (JOptionPane.showConfirmDialog(null, "Desea Cargar Partida?", "Cargar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Game.loadGame();
            }
        } else if (getLabel().toUpperCase().contains("TOP")) {
            new TopTen();
        }else if (getLabel().toUpperCase().contains("CREDITOS")) {
            new Credits();
        }else if (getLabel().toUpperCase().contains("COMO JUGAR")) {
            new Instructions();
        }
    }

    /**
     * Metodo que obtiene la coordenada x
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * Metodo que fija la coordenada x
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Metodo que obtiene la coordenada y
     * @return 
     */
    public int getY() {
        return y;
    }

    /**
     * Metodo que fija la coordenada y
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metodo que obtiene el Ancho
     * @return 
     */
    public int getWidth() {
        return width;
    }

    /**
     * Metodo que fija el Ancho
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Metodo que obtiene el Alto
     * @return 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Metodo que fija el Alto
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Metodo que obtiene el Texto
     * @return 
     */
    public String getLabel() {
        return label;
    }

    /**
     * Metodo que fija el Texto
     * @param label 
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Metodo que obtiene la Ruta
     * @return 
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Metodo que fija la Ruta
     * @param ruta 
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
