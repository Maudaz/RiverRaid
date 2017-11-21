package river;

import java.io.Serializable;
import river.entity.Entity;

/**
 * Clase de la Camara
 * @author MauricioDaza
 */
public class Camera implements Serializable {

    public int x, y;

    /**
     * Metodo que actualiza la posicion de la Camara
     * @param player 
     */
    public void tick(Entity player) {
        setY(-player.getY() + Game.HEIGHT / 2);
    }
    
    /**
     * Metodo que obtiene la coordenada en x de la camara
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * Metodo que fija la coordenada en x de la camara
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Metodo que obtiene la coordenada en y de la camara
     * @return 
     */
    public int getY() {
        return y;
    }

    /**
     * Metodo que fija la coordenada en y de la camara
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

}
