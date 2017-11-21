package river.entity.mob;

import java.awt.Graphics;
import java.io.Serializable;
import river.Game;
import river.Handler;
import river.Id;
import river.entity.Entity;
import river.tile.Tile;

/**
 * Clase del Jugador
 * @author Ethernal
 */
public class Player extends Entity implements Serializable {
        
    public static int gascounter = 200;
    public static int cnt = 0;
    
    /**
     * Constructor Parametrico del Jugador
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        
        setVy(-1);
    }

    /**
     * Metodo que dibuja al Jugador
     * @param g 
     */
    public void render(Graphics g) {
        g.drawImage(Game.player.getBufferedImage(), x, y, width, height, null);
    }

    /**
     * Metodo que Actualiza al Jugador
     */
    public void tick() {
        x += vx;
        y += vy;
        cnt++;
        if (x <= 0) {
            x = 0;
        }
        if (y <= 0) {
            y = 0;
        }
        if (x + width >= 480) {
            x = 480 - width;
        }
        for (Tile ti : handler.tile) {
            if (!ti.solid) {
                break;
            }
            if (ti.getId() == Id.wall) {
                if (getBoundsTop().intersects(ti.getBounds())) {
                    Game.kill.play();
                    this.die();
                    gascounter = 0;
                }if (getBoundsBottom().intersects(ti.getBounds())) {
                    Game.kill.play();
                    this.die();
                    gascounter = 0;
                }if (getBoundsLeft().intersects(ti.getBounds())) {
                    Game.kill.play();
                    this.die();
                    gascounter = 0;
                }if (getBoundsRight().intersects(ti.getBounds())) {
                    Game.kill.play();
                    this.die();
                    gascounter = 0;
                }
            }
        }

        for (int i = 0; i < handler.entity.size(); i++) {
            Entity gs = handler.entity.get(i);
            if (gs.getId() == Id.gas) {
                if (getBounds().intersects(gs.getBounds())) {
                    gs.die();
                    Game.gass.play();
                    Game.score += 500;
                    if(gascounter < 200){
                        gascounter = 200;
                    }
                }
            }
        }
    }
}
