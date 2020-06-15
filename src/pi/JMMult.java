package pi;

import java.math.BigDecimal;

public class JMMult {
    private static final BigDecimal CUATRO = BigDecimal.valueOf(4);
    private static final int modoRedondeo = BigDecimal.ROUND_HALF_EVEN;

    public JMMult(){}

    /**
     * Calcula Pi por el metodo de JohnMachin
     * pi/4 = 4*arctan(1/5)-arctan(1/239)
     * @param nHilos cantidad de hilos a usar
     * @param digitos cantidad de digitos de Pi
     * @return valor de Pi
     */
    public BigDecimal calculaPi(int nHilos, int digitos){
        int escala = digitos + 5;
        BigDecimal arctan1_5 = arctan(5, nHilos,escala);
        BigDecimal arctan1_239 = arctan(239, nHilos,escala);
        BigDecimal pi = arctan1_5.multiply(CUATRO).
                        subtract(arctan1_239).multiply(CUATRO);
        return pi.setScale(digitos, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Calcula Arctan(1/x) en paralelo
     * @param inversoX el valor de x, se invertira
     * @param nHilos cantidad total de hilos
     * @param escala cantidad de digitos a la derecha de la coma decimal
     * @return arctan(1/x)
     */
    public BigDecimal arctan(int inversoX, int nHilos, int escala){
        BigDecimal resultado = BigDecimal.ZERO;
        ArctanThread hilos[] = new ArctanThread[nHilos];
        //Se divide el trabajo en n hilos
        for(int i= 0; i<nHilos;i++){
            hilos[i] = new ArctanThread(inversoX, i, nHilos, escala);
            hilos[i].start();
        }
        try{
            for(int i=0; i<nHilos;i++)
                hilos[i].join();    //Barrera, espera que los demas acaben
        }catch(Exception e){}
        for(int i=0;i<nHilos;i++){
            BigDecimal subTotal = hilos[i].getSubTotal();
            resultado = resultado.add(subTotal);  //Suma los subtotales de cada hilo
        }
        return resultado;   //El valor calculado
    }

    /**
     * Hilo que calcula subtotales de arctan con serie de taylor
     */
    public class ArctanThread extends Thread{
        private BigDecimal subTotal;    //Subtotal de serie de taylor
        private int inversoX, indHilo, nHilos, escala;

        public ArctanThread(int inversoX, int indHilo,int nHilos, int escala){
            subTotal = BigDecimal.ZERO;
            this.inversoX = inversoX;
            this.indHilo = indHilo;
            this.nHilos = nHilos;
            this.escala = escala;
        }

        public BigDecimal getSubTotal(){
            return subTotal;
        }

        @Override
        public void run(){
            BigDecimal numero, termino;
            BigDecimal invXi = BigDecimal.valueOf(inversoX).pow(2*indHilo+1);
            BigDecimal invXn = BigDecimal.valueOf(inversoX).pow(2*nHilos);
            numero = BigDecimal.ZERO;
            int i = indHilo;
            do{
                if(i == indHilo){
                    numero = BigDecimal.ONE.divide(invXi, escala, modoRedondeo);
                }else{
                    numero = numero.divide(invXn, escala, modoRedondeo);
                }
                int denominador = 2 * i + 1;
                termino = numero.divide(BigDecimal.valueOf(denominador), escala, modoRedondeo);
                if ((i % 2) != 0)
                    subTotal = subTotal.subtract(termino);
                else
                    subTotal = subTotal.add(termino);
                i+=nHilos;
            }while(termino.compareTo(BigDecimal.ZERO) != 0);
        }
        
    }
    
}