package river.tile.objects;

import java.awt.Graphics;
import java.io.Serializable;
import river.Game;
import river.Handler;
import river.Id;
import river.entity.Entity;
import river.tile.Tile;

/**
 * Clase Final del Mapa
 * @author MauricioDaza
 */
public class End extends Tile implements Serializable{

    public static boolean chlvl = false;
    /**
     * Constructor Parametrico de la Clase Final del Mapa
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public End(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    /**
     * Metodo que dibuja el Final del Mapa
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Game.end.getBufferedImage(), x, y, width, height, null);
    }

    /**
     * Metodo que actualiza el Final del Mapa
     */
    public void tick() {
        for(Entity en : handler.entity){
            if(!en.solid){
                if(en.getId() == Id.player){
                    if(en.getBounds().intersects(getBounds())){
                        chlvl = true;
                    }
                }
            }
        }
    }
    
}
