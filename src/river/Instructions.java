package river;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 * Clase que genera las Instrucciones
 * @author MauricioDaza
 */
public class Instructions {
    
    static String[] instructions = new String[32];
    
    /**
     * Constructor que Inicializa y muestra las Instrucciones 
     */
    public Instructions(){
        read();
        String data = "";
        for (int i = 0; i < 31; i++) {
            data += instructions[i] + "\n";
        }
        JOptionPane.showMessageDialog(null,data);
    }
    
    /**
     * Metodo que lee el Archivo de Instrucciones 
     */
    public void read(){
        int i = 0;
        String arch = "instructions.dat", line;
        try{
            FileReader fr = new FileReader(arch);
            BufferedReader bf = new BufferedReader(fr);
            line = bf.readLine();
            while (line != null) {
                instructions[i] = line;
                line = bf.readLine();
                i++;
            }
            bf.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
    
}
