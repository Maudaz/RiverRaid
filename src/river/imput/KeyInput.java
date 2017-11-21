package river.imput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import river.Game;
import static river.Game.handler;
import river.Id;
import river.entity.Entity;
import river.entity.mob.Bullet;
import river.entity.mob.Player;

/**
 * Clase que Maneja los Eventos de Teclado
 * @author MauricioDaza
 */
public class KeyInput implements KeyListener, Serializable {

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo que Recibe que Tecla se Presiona para mover el Jugador
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : Game.handler.entity) {
            if (en.getId() == Id.player) {
                switch (key) {
                    case KeyEvent.VK_SPACE:
                            if(Bullet.cntbll < 3){
                                Game.handler.addEntity(new Bullet(en.getX(),en.getY(),16,16,true,Id.bullet,handler));
                                Game.shoot.play();
                                Bullet.cntbll ++;
                            }
                        break;
                    case KeyEvent.VK_W:
                        en.setVy(-5);
                        Player.gascounter -= 20;
                        break;
                    case KeyEvent.VK_D:
                        en.setVx(1);
                        break;
                    case KeyEvent.VK_A:
                        en.setVx(-1);
                        break;
                }
            }
        }
    }

    /**
     * Metodo que Recibe cuando se suelta la tecla para detener al Jugador
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (Entity en : Game.handler.entity) {
            if (en.getId() == Id.player) {
                switch (key) {
                    case KeyEvent.VK_W:
                        en.setVy(-1);
                        break;
                    case KeyEvent.VK_D:
                        en.setVx(0);
                        break;
                    case KeyEvent.VK_A:
                        en.setVx(0);
                        break;
                }
            }
        }
    }
}
