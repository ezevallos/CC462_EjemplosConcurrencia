package sort;

class Ordenador  extends Thread{
    
    private int[] internal;//check


    public int[] getInternal(){ //check
        return internal;
    }  

    //merger recursivo
    public void mergeSort(int[] arreglo){
        if(arreglo.length>1){
            int[] izquierda= mitad_izquierda(arreglo);
            
            int[] derecha  = mitad_derecha(arreglo);
            
            mergeSort(izquierda);
            mergeSort(derecha);
            merge(arreglo,izquierda,derecha);
        }
    }

    

    //sub arreglo izquierdo
    public int[] mitad_izquierda(int[] arreglo){
        int tam1 = arreglo.length/2; 
        int[] izquierda = new int[tam1];
        for( int i=0;i<tam1;i++){
            izquierda[i] = arreglo[i]; 
        } 
        return izquierda;
    }

    //sub arreglo derecho        
    public int[] mitad_derecha(int [] arreglo){
        int tam1=arreglo.length/2;
        int tam2=arreglo.length - tam1;
        int [] derecha = new int[tam2];

        for(int i=0;i<tam2;i++){
            derecha[i]=arreglo[i+tam1];
        }

        return derecha;
    }



    public void merge(int[] resultado, int[] izquierda, int[] derecha) {
        int i1 = 0;   
        int i2 = 0;   

        for (int i = 0; i < resultado.length; i++) {
            if (i2 >= derecha.length || (i1 < izquierda.length && izquierda[i1] <= derecha[i2])) {
                resultado[i] = izquierda[i1];   
                i1++;
            } else {
                resultado[i] = derecha[i2];   
                i2++;
            }
        }
    }
    //constructor
    Ordenador(int[] arr) {
        internal = arr;
    }

    //set la parte thread
    public void run() {
        mergeSort(internal);
    }
}