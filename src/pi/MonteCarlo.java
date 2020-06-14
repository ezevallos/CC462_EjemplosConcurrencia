package pi;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarlo {
    AtomicInteger exitos, total;
    int lanzamientos;
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
                if(x*x + y*y< 1.0)
                    exitos.incrementAndGet();
                total.incrementAndGet();
            }
        }
    }

    public MonteCarlo (int i, int nHilos) {
        this.exitos = new AtomicInteger(0);
        this.total = new AtomicInteger(0);
        this.lanzamientos = i;
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
        System.out.println("Exitos: " + exitos.get()+" Totales: "+ total.get());
        return 4.0 * exitos.get() / total.get();
    }

}