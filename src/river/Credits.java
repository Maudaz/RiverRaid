package river;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
* Clase que genera los Creditos
* @author MauricioDaza 
*/
public class Credits {
    
    static String[] credits = new String[13];
    
    /**
     * Constructor que Inicializa y muestra los Creditos 
     */
    public Credits(){
        read();
        String data = "";
        for (int i = 0; i < 12; i++) {
            data += credits[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, "Creditos\n\n" + data);
    }
    
    /**
     * Metodo que lee el Archivo de Creditos 
     */
    public void read(){
        int i = 0;
        String arch = "credits.dat", line;
        try{
            FileReader fr = new FileReader(arch);
            BufferedReader bf = new BufferedReader(fr);
            line = bf.readLine();
            while (line != null) {
                credits[i] = line;
                line = bf.readLine();
                i++;
            }
            bf.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
}
