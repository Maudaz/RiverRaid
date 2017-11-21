package river.graphics;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Clase Sprite
 * @author MauricioDaza
 */
public class Sprites implements Serializable {

    public SpriteSheet sheet;
    public BufferedImage image;

    /**
     * Constructor Parametrico de los Sprites
     * @param sheet
     * @param x
     * @param y 
     */
    public Sprites(SpriteSheet sheet, int x, int y) {
        image = sheet.getSprite(x, y);
    }

    /**
     * Metodo que obtiene el Sprite
     * @return 
     */
    public BufferedImage getBufferedImage() {
        return image;
    }
}
