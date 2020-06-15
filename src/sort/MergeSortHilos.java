package sort;

import java.util.Random; //biblioteca para implementar Array a ordenar 


public class MergeSortHilos {


    public static void main(String[] args) throws InterruptedException{
        Random aleatorio = new Random();

        int[] arreglo_sin_ordenar = new int[50];
        
        //generar aleatoriamente el arreglo a ordenar
        for(int i=0;i<arreglo_sin_ordenar.length;i++){
            //System.out.println("entro");
            arreglo_sin_ordenar[i]=aleatorio.nextInt(1000);
        }

        //System.out.println(arreglo_sin_ordenar[0]);
        long inicio_tiempo=System.currentTimeMillis();
        //division del arreglo en dos
        int[] subArr1 = new int[arreglo_sin_ordenar.length/2]; 
        int[] subArr2 = new int[arreglo_sin_ordenar.length - arreglo_sin_ordenar.length/2];
        System.arraycopy(arreglo_sin_ordenar, 0, subArr1, 0, arreglo_sin_ordenar.length/2);
        System.arraycopy(arreglo_sin_ordenar, arreglo_sin_ordenar.length/2, subArr2, 0, 
        arreglo_sin_ordenar.length - arreglo_sin_ordenar.length/2);

        //Hilos 
        Ordenador hilo1 = new Ordenador(subArr1);
        Ordenador hilo2 = new Ordenador(subArr2);

        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();



        int [] ordenado=finalMerge (hilo1.getInternal(), hilo2.getInternal());

        System.out.println("Lista ordenada");

        for (int j=0;j<ordenado.length;j++){
            System.out.println(ordenado[j]);
        }

        long fin_tiempo = System.currentTimeMillis();
        long duracion = fin_tiempo - inicio_tiempo;
        System.out.println("El orden nos toma: " + (float)duracion/1000 + " segundos");

        /*
        for(int i=0;i<arreglo_sin_ordenar.length;i++){
            //System.out.println("entro");
            arreglo_sin_ordenar[i]=aleatorio.nextInt(1000);
        }*/

    }


    public static int[] finalMerge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i=0; 
        int j=0; 
        int r=0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[r]=a[i];
                i++;
                r++;
            } else {
                result[r]=b[j];
                j++;
                r++;
            }
            if (i==a.length) {
                while (j<b.length) {
                    result[r]=b[j];
                    r++;
                    j++;
                }
            }
            if (j==b.length) {
                while (i<a.length) {
                    result[r]=a[i];
                    r++;
                    i++;
                }
            }
        }
        
        return result;
    }
    
}