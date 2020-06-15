package pi;
import java.io.FileWriter;
import java.math.BigDecimal;

public class Pi {
    public static void main(String[] args) throws Exception {
        int nHilos = Integer.parseInt(System.console().readLine("Determine cantidad de threads:"));
        int nDigitos = Integer.parseInt(System.console().readLine("Determine cantidad de digitos:"));
        
        JMMult valorPi = new JMMult();

        long t_inicial = System.currentTimeMillis();
        BigDecimal pi = valorPi.calculaPi(nHilos, nDigitos);
        long t_final = System.currentTimeMillis();
        printTextFile(pi, (t_final - t_inicial));
        System.out.println("Duración del programa:" + (t_final - t_inicial) + " milisegundos.");
    }

    public static void printTextFile(BigDecimal valor, long tiempo){
        String filename = "valor_pi_"+(System.currentTimeMillis()/1000)+".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Tiempo: " + tiempo + "milisegundos.\n");
            writer.append(valor.toString());
            writer.close();
            System.out.println("Pi impreso en: "+filename);
        } catch (Exception e) {
            System.out.println("An error occurred writing file");
            e.printStackTrace();
        }
        
    }
}
