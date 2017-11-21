package river;

import java.applet.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import river.entity.Entity;
import river.entity.mob.Player;
import river.graphics.SpriteSheet;
import river.graphics.Sprites;
import river.graphics.gui.Launcher;
import river.imput.KeyInput;
import river.imput.MouseInput;
import river.tile.objects.End;

/**
 * Clase Principal del Juego
 * @author MauricioDaza
 */
public class Game extends Canvas implements Runnable, Serializable {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 640;
    public static final String TITLE = "RIVER RAID";

    public static boolean playing = false, win = false;
    public static int score = 0;
    public static int attemps = 3;
    public static String name = " ";
    public static int level = 0;

    private Thread thread;
    private static boolean isrunning = false;
    public static JFrame windows;
    private static BufferedImage[] levels = new BufferedImage[3];
    public static BufferedImage background;
    private static ImageIcon icon;
    private Random random = new Random();

    public static Handler handler;
    public static SpriteSheet sheet;
    public static Sprites [] boat = new Sprites [2], helicopter = new Sprites [2];
    public static Sprites gas, player, bridge, wall, grass,bullt,end;
    public static Camera cam;
    public static Launcher launcher;
    public static Clock timmer;
    public static MouseInput mouse;
    public static AudioClip mennu, kill, gammeover, button, gammewin, loseattemp, gass, shoot;

    /**
     * Constructor que Inicializa los atributos y ventanas
     */
    public Game() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        icon = new ImageIcon(Game.class.getResource("/resources/windows/icon.png"));
        windows = new JFrame(TITLE);
        windows.add(this);
        windows.pack();
        windows.setResizable(false);
        windows.setLocationRelativeTo(null);
        windows.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        windows.addWindowListener(new WindowsAdapter());
        windows.setIconImage(icon.getImage());
        windows.setVisible(true);
    }

    /**
     * Metodo que Inicializa los Sprites - Otras Imagenes - Sonido
     */
    private void init() {
        handler = new Handler();
        sheet = new SpriteSheet("/resources/textures/allSprites.png");
        player = new Sprites(sheet, 1, 1);
        gas = new Sprites(sheet, 2, 1);
        for (int i = 0; i < 2; i++) {
            helicopter[i] = new Sprites(sheet, 3, i+1);
            boat[i] = new Sprites(sheet, 4, i+1);
        }
        bridge = new Sprites(sheet, 5, 1);
        bullt = new Sprites(sheet, 6, 1);
        wall = new Sprites(sheet, 7, 1);
        grass = new Sprites(sheet, 8, 1);
        end = new Sprites(sheet, 9,1);
        
        try {
            levels[0] = ImageIO.read(getClass().getResource("/resources/textures/level-1.png"));
            levels[1] = ImageIO.read(getClass().getResource("/resources/textures/level-2.png"));
            levels[2] = ImageIO.read(getClass().getResource("/resources/textures/level-3.png"));
            background = ImageIO.read(getClass().getResource("/resources/textures/background.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        shoot = Applet.newAudioClip(getClass().getResource("/resources/sounds/shot.wav"));
        button = Applet.newAudioClip(getClass().getResource("/resources/sounds/buttons.wav"));
        gass = Applet.newAudioClip(getClass().getResource("/resources/sounds/gass.wav"));
        mennu = Applet.newAudioClip(getClass().getResource("/resources/sounds/menu.wav"));
        loseattemp = Applet.newAudioClip(getClass().getResource("/resources/sounds/loseattemp.wav"));
        gammewin = Applet.newAudioClip(getClass().getResource("/resources/sounds/gamewin.wav"));
        gammeover = Applet.newAudioClip(getClass().getResource("/resources/sounds/gameover.wav"));
        kill = Applet.newAudioClip(getClass().getResource("/resources/sounds/kill.wav"));
        
        level = random.nextInt(3);
        handler.createLevel(levels[level]);
        cam = new Camera();
        launcher = new Launcher();
        mouse = new MouseInput();
        addKeyListener(new KeyInput());
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        mennu.loop();
        timmer = new Clock();
        timmer.time.start();
    }

    /**
     * Metodo que Inicia el Hilo de los Graficos
     */
    private synchronized void start() {
        if (isrunning) {
            return;
        }
        isrunning = true;
        thread = new Thread(this, "Graphics");
        thread.start();
    }

    /**
     * Metodo que Detiene el Hilo de los Graficos
     */
    private synchronized void stop() {
        if (isrunning) {
            return;
        }
        isrunning = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que Mide los FPS y APS
     */
    public void run() {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        double nanoSeg = 1000000000 / 60;
        int frames = 0;
        int ticks = 0;

        while (isrunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeg;
            lastTime = now;
            while (delta >= 1) {
                tick();
                ticks++;
                delta--;
            }
            render();
            frames++;
            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                if(playing){
                windows.setTitle("|| "+ name + " || Puntos: " + score + " || Tiempo: " + timmer.min + ":" + timmer.sec + " || Vidas: " + attemps + " || Gas: " + Player.gascounter + "/" + Handler.ctgas + " ||");
                }
                if(!playing){
                windows.setTitle(TITLE + " || FPS: " + frames + " || APS: " + ticks);    
                }
                frames = ticks = 0;

            }
        }
        stop();
    }

    /**
     * Metodo que Dibuja el Menu y el Juego
     */
    public void render() {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = strategy.getDrawGraphics();
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        if (playing) {
            g.translate(cam.getX(), cam.getY());
            handler.render(g);
        } else if (!playing) {
            launcher.render(g);
        }
        g.dispose();
        strategy.show();
    }

    /**
     * Metodo que Actualiza el Juego y Advierte si Pierde Vidas o el Juego o Se Agota la Gasolina
     */
    public void tick() {
        if (playing) {
            handler.tick();
        }
        
        if (Player.cnt == 10){
            Player.gascounter -= 1;
            Player.cnt = 0;
        }
        
        for (Entity en : handler.entity) {
            if (en.getId() == Id.player) {
                cam.tick(en);
            }
        }

        if (Player.gascounter <= 0) {
            Player.gascounter = 200;
            score-=100;
            attemps -= 1;
            loseattemp.play();
            JOptionPane.showMessageDialog(null, "PERDISTE UNA VIDA...!");
            handler.clearLevel();
            handler.createLevel(levels[level]);
        }
        if (attemps == 0) {
            attemps = 3;
            gammeover.play();
            score = 0;
            playing = false;
            level = random.nextInt(3);
            JOptionPane.showMessageDialog(null, "PERDISTE TODAS TUS VIDAS...!");
            Player.gascounter = 200;
            handler.clearLevel();
            handler.createLevel(levels[level]);
            timmer.time.stop();
            timmer.sec = 0;
            timmer.min = 0;
            render();
        }
        
        if (timmer.min == 3 && timmer.sec == 0) {
            score = 0;
            win = true;
            attemps = 3;
            gammewin.play();
            JOptionPane.showMessageDialog(null,"PUNTAJE: "+score);
            JOptionPane.showMessageDialog(null,"FELICIDADES...! GANASTE");
            TopTen.read();
            Player.gascounter = 200;
            playing = false;
            level = random.nextInt(3);
            handler.clearLevel();
            handler.createLevel(levels[level]);
            timmer.time.stop();
            timmer.sec = 0;
            timmer.min = 0;
            render();
        }
        
        if(End.chlvl){
            score += 500;
            attemps += 1;
            Player.gascounter = 200;
            level = random.nextInt(3);
            handler.clearLevel();
            handler.createLevel(levels[level]);
        }
    }

    /**
     * Metodo que Retorna el Ancho del Frame
     * @return
     */
    public static int getFrameWidth() {
        return WIDTH;
    }

    /**
     * Metodo que Retorna el Alto del Frame
     * @return
     */
    public static int getFrameHeight() {
        return HEIGHT;
    }

    /**
     * Metodo que Guarda Partida
     */
    public static void saveGame() {
        try {
            File archSave = new File("save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archSave));
            Save sav = new Save(handler, score, attemps, level, name, Player.gascounter, timmer.sec, timmer.min);
            oos.writeObject(sav);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        timmer.time.stop();
    }

    /**
     * Metodo que Carga Partida
     * @return
     */
    public static boolean loadGame() {
        boolean load = false;
        try {
            File archLoad = new File("save.dat");
            if (!archLoad.exists()) {
                JOptionPane.showMessageDialog(null, "NO EXISTE ARCHIVO DE GUARDADO...!");
                return false;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archLoad));
            Save sav;
            sav = (Save) ois.readObject();
            handler = sav.getHandler();
            score = sav.getScore();
            attemps = sav.getAttemps();
            level = sav.getLevel();
            name = sav.getName();
            Player.gascounter = sav.getGascounter();
            timmer.sec = sav.getSeconds();
            timmer.min = sav.getMinutes();
            timmer.time.start();
            playing = true;
            load = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return load;
    }

    /**
     * Metodo MAIN
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    /**
     * Clase que maneja eventos de Windows
     */
    public static class WindowsAdapter implements WindowListener {

        public void windowOpened(WindowEvent e) {
        }

        /**
         * Metodo del Cierre por Defecto que Pregunta si se Desea Guardar Partida
         * @param e
         */
        public void windowClosing(WindowEvent e) {
            if (!playing) {
                if (JOptionPane.showConfirmDialog(null, "Desea Realmente Salir?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
            if (playing) {
                if (JOptionPane.showConfirmDialog(null, "Desea Guardar Partida Antes?", "Guardar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Game.isrunning = false;
                    saveGame();
                    JOptionPane.showMessageDialog(null, "GUARDADO CON EXITO...!");
                }
                System.exit(0);
            }
        }

        public void windowClosed(WindowEvent e) {
        }

        public void windowIconified(WindowEvent e) {
        }

        public void windowDeiconified(WindowEvent e) {
        }

        public void windowActivated(WindowEvent e) {
        }

        public void windowDeactivated(WindowEvent e) {
        }
    }
}
