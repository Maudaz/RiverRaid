package river.entity.mob;

import java.awt.Graphics;
import java.io.Serializable;
import river.Game;
import river.Handler;
import river.Id;
import river.entity.Entity;

/**
 * Clase de los Puentes
 * @author MauricioDaza
 */
public class Bridge extends Entity implements Serializable {

    /**
     * Constructor Parametrico de los Puentes
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Bridge(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

    /**
     * Metodo que dibuja los Puentes
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Game.bridge.getBufferedImage(), x, y, width, height, null);
    }

    /**
     * Metodo que actualiza los Puentes
     */
    public void tick() {
        
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity pl = handler.entity.get(i);
            if (pl.getId() == Id.player) {
                if (getBoundsTop().intersects(pl.getBounds())) {
                    Game.kill.play();
                    pl.die();
                    Player.gascounter=0;
                }
                if (getBoundsBottom().intersects(pl.getBounds())) {
                    Game.kill.play();
                    pl.die();
                    Player.gascounter=0;
                }
                if (getBoundsLeft().intersects(pl.getBounds())) {
                    Game.kill.play();
                    pl.die();
                    Player.gascounter=0;
                }
                if (getBoundsRight().intersects(pl.getBounds())) {
                    Game.kill.play();
                    pl.die();
                    Player.gascounter=0;
                }
            }
        }
        
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity bll = handler.entity.get(i);
            if(bll.getId() == Id.bullet){
                if(getBoundsBottom().intersects(bll.getBounds())) {
                    this.die();
                    bll.die();
                    Bullet.cntbll --;
                    Game.kill.play();
                    Game.score += 100;
                }
            }
        } 
    }
    
}
