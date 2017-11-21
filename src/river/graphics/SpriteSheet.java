package river.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 * Clase Hoja de Sprites
 * @author MauricioDaza
 */
public class SpriteSheet implements Serializable {

    private BufferedImage sheet;

    /**
     * Constructor Parametrico de la Hoja de Sprites
     * @param path 
     */
    public SpriteSheet(String path) {
        try {
            sheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que obtiene el Sprite a dibujar
     * @param x
     * @param y
     * @return 
     */
    public BufferedImage getSprite(int x, int y) {
        return sheet.getSubimage(x * 32 - 32, y * 32 - 32, 32, 32);
    }
}
