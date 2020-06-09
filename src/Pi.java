package pi;
//import java.util.concurrent.locks.*;

public class Pi {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        long t_inicial = System.currentTimeMillis();
        double val = PiVal.getPi();
        long t_final = System.currentTimeMillis();
        System.out.println("Valor aproximado: " + val);
        System.out.println("Valor verdadero de pi:" + Math.PI);
        System.out.println("Diferencia:" + Math.abs(Math.PI - val));
        System.out.println("Duración del programa:" + (t_final - t_inicial) + " milisegundos.");
    }
}
