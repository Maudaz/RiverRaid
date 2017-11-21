package river;

import java.io.Serializable;

/**
 * Clase que Guarda el Juego
 * @author MauricioDaza
 */
public class Save implements Serializable {

    private Handler handler;
    private int seconds, minutes;
    private int score, attemps, level, gascounter;
    private String name;

    /**
     * Constructor Parametrico de la Clase Guardar
     * @param handler
     * @param score
     * @param attemps
     * @param level
     * @param name
     * @param gascounter
     * @param seconds
     * @param minutes 
     */
    public Save(Handler handler, int score, int attemps, int level, String name, int gascounter, int seconds, int minutes) {
        this.handler = handler;
        this.score = score;
        this.attemps = attemps;
        this.level = level;
        this.name = name;
        this.gascounter = gascounter;
        this.seconds = seconds;
        this.minutes = minutes;
    }

    /**
     * Metodo que obtiene los Segundos
     * @return 
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Metodo que obtiene los Minutos
     * @return 
     */
    public int getMinutes() {
        return minutes;
    }
    
    /**
     * Metodo que obtiene el Combustible
     * @return 
     */
    public int getGascounter() {
        return gascounter;
    }

    /**
     * Metodo que obtiene el Controlador
     * @return 
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Metodo que obtiene el Puntaje
     * @return 
     */
    public int getScore() {
        return score;
    }

    /**
     * Metodo que obtiene la cantidad de Vidas
     * @return 
     */
    public int getAttemps() {
        return attemps;
    }

    /**
     * Metodo que obtiene el Nivel
     * @return 
     */
    public int getLevel() {
        return level;
    }

    /**
     * Metodo que obtiene el Nombre
     * @return 
     */
    public String getName() {
        return name;
    }
}
