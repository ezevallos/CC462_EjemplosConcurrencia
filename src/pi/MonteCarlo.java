package pi;
//import java.util.concurrent.*;

public class MonteCarlo {
    int contador;
    int lanzamientos;
    double ValorPi;
    int nHilos;
    class MC implements Runnable {
        int m_lanzamientos;
        
        public MC(int lanzamientos) {
            this.m_lanzamientos = lanzamientos;
        }

        @Override
        public void run() {
            for (int i=1; i <= m_lanzamientos;i++){
                double x = Math.random();
                double y = Math.random();
                if (x * x + y * y <= 1)
                    contador++;
            }
        }
    }
    public MonteCarlo (int i, int nHilos) {
        this.contador = 0;
        this.lanzamientos = i;
        this.ValorPi = 0;
        this.nHilos = nHilos;
    }
    public double calculaPi(){
        Thread[] hilos = new Thread[nHilos];
        for(int i=0; i< nHilos; i++) {
            hilos[i] = new Thread(new MC(lanzamientos/nHilos));
            hilos[i].start();
        }
        try{
            for(int i=0;i<nHilos;i++){
                hilos[i].join();
            }
        }catch(Exception e){}
        System.out.println("Contador: " + contador);
        return 4.0 * contador / lanzamientos;
    }

}