package river.tile.objects;

import java.awt.Graphics;
import java.io.Serializable;
import river.Game;
import river.Handler;
import river.Id;
import river.tile.Tile;

/**
 * Clase de el Campo
 * @author Ethernal
 */
public class Grass extends Tile implements Serializable{

    /**
     * Constructor Parametrico del Campo
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Grass(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    /**
     * Metodo que dibuja el Campo
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Game.grass.getBufferedImage(), x, y, width, height, null);
    }

    /**
     * Metodo que actualiza el campo
     */
    public void tick() {
        
    }
    
}
