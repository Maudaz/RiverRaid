package river;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Clase que Genera el Top10
 * @author MauricioDaza
 */
public class TopTen{

    static String[][] top = new String[11][2];

    /**
     * Constructor de la Clase Top10 que muestra el Top
     */
    public TopTen() {
        read();
        String data = "";
        for (int i = 0; i < 10; i++) {
            data += top[i][0] + " " + top[i][1] + "\n";
        }
        JOptionPane.showMessageDialog(null, "Puntajes\n" + data);
    }

    /**
     * Metodo que Lee el Archivo, Ordena las Puntuaciones y Vuelve a Guardar el Archivo
     */
    public static void read() {
        int i = 0, after, before;
        String arch = "topten.dat", line;
        String[] aux;
        if (Game.win) {
            top[10][0] = Game.name;
            top[10][1] = String.valueOf(Game.score);
        }
        try {
            FileReader fr = new FileReader(arch);
            BufferedReader bf = new BufferedReader(fr);
            line = bf.readLine();
            while (line != null) {
                top[i] = line.split("-");
                line = bf.readLine();
                i++;
            }
            bf.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int j = 1; j < 11; j++) {
            for (int k = 0; k < 10; k++) {
                after = Integer.parseInt(top[k][1]);
                before = Integer.parseInt(top[k + 1][1]);
                if (after < before) {
                    aux = top[k];
                    top[k] = top[k + 1];
                    top[k + 1] = aux;
                }
            }
        }
        try {
            FileWriter fw = new FileWriter(arch);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(arch);
            for (int j = 0; j < top.length; j++) {
                pw.println(top[j][0] + "-" + top[j][1]);
            }
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
