package river.tile.objects;

import java.awt.Graphics;
import java.io.Serializable;
import river.Game;
import river.Handler;
import river.Id;
import river.tile.Tile;

/**
 * Clase del Muro
 * @author Ethernal
 */
public class Wall extends Tile implements Serializable{

    /**
     * Constructor Parametrico del Muro
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    /**
     * Metodo que dibja el Muro
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Game.wall.getBufferedImage(), x, y, width, height, null);
    }
    
    /**
     * Metodo que actualiza el Muro
     */
    public void tick() {

    }
    
}
