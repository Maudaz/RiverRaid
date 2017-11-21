package river.imput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import river.Game;
import river.graphics.gui.Button;

/**
 * Clase que Maneja los Eventos de Mouse
 * @author MauricioDaza
 */
public class MouseInput implements MouseListener, MouseMotionListener, Serializable {

    public int x, y;

    public void mouseClicked(MouseEvent e) {
        
    }

    /**
     * Metodo que al Presionar el Click del Mouse activa las acciones de la Clase Botones
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < Game.launcher.buttons.length; i++) {
            Button button = Game.launcher.buttons[i];
            if (x >= button.getX() && y >= button.getY() && x <= button.getX() + button.getWidth() && y <= button.getY() + button.getHeight()) {
                Game.button.play();
                button.triggerEvent();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Metodo que Mueve el Puntero del Mouse en la Pantalla
     * @param e
     */
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

}
