package river.entity.mob;

import java.awt.Graphics;
import java.io.Serializable;
import river.Game;
import river.Handler;
import river.Id;
import river.entity.Entity;
import river.tile.Tile;

/**
 * Clase de las Balas
 * @author MauricioDaza
 */
public class Bullet extends Entity implements Serializable{

    public static int cntbll = 0;
    public int cntbl = 0;
    
    /**
     * Constructor Parametrico de las Balas
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Bullet(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        setVy(-2);
    }

    /**
     * Metodo que dibuja las Balas
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Game.bullt.getBufferedImage(), this.x, this.y, width, height, null);
    }

    /**
     * Metodo que actualiza las Balas
     */
    public void tick() {
        x += vx;
        y += vy;
        if (x <= 0) {
            x = 0;
        }
        if (y <= 0) {
            y = 0;
        }
        if (x + width >= 480) {
            x = 480 - width;
        }
        for(Tile ti : handler.tile){
            if(!ti.solid){
                break;
            }
            if(ti.getId() == Id.wall){
                if(getBounds().intersects(ti.getBounds())){
                    this.die();
                    cntbll --;
                }
            }
        }
    }
}
