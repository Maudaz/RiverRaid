package river.entity.mob;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import river.Game;
import river.Handler;
import river.Id;
import river.entity.Entity;
import river.tile.Tile;

/**
 * Clase de los Botes
 * @author MauricioDaza
 */
public class Boat extends Entity implements Serializable {
    
    private Random random = new Random();
    private int facing = 0;
    
    /**
     * Constructor Parametrico de los Botes
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Boat(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        
        int direction = random.nextInt(2);
        switch (direction) {
            case 0:
                setVx(-1);
                facing = 0;
                break;
            case 1:
                setVx(1);
                facing = 1;
                break;
        }
    }

    /**
     * Metodo que dibuja los Botes
     * @param g 
     */
    public void render(Graphics g) {
        if(facing == 0){
            g.drawImage(Game.boat[1].getBufferedImage(), x, y, width, height, null);
        }
        if(facing == 1){
            g.drawImage(Game.boat[0].getBufferedImage(), x, y, width, height, null);
        }
    }

    /**
     * Metodo que actualiza los Botes
     */
    public void tick() {
        x += vx;
        y += vy;
        if (y <= 0) {
            y = 0;
        }
        if (x + width >= 480) {
            x = 480 - width;
        }
        for (Tile ti : handler.tile) {
            if (ti.isSolid()) {
                if (getBoundsLeft().intersects(ti.getBounds())) {
                    setVx(1);
                    facing = 1;
                }
                if (getBoundsRight().intersects(ti.getBounds())) {
                    setVx(-1);
                    facing = 0;
                }
            }
        }
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity pl = handler.entity.get(i);
            if (pl.getId() == Id.player) {
                if (getBoundsTop().intersects(pl.getBounds())) {
                    Game.kill.play();
                    setVx(1);
                    Player.gascounter=0;
                }
                if (getBoundsBottom().intersects(pl.getBounds())) {
                    Game.kill.play();
                    setVx(1);
                    Player.gascounter=0;
                }
                if (getBoundsLeft().intersects(pl.getBounds())) {
                    Game.kill.play();
                    setVx(1);
                    Player.gascounter=0;
                }
                if (getBoundsRight().intersects(pl.getBounds())) {
                    Game.kill.play();
                    setVx(-1);
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
