package pi;
import java.math.BigDecimal;

public class JohnMachin  {
    // constante utilizada en el cálculo de PI
    private static final BigDecimal CUATRO = BigDecimal.valueOf(4);

    // modo de redondeo utilizado al calcular PI
    private static final int modoRedondeo = BigDecimal.ROUND_HALF_EVEN;
    private static BigDecimal result;
    private int digits, nHilos;

    public JohnMachin(int nHilos, int digits){
        this.nHilos = nHilos;
        this.digits = digits;
    }

    public static BigDecimal calculaPi(int digits){
        int escala = digits + 5;
        BigDecimal acrtan1_5 = arctan(5, escala);
        BigDecimal arctan1_239 = arctan(239, escala);
        BigDecimal pi = arctan1_5.multiply(CUATRO).
                        subtract(arctan1_239).multiply(CUATRO);
        return pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal arctan (int inversoX, int escala){
        BigDecimal resultado, numero, termino;
        BigDecimal invX = BigDecimal.valueOf(inversoX);
        BigDecimal invX2 = BigDecimal.valueOf(inversoX * inversoX);
        numero = BigDecimal.ONE.divide(invX, escala, modoRedondeo);
        resultado = numero;
        int i = 1;
        do{
            numero = numero.divide(invX2, escala, modoRedondeo);
            int denominador = 2 * i + 1;
            termino = numero.divide(BigDecimal.valueOf(denominador), escala, modoRedondeo);
            if ((i % 2) != 0)
                resultado = resultado.subtract(termino);
            else
                resultado = resultado.add(termino);
            i++;
        }while(termino.compareTo(BigDecimal.ZERO) != 0);
        return resultado;
    }
}