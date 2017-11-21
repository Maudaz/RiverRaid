package river;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;
import river.entity.Entity;
import river.entity.mob.Player;
import river.entity.mob.Helicopter;
import river.entity.mob.Gas;
import river.entity.mob.Boat;
import river.entity.mob.Bridge;
import river.tile.Tile;
import river.tile.objects.End;
import river.tile.objects.Grass;
import river.tile.objects.Wall;

/**
 * Clase que Crea y Maneja los Items
 */
public class Handler implements Serializable {

    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public LinkedList<Tile> tile = new LinkedList<Tile>();
    public static int ctgas = 200;
    public int ctbll = 0;

    /**
     * Metodo que Dibuja los Items
     * @param g
     */
    public void render(Graphics g) {
        for (Entity en : entity) {
            en.render(g);
        }
        for (Tile ti : tile) {
            ti.render(g);
        }
    }

    /**
     * Metodo que Actualiza los Items
     */
    public void tick() {
        for (int i = 0; i < entity.size(); i++) {
            entity.get(i).tick();
        }
        for (int i = 0; i < tile.size(); i++) {
            tile.get(i).tick();
        }
    }

    /**
     * Metodo que Agrega un Item de tipo Entidad
     * @param en
     */
    public void addEntity(Entity en) {
        entity.add(en);
    }

    /**
     * Metodo que Remueve un Item de tipo Entidad
     * @param en
     */
    public void removeEntity(Entity en) {
        entity.remove(en);
    }

    /**
     * Metodo que Agrega un Item de tipo Terreno
     * @param ti
     */
    public void addTile(Tile ti) {
        tile.add(ti);
    }

    /**
     * Metodo que Remueve un Item de tipo Terreno
     * @param ti
     */
    public void removeTile(Tile ti) {
        tile.remove(ti);
    }
    
    /**
     * Metodo que Genera los Mapas de los Niveles usando Pixeles
     * @param level
     */
    public void createLevel(BufferedImage level) {
        int width = level.getWidth();
        int height = level.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = level.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                if (red == 0 && green == 0 && blue == 0) {
                    addTile(new Wall(x * 32, y * 32, 32, 32, true, Id.wall, this));
                }
                if (red == 0 && green == 0 && blue == 255) {
                    addEntity(new Player(x * 32, y * 32, 32, 32, false, Id.player, this));
                }
                if (red == 255 && green == 255 && blue == 0) {
                    addEntity(new Gas(x * 32, y * 32, 32, 32, true, Id.gas, this));
                }
                if (red == 20 && green == 120 && blue == 80) {
                    addEntity(new Helicopter(x * 32, y * 32, 32, 32, true, Id.helicopter, this));
                }
                if (red == 120 && green == 70 && blue == 30) {
                    addEntity(new Boat(x * 32, y * 32, 32, 32, true, Id.boat, this));
                }
                if (red == 150 && green == 0 && blue == 150) {
                    addEntity(new Bridge(x * 32, y * 32, 32*3, 32, true, Id.bridge, this));
                }
                if (red == 0 && green == 20 && blue == 0) {
                    addTile(new Grass(x * 32, y * 32, 32, 32, true, Id.grass, this));
                }
                if (red == 150 && green == 120 && blue == 100) {
                    addTile(new End(x * 32, y * 32, 32*3, 32, true, Id.end, this));
                }
            }
        }
    }

    /**
     * Metodo que limpia el nivel
     */
    public void clearLevel() {
        ctgas=200;
        entity.clear();
        tile.clear();
    }
}
