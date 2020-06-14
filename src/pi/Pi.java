package pi;
//import java.util.concurrent.locks.*;
import java.math.BigDecimal;

public class Pi {
    public static void main(String[] args) throws Exception {
        int nHilos = Integer.parseInt(System.console().readLine("Determine cantidad de threads:"));
        //int nLanzamientos = Integer.parseInt(System.console().readLine("Determine cantidad de lanzamientos:"));
        //MonteCarlo ValorPi = new MonteCarlo(nLanzamientos, nHilos);

        JohnMachin ValorPi = new JohnMachin(nHilos);

        long t_inicial = System.currentTimeMillis();
        double val = ValorPi.calculaPi();
        BigDecimal pi = calculaPi();
        long t_final = System.currentTimeMillis();
        System.out.println("Valor aproximado: " + val);
        System.out.println("Valor verdadero de pi:" + Math.PI);
        System.out.println("Diferencia:" + Math.abs(Math.PI - val));
        System.out.println("Duración del programa:" + (t_final - t_inicial) + " milisegundos.");
    }
}
