package TPO_desarrollo_parte2;

//Alumno: Osorio Mariano
import TPO_desarrollo_parte1.Obra;
import Utiles.TecladoIn;

public class obraTest2 {

    public static void main(String[] args) {

        int cantObras = 30;
        int pos;
        int opcion;
        int votoCantObra;
        int codigo;
        double mayorValor;
        char usarPre, continuar;
        boolean finalizar = false;
        boolean arregloOrdenado = false;

        Obra[] puestoDisponible = new Obra[cantObras];
        Obra[] enConcurso;

        for (int j = 0; j < cantObras; j++) {
            puestoDisponible[j] = new Obra(-(1 + j));
        }

        System.out.println("Desea utilizar la precarga con 10 obras ? s/n");
        usarPre = TecladoIn.readLineNonwhiteChar();

        if (usarPre == 's') {

            pos = precarga(puestoDisponible);

            System.out.println("Desea continuar con la carga? s/n");
            continuar = TecladoIn.readLineNonwhiteChar();

            if (continuar == 's') {
                cargarObras(puestoDisponible, pos);
            }

        } else {
            pos = 0;
            cargarObras(puestoDisponible, pos);
        }

        /*
        **En el arreglo enConcurso solamente estan las obras en concurso
        **a diferncia del arreglo puestos disponibles que contiene la 
        **cantidad maxima que pueden participar del concurso
         */
        if (puestoDisponible.length != cantDeObras(puestoDisponible)) {
            enConcurso = obrasEnConcurso(puestoDisponible);

        } else {
            enConcurso = puestoDisponible;
        }

        do {
            System.out.println("");
            menu();

            System.out.println("");
            System.out.println("* - Ingrese una opción: ");
            opcion = TecladoIn.readLineInt();

            switch (opcion) {
                case 1:

                    do {

                        System.out.println("** - Seleccione el método que desea utilizar: ");
                        System.out.println("     1 - BURBUJA MEJORADO");
                        System.out.println("     2 - SELECCIÓN");
                        System.out.println("     3 - INSERCIÓN");

                        opcion = TecladoIn.readLineInt();

                        switch (opcion) {
                            case 1:
                                burbujaMejorado(enConcurso);
                                break;
                            case 2:
                                seleccion(enConcurso);
                                break;
                            case 3:
                                insercion(enConcurso);
                                break;
                            default:
                                System.out.println("La opcion ingresa no es correcta. Por favor reintente el ingreso");
                        }
                    } while (opcion < 1 || opcion > 3);

                    System.out.println("** Obras ordenadas por código - - Revise su resultado en la opción 2 - - ");

                    arregloOrdenado = true;

                    break;

                case 2:
                    mostrarObras(enConcurso);
                    break;

                case 3:
                    if (!arregloOrdenado) {
                        seleccion(enConcurso);
                    }
                    do {
                        obrasSinDescalificar(enConcurso);

                        System.out.println("");
                        System.out.println("Ingrese el código que desea votar: ");
                        codigo = TecladoIn.readLineInt();

                        pos = busquedaBinaria(enConcurso, codigo);

                        if (pos == -1) {
                            System.out.println("");
                            System.out.println("El código " + codigo + ", NO participa en el concurso");
                            System.out.println("------------------------------------");
                        } else {
                            enConcurso[pos].votar();
                            System.out.println("---- Voto realizado correctamente ----");
                        }
                    } while (pos == -1);

                    break;

                case 4:
                    votoCantObra = menorA10Votos(enConcurso, enConcurso.length);

                    if (votoCantObra == 0) {
                        System.out.println("En el concurso no se encuentran obras con menos de 10 votos");
                    } else {
                        System.out.println("La cantidad de obra/s en concurso con menos de 10 votos es/son: " + votoCantObra);
                    }
                    System.out.println("************************************");
                    break;

                case 5:

                    mayorValor = mayorPrecio(enConcurso, enConcurso.length);

                    System.out.print("El mayor precio en el concurso es $");
                    System.out.println(mayorValor);

                    System.out.println("");
                    System.out.println("Obra/s con ese valor: ");
                    for (int i = 0; i < enConcurso.length; i++) {
                        if (enConcurso[i].getPrecio() == mayorValor && !enConcurso[i].getDescalificada()) {
                            System.out.println("** " + enConcurso[i].getTitulo());
                        }
                    }
                    break;

                case 6:
                    if (!arregloOrdenado) {
                        burbujaMejorado(enConcurso);
                    }
                    System.out.println("");
                    System.out.println("Ingrese el código que desea buscar: ");
                    codigo = TecladoIn.readLineInt();

                    pos = busquedaBinaria(enConcurso, codigo);

                    if (pos == -1) {
                        System.out.println("");
                        System.out.println("El código " + codigo + ", NO participa en el concurso");
                        System.out.println("------------------------------------");
                    } else {
                        System.out.println("");
                        System.out.println("+++++++++++++++++++++++++++++++++++");
                        System.out.println(enConcurso[pos].aCadena());
                        System.out.println("+++++++++++++++++++++++++++++++++++");
                    }
                    break;

                case 7:
                    quick(enConcurso, 0, enConcurso.length - 1);
                    System.out.println("- - - Orden por título exitoso - Para chequear resultado retome la opción 2 - - - ");
                    break;

                case 8:
                    finalizar = true;
                    break;

                default:
                    System.out.println("");
                    System.out.println("*** Opción incorrecta ****************************");
                    System.out.println("Por favor ingrese una de las siguientes opciones: ");

            }
        } while (!finalizar);

    }

    public static int precarga(Obra[] arregloCarga) {
        //Se pueden agregar precargas hasta un maximo de 30 valores
        //El valor cantPreC se debe actualizar a la cantidad de precargas

        int cantPreC;

        arregloCarga[0] = new Obra("Pablo Picasso", "Los tres músicos", 103, 18.3, 25.1);
        arregloCarga[1] = new Obra("Leonardo Da Vinci", "La Gioconda", 100, 77, 53);
        arregloCarga[2] = new Obra("Emilio Pettoruti", "Meditazione", 108, 70, 70);
        arregloCarga[3] = new Obra("Emilio Pettoruti", "Pensierosa", 109, 64, 49);
        arregloCarga[4] = new Obra("Joan Miró", "Mujer, pájaro, estrella", 104, 245, 170);
        arregloCarga[5] = new Obra("Antonio Berni", "La luna y su eco", 106, 73, 100);
        arregloCarga[6] = new Obra("Johannes Vermeer", "La joven de la perla", 105, 44, 39);
        arregloCarga[7] = new Obra("Vincent van Gogh", "La noche estrellada", 101, 74, 92);
        arregloCarga[8] = new Obra("Antonio Berni", "Desocupados", 107, 220, 300);
        arregloCarga[9] = new Obra("Vincent van Gogh", "Autorretrato", 102, 66, 54);

        cantPreC = 10;

        return cantPreC;
    }

    public static void cargarObras(Obra[] arreglo, int posicion) {
        /*La posicion del arreglo en el que comenzaran a realizarse los cambios dependerar de la variable posicion
          que ingresa como parametro*/
        /*En la repetitiva de carga de datos ademas se analizan las condiciones especiales que
          deben cumplir el dato codigo, largo y ancho para ser ingresos correctos*/
        //El usuario toma la decision si desea seguir cargando objetos al arreglo.

        String autor, titulo;
        int codigo, cantFilas;
        double largo, ancho;
        char respuesta;
        boolean continuar = true;

        cantFilas = arreglo.length;

        System.out.println("---********---");
        System.out.println("--- Como máximo podrá ingresar " + cantFilas + " obras de arte ---");

        do {
            System.out.println("OBRA NRO " + (posicion + 1) + ": ");
            System.out.println("Ingrese el Autor: ");
            autor = TecladoIn.readLine();
            System.out.println("Ingrese el título de la obra");
            titulo = TecladoIn.readLine();
            System.out.println("Ingrese el código asignado ");
            codigo = TecladoIn.readLineInt();

            do {

                if (codigo < 0) {
                    System.out.println("El código debe ser positivo. Debera reingresar el valor");
                    codigo = TecladoIn.readLineInt();
                }

                if (codigo >= 0 && posicion > 0 && codigoRepetido(arreglo, codigo)) {
                    System.out.println("El código ingresaso ya existe, debe reingresar un código válido: ");
                    codigo = TecladoIn.readLineInt();
                }

            } while (codigo < 0);

            System.out.println("Ingresar las medidas de la obra en centímetros---");
            System.out.println("  --- Largo:");
            largo = TecladoIn.readLineDouble();

            while (largo <= 0) {
                System.out.println("Debe ingresar un valor mayor a cero :");
                largo = TecladoIn.readLineDouble();
            }
            System.out.println("  --- Ancho:");
            ancho = TecladoIn.readLineDouble();

            while (ancho <= 0) {
                System.out.println("Debe ingresar un valor mayor a cero :");
                ancho = TecladoIn.readLineDouble();
            }

            arreglo[posicion] = new Obra(autor, titulo, codigo, largo, ancho);
            posicion++;

            if (posicion < cantFilas) {
                System.out.println("¿Desea ingresar otra obra de arte? s/n");
                respuesta = TecladoIn.readLineNonwhiteChar();
                if (respuesta == 'n' || respuesta == 'N') {
                    continuar = false;
                }
            } else {
                continuar = false;
            }
            System.out.println("");
        } while (continuar);
    }

    public static boolean codigoRepetido(Obra[] arreglo, int codigo) {
        //Comprueba si el un código ya se encuentra en la precarga del arreglo
        /* "carga" determina la cantidad de obras cargadas en el arreglo 
        *** al momento de su llamado y asi limitar el recorrido de busqueda*/
        //Retorna "repetido" (valor logico) si esta o no el codigo repetido.

        int carga, i = 0;

        carga = cantDeObras(arreglo);
        boolean repetido = false;

        while (i < carga && !repetido) {
            if (codigo == arreglo[i].getCodigo()) {
                repetido = true;
            }
            i++;
        }
        return repetido;
    }

    public static int cantDeObras(Obra[] arreglo) {
        //Determina la cantida de Obras cargadas encontrando la primer posicion vacia del arreglo
        //Si encuentra titulo igual a null detiene el recorrido y retorna la posicion

        int pos = 0, longitud;
        boolean encontrado = false;

        longitud = arreglo.length;

        while (pos < longitud && !encontrado) {
            if (arreglo[pos].getTitulo() == null) {
                encontrado = true;
            } else {
                pos++;
            }
        }
        return pos;
    }

    public static Obra[] obrasEnConcurso(Obra[] arregloCargado) {
        //Precondición la longitud de arregloCargado debe ser menor a lo asignado en el concurso.
        //Se declara un nuevo arreglo
        //Se crea en base a la cantidad de obras cargadas en arregloCargado
        //Se retorna el nuevo arreglo

        Obra[] arreglo;
        int longitud;

        longitud = cantDeObras(arregloCargado);
        arreglo = new Obra[longitud];

        for (int i = 0; i < longitud; i++) {
            arreglo[i] = arregloCargado[i];
        }
        return arreglo;
    }

    public static void menu() {
        /*menu informatido de las opciones que el usurio puede seleccionar. 
          Cada opcion va a llamar a un modulo x que cumple dicha seleccion*/

        System.out.println("------------------------------------ MENÚ ------- ");
        System.out.println("1 - Ordenar el ingreso de las obras de artes");
        System.out.println("2 - Ver las obras en concurso");
        System.out.println("3 - Votar una obra de arte");
        System.out.println("4 - Ver la cantidad de obras con menos de 10 votos");
        System.out.println("5 - Ver obras de arte con el mayor precio");
        System.out.println("6 - Buscar un código en específico dentro del concurso");
        System.out.println("7 - Ordenar las obras según el título");
        System.out.println("8 - Finalizar");

    }

    //-------------------------------------MOSTRAR VALORES DEL ARREGLO
    public static void mostrarObras(Obra[] arreglo) {
        //Realiza un recorrido dentro del arreglo
        //Imprime cada Obra (posicion del arreglo) mostrando solo codigo, titulo y autor de la misma

        int cantCol;
        int espacioAutor;
        int espacioTitulo;

        cantCol = arreglo.length;
        espacioAutor = autorLargo(arreglo) + 2;
        espacioTitulo = tituloLargo(arreglo) + 2;

        if (espacioAutor <= 5) {
            espacioAutor = 7;
        }
        if (espacioTitulo <= 6) {
            espacioTitulo = 8;
        }

        System.out.println(String.format("%" + 10 + "s" + "%" + espacioTitulo + "s" + "%" + espacioAutor + "s", "CÓDIGO", "TÍTULO", "AUTOR"));
        for (int i = 0; i < cantCol; i++) {
            System.out.print(String.format("%" + 10 + "d", arreglo[i].getCodigo()));
            System.out.print(String.format("%" + espacioTitulo + "s", arreglo[i].getTitulo()));
            System.out.print(String.format("%" + espacioAutor + "s", arreglo[i].getAutor()));
            System.out.println("");
        }
    }

    public static int autorLargo(Obra[] listaObra) {
        // Retorna la longitud del nombre de autor mas largo del arreglo

        int cantCol, nombreMayor = 0, autorLong;
        cantCol = listaObra.length;

        for (int j = 0; j < cantCol; j++) {
            autorLong = listaObra[j].getAutor().length();

            if (autorLong > nombreMayor) {
                nombreMayor = autorLong;
            }
        }
        return nombreMayor;
    }

    public static int tituloLargo(Obra[] listaObra) {
        //Retorna la longitud del titulo mas largo dentro del arreglo

        int cantCol, titMayor = 0, tituloLong;
        cantCol = listaObra.length;

        for (int j = 0; j < cantCol; j++) {
            tituloLong = listaObra[j].getTitulo().length();

            if (tituloLong > titMayor) {
                titMayor = tituloLong;
            }
        }
        return titMayor;
    }

    //-------------------------------------METODOS de ORDENAMIENTO
    public static void burbujaMejorado(Obra[] arreglo) {
        /*La estructura repetitiva más interna realiza un recorrido exhaustivo 
        comparando la posicion de un elemento con el elemento en la posicion siguiente,
        si cumple la condicion se realiza el intercambio de los elementos.
        La estructura externa contrala el corte del recorrido del arreglo.
        En caso de que este ordenado no generara nuevos intercambios.*/

        int i = 0, j, ultimaPos;
        boolean continuar;
        Obra aux;

        ultimaPos = arreglo.length - 1;

        do {
            continuar = false;

            for (j = 0; j < ultimaPos - i; j++) {

                if (arreglo[j + 1].getCodigo() < arreglo[j].getCodigo()) {

                    continuar = true;

                    aux = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = aux;

                }
            }
            i++;
        } while (i <= ultimaPos && continuar);
    }

    public static void seleccion(Obra[] arreglo) {
        /*Ordena el arreglo identificando el menor elemento almacenado [minimo] e 
        intercambiando a éste elemento con el de la primer posicion [i] del arreglo analizado
        Posterior a esto se reduce el tamaño del arreglo a ordenar y se procede
        de la misma manera hasta que finalmente el recorrido queda acotado en comparar 
        la ultima y anteultima posición*/

        int ultPos, anteUltPos, i, j, minimo;
        Obra aux;

        ultPos = arreglo.length - 1;
        anteUltPos = arreglo.length - 2;

        for (i = 0; i <= anteUltPos; i++) {

            minimo = i;

            for (j = i + 1; j <= ultPos; j++) {

                if (arreglo[j].getCodigo() < arreglo[minimo].getCodigo()) {
                    minimo = j;
                }
            }

            aux = arreglo[minimo];
            arreglo[minimo] = arreglo[i];
            arreglo[i] = aux;
        }

    }

    public static void insercion(Obra[] arreglo) {
        //Selecciona el elemento de la primer posición del arreglo y la toma como la parte ordenada del mismo.
        //Realiza la busqueda desde la parte ordenada +1 hasta la ultima posicion del arreglo
        /*Posiciona el elemento a ordenar segun corresponda dentro de la parte del arreglo ordenada 
          (para eso realiza un recorrido inverso hacia la primer posicion del arreglo y 
           utiliza una variable auxiliar que guarda el valor del elemento a ordenar)*/

        int p, j;
        Obra aux;

        for (p = 1; p < arreglo.length; p++) {

            aux = arreglo[p];
            j = p;

            while (j > 0 && aux.getCodigo() < arreglo[j - 1].getCodigo()) {
                arreglo[j] = arreglo[j - 1];
                j--;
            }
            arreglo[j] = aux;
        }

    }

    //---------------------------------VOTAR OBRAS EN CONCURSO
    public static void obrasSinDescalificar(Obra[] arreglo) {
        //Solo se muestran las obras que no estan descalificadas.
        //Con su respectivo código y título.

        int cantCol;
        int espacioTitulo;

        cantCol = arreglo.length;

        espacioTitulo = tituloLargo(arreglo) + 2;
        if (espacioTitulo <= 6) {
            espacioTitulo = 8;
        }

        System.out.println(String.format("%" + 10 + "s" + "%" + espacioTitulo + "s", "CÓDIGO", "TÍTULO"));
        for (int i = 0; i < cantCol; i++) {
            if (!arreglo[i].getDescalificada()) {
                System.out.print(String.format("%" + 10 + "d", arreglo[i].getCodigo()));
                System.out.print(String.format("%" + espacioTitulo + "s", arreglo[i].getTitulo()));
                System.out.println("");
            }
        }
    }

    //========= OBRAS con menos de 10 votos
    public static int menorA10Votos(Obra[] arreglo, int longitud) {
        /*Se recorre el arreglo desde la ultima a la primer posición de forma recursiva,
        armando el resultado a la vuelta del llamado recursivo
        Se toma como caso base cuando pos es menor a cero donde ya no quedan posiciones por recorrer
        y el modulo retorna cantObras = 0 */

        int pos;
        int cantObras = 0;

        pos = longitud - 1;

        if (pos >= 0) {

            cantObras = menorA10Votos(arreglo, pos);

            if (arreglo[pos].getVotos() < 10) {
                cantObras += 1;
            }
        }
        return cantObras;
    }

    //========= Obra con mayor precio de venta
    public static double mayorPrecio(Obra[] arreglo, int longitud) {
        /*Recorre el arreglo desde la ultima a la primer posición mediante recursividad.
        El resultado del arreglo se arma a la vuelta del llamado.
        Caso base cuando pos es cero donde a mayorValor se le asigna el precio del elemento en esa posición */

        double mayorValor;
        int pos;

        pos = longitud - 1;

        if (pos == 0) {
            mayorValor = arreglo[pos].getPrecio();

        } else {

            mayorValor = mayorPrecio(arreglo, pos);

            if (arreglo[pos].getPrecio() > mayorValor) {
                mayorValor = arreglo[pos].getPrecio();
            }
        }
        return mayorValor;
    }

    //========= Busqueda BINARIA de codigo X
    public static int busquedaBinaria(Obra[] arreglo, int cod) {
        /*Precondicion el arreglo ingresado por parametro debe estar ordenado.
        Mediante una estructura repetitiva se busca el código ingresado por parametro 
        dividiendo el arreglo por la mitad. Preguntando si cod es igual al elemento 
        en la mitad del arreglo para finalizar de iterar y en caso falso se procede 
        a continuar en la mitad inferior o superior segun corresponda*/

        int inicio = 0;
        int fin = arreglo.length - 1;
        int resultado = -1;
        int medio;

        while (inicio <= fin) {
            medio = (inicio + fin) / 2;

            if (cod == arreglo[medio].getCodigo()) {
                resultado = medio;
                inicio = fin + 1;
            } else {
                if (cod < arreglo[medio].getCodigo()) {
                    fin = medio - 1;
                } else {
                    inicio = medio + 1;
                }
            }
        }
        return resultado;
    }

    //========= Orden mediante QUICKsort
    public static void quick(Obra[] arreglo, int ini, int fin) {
        /*Mediante llamados recursivos y el uso de un pivote el método divide el arreglo en subarreglos
        Donde realiza nuevos llamados en si mismo para los nuevos arreglos en dos casos: 
        1 cuando los elementos son menores al pivote, tomando como limite ini , posPivote-1
        2 cuando los elementos son mayores al pivote, tomando como limite posPivote+1, fin.*/

        int posPivote;

        if (ini < fin) {

            posPivote = split(arreglo, ini, fin);

            quick(arreglo, ini, posPivote - 1);
            quick(arreglo, posPivote + 1, fin);
        }
    }

    public static int split(Obra[] arreglo, int izquierda, int derecha) {
        /*El método split declara el pivote como el primer elemento del arreglo,
        luego mediante los indice izquierda y derecha recorre el arreglo para ordenar
        todos los elementos menores o iguales al pivote a su izquierda y los mayores a su derecha
        Deja de iterar cuando los indices se cruzan y como último paso intercambia el pivote 
        con el elemento ubicado en la posición derecha, finalmente retorna la nueva posicion del pivote [derecha]*/
        int pivote;
        Obra aux;

        pivote = izquierda;

        while (izquierda <= derecha) {

            while (izquierda < arreglo.length
                    && ((arreglo[izquierda].getTitulo().compareToIgnoreCase(arreglo[pivote].getTitulo()) < 0)
                    || (arreglo[izquierda].getTitulo().compareToIgnoreCase(arreglo[pivote].getTitulo()) == 0))) {
                izquierda++;
            }

            while ((arreglo[derecha].getTitulo().compareToIgnoreCase(arreglo[pivote].getTitulo()) > 0)) {
                derecha--;
            }

            if (izquierda < derecha) {
                aux = arreglo[izquierda];
                arreglo[izquierda] = arreglo[derecha];
                arreglo[derecha] = aux;
            }

        }
        aux = arreglo[pivote];
        arreglo[pivote] = arreglo[derecha];
        arreglo[derecha] = aux;

        return derecha;
    }

}
