<center><h1>
Universidad Nacional de Ingeniería
</center></h1s>
<center><h2>
Factultad de Ciencias
</center></h2>

![EscudoUNI](https://upload.wikimedia.org/wikipedia/commons/f/f7/Uni-logo_transparente_granate.png)

<center><h2>
CC462 - Sistemas Concurrentes y Distribuidos
</center></h2>

### Profesor:
Profesor Yuri Núñez

### Alumnos:
ALEGRE IBÁÑEZ, Víctor Augusto       20130504C

ZAVALETA BUENO, Romel Rolando       20120236F

ZEVALLOS LABARTHE, Enrique Martín   20130384H

<center><h2>
Junio 2020
</center></h2>

### Introducción.- Concurrencia

### Problema 1.- Calcular el número pi con la mayor cantidad de dígitos posible, utilizando hilos.

#### Planteamiento:
Se calcula pi mediante un algoritmo desarrollado en el siglo XVIII por el matemático inglés John Machin. Este algoritmo primero calcula pi/4 = 4*arctan(1/5) - arctan(1/239), y luego multiplica el resultado por 4 para obtener el valor de pi. (FRIESEN, 2015) [[1]](#1)
Para calcular el arco tangente de un ángulo en radianes, utilizamos series de Taylor, donde

```arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 + (x^9)/9...```

Para ejecutarlo concurrentemente, construimos un método llamado arctan que instancia `nHilos` hilos. Cada hilo calcula un subtotal mediante el objeto ArctanThread que ejecuta el cálculo de la serie de Taylor. Posteriormente, los hilos se juntan al llegar a la barrera `join()` y suman los subtotales. Finalmente, multiplicamos la suma por 4 y obtenemos el valor de pi.
Nótese que para obtener una cantidad mayor de decimales, hacemos uso de la clase `BigDecimal`.

#### Ejecución:
Al ejecutar el programa, se nos pide mediante la consola ingresar la cantidad de hilos a utilizar, y el número de dígitos que deseamos obtener. A continuación dos muestras, la primera ejecutando sólo un hilo y la segunda ejecutando 4. Mostramos primero lo que aparece en consola, y luego el archivo de texto creado, `valor_pi_..._.txt`:

##### Muestra 1:
```bash
(consola)
Determine cantidad de threads:1
Determine cantidad de digitos:50000
Pi impreso en: valor_pi_1592186968.txt
Duración del programa:4644 milisegundos.
(archivo_texto)
Tiempo: 4644milisegundos.
3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326 ... 256959688159205600101655256375679
```
##### Muestra 2:
```bash
(consola)
Determine cantidad de threads:4
Determine cantidad de digitos:50000
Pi impreso en: valor_pi_1592186951.txt
Duración del programa:1594 milisegundos.
(archivo_texto)
Tiempo: 1594milisegundos.
3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326...256959688159205600101655256375679
```

### Problema 2.- Implementar un método de ordenamiento utilizando hilos.

## Bibliografía:

<a id="1">[1]</a>: FRIESEN, J. (2015). Chapter 1: Performing More Advanced Thread Tasks - Joining Threads. In Java Threads and the Concurrency Utilities (pp. 12-15). Berkeley, CA: Apress.