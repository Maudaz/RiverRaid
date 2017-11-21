package river.tile;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import river.Handler;
import river.Id;

/**
 * Clase Abstracta y Padre de todos los Items de Terreno
 * @author MauricioDaza
 */
public abstract class Tile implements Serializable {

    public int x, y, width, height, vx, vy;
    public boolean solid;
    public Id id;
    public Handler handler;

    /**
     * Constructor Parametrico de la Clase Terrenos
     * @param x
     * @param y
     * @param width
     * @param height
     * @param solid
     * @param id
     * @param handler 
     */
    public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.handler = handler;
    }

    /**
     * Metodo Abstracto que dibuja los Items Terreno
     * @param g
     */
    public abstract void render(Graphics g);

    /**
     * Metodo Abstracto que actualiza los Items Terreno
     */
    public abstract void tick();

    /**
     * Metodo que Remueve o Mata los Items Terreno
     */
    public void die() {
        handler.removeTile(this);
    }

    /**
     * Metodo que obtiene los Limites
     * @return 
     */
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Metodo que obtiene los Limites de Arriba
     * @return 
     */
    public Rectangle getBoundsTop() {
        return new Rectangle(getX() + 10, getY(), getWidth() - 20, 5);
    }

    /**
     * Metodo que obtiene los Limites de Abajo
     * @return 
     */
    public Rectangle getBoundsBottom() {
        return new Rectangle(getX() + 10, getY() + getHeight() - 5, getWidth() - 20, 5);
    }

    /**
     * Metodo que obtiene la coordenada x
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * Metodo que fija la coordenada x
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Metodo que obtiene la coordenada y
     * @return 
     */
    public int getY() {
        return y;
    }

    /**
     * Metodo que fija la coordenada y
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metodo que obtiene el Ancho
     * @return 
     */
    public int getWidth() {
        return width;
    }

    /**
     * Metodo que fija el Ancho
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Metodo que obtiene el Alto
     * @return 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Metodo que fija el Alto
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Metodo que obtiene si es Solido o no
     * @return 
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * Metodo que fija si es Solido o no
     * @param solid 
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    /**
     * Metodo que obtiene la Velocidad en x
     * @return 
     */
    public int getVx() {
        return vx;
    }

    /**
     * Metodo que fija la Velocidad en x
     * @param vx 
     */
    public void setVx(int vx) {
        this.vx = vx;
    }

    /**
     * Metodo que obtiene la Velocidad en y
     * @return 
     */
    public int getVy() {
        return vy;
    }

    /**
     * Metodo que fija la Velocidad en y
     * @param vy 
     */
    public void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * Metodo que obtiene el Id
     * @return 
     */
    public Id getId() {
        return id;
    }

    /**
     * Metodo que fija el Id
     * @param id 
     */
    public void setId(Id id) {
        this.id = id;
    }
}
