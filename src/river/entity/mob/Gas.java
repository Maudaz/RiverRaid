package river.entity.mob;

import java.awt.Graphics;
import java.io.Serializable;
import river.Game;
import river.Handler;
import river.Id;
import river.entity.Entity;

/**
 * Clase del Combustible
 * @author MauricioDaza
 */
public class Gas extends Entity implements Serializable {

    /**
     * Constructor Parametrico del Combustible
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Gas(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }
    
    /**
     * Metodo que dibuja el Combustible
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Game.gas.getBufferedImage(), x, y, width, height, null);
    }
    
    /**
     * Metodo que actualiza el Combustible
     */
    public void tick() {
        
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity bll = handler.entity.get(i);
            if(bll.getId() == Id.bullet){
                if(getBoundsBottom().intersects(bll.getBounds())) {
                    this.die();
                    bll.die();
                    Bullet.cntbll --;
                    Game.kill.play();
                    Game.score -= 200;
                }
            }
        }
        
    }
    
}
