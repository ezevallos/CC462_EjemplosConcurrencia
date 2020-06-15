package pi;
import java.io.FileWriter;
//import java.util.concurrent.locks.*;
import java.math.BigDecimal;

public class Pi {
    public static void main(String[] args) throws Exception {
        int nHilos = Integer.parseInt(System.console().readLine("Determine cantidad de threads:"));
        int nDigitos = Integer.parseInt(System.console().readLine("Determine cantidad de digitos:"));
        //MonteCarlo ValorPi = new MonteCarlo(nLanzamientos, nHilos);

        //JohnMachin ValorPi = new JohnMachin(0,0);
        JMMult valorPi = new JMMult();

        long t_inicial = System.currentTimeMillis();
        BigDecimal pi = valorPi.calculaPi(nHilos, nDigitos);
        long t_final = System.currentTimeMillis();
        printTextFile(pi);
        System.out.println("Valor verdadero de pi:" + Math.PI);
        //System.out.println("Diferencia:" + Math.abs(Math.PI - val));
        System.out.println("Duración del programa:" + (t_final - t_inicial) + " milisegundos.");
    }

    public static void printTextFile(BigDecimal valor){
        String filename = "valor_pi_"+(System.currentTimeMillis()/1000)+".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(valor.toString());
            writer.close();
            System.out.println("Pi impreso en: "+filename);
        } catch (Exception e) {
            System.out.println("An error occurred writing file");
            e.printStackTrace();
        }
        
    }
}
