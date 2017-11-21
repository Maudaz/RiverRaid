package river.graphics.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import river.Game;

/**
 * Clase Pantalla del Menu
 * @author MauricioDaza
 */
public class Launcher implements Serializable {

    public Button[] buttons;
    public Image background, trophy;

    /**
     * Constructor de la Clase Pantalla del Menu .. Inicializa los Botones
     */
    public Launcher() {
        buttons = new Button[6];
        buttons[0] = new Button(0, 420, 160, 40, "   EMPEZAR", "/resources/windows/button.png");
        buttons[1] = new Button(320, 420, 160, 40, "    CARGAR", "/resources/windows/button.png");
        buttons[2] = new Button(160, 510, 160, 40, "      SALIR", "/resources/windows/button.png");
        buttons[3] = new Button(400, 56, 80, 20, "TOP 10", "/resources/windows/buttontop.png");
        buttons[4] = new Button(0, 600, 160, 40, " COMO JUGAR", "/resources/windows/button.png");
        buttons[5] = new Button(320, 600, 160, 40, "   CREDITOS", "/resources/windows/button.png");
        background = new ImageIcon(getClass().getResource("/resources/windows/background.png")).getImage();
        trophy = new ImageIcon(getClass().getResource("/resources/windows/trophy.png")).getImage();
    }

    /**
     * Metodo que Dibuja el Fondo del Menu y los Botones de la Clase Botones
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(background, 0, 0, Game.getFrameWidth() + 25, Game.getFrameHeight() + 25, null);
        g.drawImage(trophy, 400, 8, 80, 60, null);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].render(g);
        }
        buttons[3].renderTop(g);
    }
}
