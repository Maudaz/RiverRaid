package river;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.Timer;

/**
 * Clase del Reloj
 * @author MauricioDaza
 */
public class Clock implements Serializable {

    public Timer time;
    public int sec, min;

    /**
     * Constructor que inicializa los atributos y el timer
     */
    public Clock() {
        Seconds cont = new Seconds();
        sec = 0;
        min = 0;
        time = new Timer(1000, cont);
    }

    /**
     * Clase que hace funcionar el Reloj
     * @author MauricioDaza 
     */
    class Seconds implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Game.playing) {
                sec++;
            }
            if (sec == 60) {
                min++;
                sec = 0;
            }
        }
    }
}
