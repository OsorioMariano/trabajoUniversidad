package TPO_desarrollo_parte1;

// Alumno: Osorio Mariano

import Utiles.TecladoIn;

public class testObra {

    public static void main(String[] args) {

        String autor;
        int cantObras = 30;
        int pos;
        int opcion;
        char usarPre, continuar;
        boolean finalizar = false, nombreCorrecto;

        Obra[] puestoDisponible = new Obra[cantObras];
        Obra[] enConcurso;

        for (int j = 0; j < cantObras; j++) {
            puestoDisponible[j] = new Obra();
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
        **a diferncia del arreglo puestos disponibles que contiene la cantidad maxima
         */
        if (puestoDisponible.length != cantDeObras(puestoDisponible)) {
            enConcurso = obrasEnConcurso(puestoDisponible);
        } else {
            enConcurso = puestoDisponible;
        }

        do {
            System.out.println("");
            menu();

            System.out.println("********");
            System.out.println("Ingrese una opción: ");
            opcion = TecladoIn.readLineInt();

            switch (opcion) {
                case 1:
                    mostrarObras(enConcurso);

                    break;

                case 2:
                    obrasSinDescalificar(enConcurso);

                    enConcurso[obraElegida(enConcurso)].votar();

                    break;

                case 3:

                    obrasSinDescalificar(enConcurso);

                    enConcurso[obraElegida(enConcurso)].descalificar();

                    break;

                case 4:
                    System.out.println("Los autores que participan el en concurso son: ");
                    System.out.println(mostrarAutores(enConcurso));

                    do {
                        nombreCorrecto = true;

                        System.out.println("Ingrese el nombre del autor: ");
                        autor = TecladoIn.readLine();

                        if (!mostrarAutores(enConcurso).contains(autor)) {
                            nombreCorrecto = false;

                            System.out.println("El autor no es correcto.- Debe repetir el ingreso----");
                        }
                    } while (!nombreCorrecto);

                    System.out.println("");
                    obrasDeAutor(enConcurso, autor);

                    break;

                case 5:
                    System.out.print("El mayor precio el en concurso es de $");
                    System.out.println(mayorPrecio(enConcurso));

                    System.out.println("");
                    System.out.println("Obra/s con ese valor: ");
                    for (int i = 0; i < enConcurso.length; i++) {
                        if (enConcurso[i].getPrecio() == mayorPrecio(enConcurso) && !enConcurso[i].getDescalificada()) {
                            System.out.println("** " + enConcurso[i].getTitulo());
                        }
                    }
                    break;

                case 6:
                    ganadores(enConcurso);
                    break;

                case 7:
                    mostrarCodigos(enConcurso);
                    System.out.println(enConcurso[(obraElegida(enConcurso))].aCadena());

                    break;

                case 8:
                    finalizar = true;
                    break;

                default:
                    System.out.println("Opcion incorrecta - Reintentar seleccion");
            }

        } while (!finalizar);

    }

    public static int precarga(Obra[] arregloCarga) {
        //Se pueden agregar precargas hasta un maximo de 30 valores
        //El valor cantPreC se debe actualizar a la cantidad de precargas

        int cantPreC;

        arregloCarga[0] = new Obra("Leonardo Da Vinci", "La Gioconda", 100, 77, 53);
        arregloCarga[1] = new Obra("Vincent van Gogh", "La noche estrellada", 101, 74, 92);
        arregloCarga[2] = new Obra("Vincent van Gogh", "Autorretrato", 102, 66, 54);
        arregloCarga[3] = new Obra("Pablo Picasso", "Los tres músicos", 103, 18.3, 25.1);
        arregloCarga[4] = new Obra("Joan Miró", "Mujer, pájaro, estrella", 104, 245, 170);
        arregloCarga[5] = new Obra("Johannes Vermeer", "La joven de la perla", 105, 44, 39);
        arregloCarga[6] = new Obra("Antonio Berni", "La luna y su eco", 106, 73, 100);
        arregloCarga[7] = new Obra("Antonio Berni", "Desocupados", 107, 220, 300);
        arregloCarga[8] = new Obra("Emilio Pettoruti", "Meditazione", 108, 70, 70);
        arregloCarga[9] = new Obra("Emilio Pettoruti", "Pensierosa", 109, 64, 49);

        cantPreC = 10;

        return cantPreC;
    }

    public static void cargarObras(Obra[] arreglo, int posicion) {
        /*La posicion del arreglo en el que comenzaran a realizarse los cambios dependerar de la variable posicion
          que ingresa como parametro*/
        /*En la repetitiva de carga de datos ademas se analiza las condiciones especiales que
          deben cumplir el dato codigo, largo y ancho para ser ingresos correctos*/
        //El usuario toma la decision si desea seguir cargando objetos al arreglo.

        String autor, titulo;
        int codigo, cantFilas;
        double largo, ancho;
        char respuesta;
        boolean continuar = true;

        cantFilas = arreglo.length;

        System.out.println("---********---");
        System.out.println("--- Como maximo podra ingresar " + cantFilas + " obras de artes ---");

        do {
            System.out.println("OBRA NRO " + (posicion + 1) + ": ");
            System.out.println("Ingrese el Autor: ");
            autor = TecladoIn.readLine();
            System.out.println("Ingrese el título de la obra");
            titulo = TecladoIn.readLine();
            System.out.println("Ingrese el codigo asignado ");
            codigo = TecladoIn.readLineInt();

            while (posicion > 0 && codigoRepetido(arreglo, codigo)) {
                System.out.println("El codigo ingresaso ya existe, debe reingresar un código valido: ");
                codigo = TecladoIn.readLineInt();
            }

            System.out.println("Ingresar las medidas de la obra en centimetros---");
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
        /*
        **"carga" determina la cantidad de obras cargadas en el arreglo 
        **al momento de su llamado y asi limitar el recorrido de busqueda
         */
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

        System.out.println(String.format("%" + 7 + "s" + "%" + espacioTitulo + "s" + "%" + espacioAutor + "s", "CÓDIGO", "TÍTULO", "AUTOR"));
        for (int i = 0; i < cantCol; i++) {
            System.out.print(String.format("%" + 7 + "d", arreglo[i].getCodigo()));
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

    public static void obrasSinDescalificar(Obra[] arreglo) {
        //Solo se muestran las obras que no estan descalificadas        

        int cantCol;
        int espacioTitulo;

        cantCol = arreglo.length;

        espacioTitulo = tituloLargo(arreglo) + 2;
        if (espacioTitulo <= 6) {
            espacioTitulo = 8;
        }

        System.out.println(String.format("%" + 8 + "s" + "%" + espacioTitulo + "s", "CÓDIGO", "TÍTULO"));
        for (int i = 0; i < cantCol; i++) {
            if (!arreglo[i].getDescalificada()) {
                System.out.print(String.format("%" + 8 + "d", arreglo[i].getCodigo()));
                System.out.print(String.format("%" + espacioTitulo + "s", arreglo[i].getTitulo()));
                System.out.println("");
            }
        }
    }

    public static int obraElegida(Obra[] arreglo) {
        //El usuario selecciona una obra en particular mediante el ingreso de su codigo
        //La repetitiva verifica que el codigo sea correcto y devuelve la posicion de esa obra

        int cantCol, pos, obraSelec;
        boolean seguir;

        cantCol = arreglo.length;

        do {
            seguir = true;
            pos = 0;

            System.out.println("");
            System.out.println("Ingrese el código de la obra elegida: ");
            obraSelec = TecladoIn.readLineInt();

            while (pos < cantCol && seguir) {

                if (arreglo[pos].getCodigo() == obraSelec) {
                    seguir = false;
                } else {
                    pos++;
                }
            }
            if (seguir) {
                System.out.println("El codigo ingresado es incorrecto.");
            }
        } while (seguir);

        return pos;
    }

    public static String mostrarAutores(Obra[] arreglo) {
        //Entran al modulo el arreglo con todas las obras en el concurso
        //Devuelve todos los autores sin repetir nombres
        //Compara cada autor con su anterior si no se repite se concatena en la cadena participantes

        String participantes = "";
        int cantCol, i;
        boolean autorUnico;

        cantCol = arreglo.length;

        for (int j = 0; j < cantCol; j++) {
            autorUnico = true;
            i = j - 1;
            while (i >= 0 && autorUnico) {
                if (arreglo[j].getAutor().equals(arreglo[i].getAutor())) {
                    autorUnico = false;
                }
                i--;
            }

            if (autorUnico) {
                participantes = participantes + arreglo[j].getAutor() + "\n";
            }
        }
        return participantes;
    }

    public static void obrasDeAutor(Obra[] arreglo, String autor) {
        //Realiza un recorrido completo buscando las todas las obras de un autor x que ingresa por parametro.       

        System.out.println("Las obras de artes pertenecientes al autor " + autor + " son:");
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].getAutor().contains(autor)) {
                System.out.println("  " + arreglo[i].getTitulo());
            }
        }
    }

    public static double mayorPrecio(Obra[] arreglo) {
        /*Realiza un recorrido exhaustivo del arreglo comparando el precio
        **de cada obra con un precio maximo preestablecido en un valor minimo.
        **En mayorValor se va a asignar el mayor precio del arreglo y luego sera retornado
         */

        double mayorValor = 0;
        int cantFilas;
        cantFilas = arreglo.length;

        for (int i = 0; i < cantFilas; i++) {
            if (arreglo[i].getPrecio() > mayorValor) {
                mayorValor = arreglo[i].getPrecio();
            }
        }
        return mayorValor;
    }

    public static void ganadores(Obra[] arreglo) {
        //Determina los ganadores en tres grupos (primer, segundo y tercer puesto.)
        // No toma en cuenta a los descalificados
        //Si hay un empate los clasifica segun el grupo donde corresponda
        //Segundo y tercer puesto limitan los votos de acuerdo al puesto anterior 

        int primero = 0, segundo = 0, tercero = 0;
        int cantFilas;
        String primerP = "", segundoP = "", tercerP = "";

        cantFilas = arreglo.length;

        for (int i = 0; i < cantFilas; i++) {
            if (arreglo[i].getVotos() > primero && !arreglo[i].getDescalificada()) {
                primero = arreglo[i].getVotos();
            }
        }

        for (int i = 0; i < cantFilas; i++) {
            if (arreglo[i].getVotos() > segundo && arreglo[i].getVotos() < primero && !arreglo[i].getDescalificada()) {
                segundo = arreglo[i].getVotos();
            }
        }

        for (int i = 0; i < cantFilas; i++) {
            if (arreglo[i].getVotos() > tercero && arreglo[i].getVotos() < segundo && !arreglo[i].getDescalificada()) {
                tercero = arreglo[i].getVotos();
            }
        }

        for (int i = 0; i < cantFilas; i++) {

            if (arreglo[i].getVotos() == primero && !arreglo[i].getDescalificada()) {
                primerP = primerP + arreglo[i].getCodigo() + " - " + arreglo[i].getTitulo() + " -  " + arreglo[i].getAutor() + "\n";
            }
            if (arreglo[i].getVotos() == segundo && !arreglo[i].getDescalificada()) {
                segundoP = segundoP + arreglo[i].getCodigo() + " - " + arreglo[i].getTitulo() + " -  " + arreglo[i].getAutor() + "\n";
            }
            if (arreglo[i].getVotos() == tercero && !arreglo[i].getDescalificada()) {
                tercerP = tercerP + arreglo[i].getCodigo() + " - " + arreglo[i].getTitulo() + " -  " + arreglo[i].getAutor() + "\n";
            }
        }
        System.out.println("Podio de ganadores: ");
        System.out.println("Puesto 1 - con " + primero + " puntos: \n" + primerP);
        System.out.println("Puesto 2 - con " + segundo + " puntos: \n" + segundoP);
        System.out.println("Puesto 3 - con " + tercero + " puntos: \n" + tercerP);

    }

    public static void mostrarCodigos(Obra[] arreglo) {
        // Muestra todos los codigos del concurso.

        int cantCol;
        cantCol = arreglo.length;

        System.out.println("Códigos en concurso: ");
        for (int i = 0; i < cantCol; i++) {
            System.out.print(String.format("%" + 5 + "d", arreglo[i].getCodigo()));

            if ((i + 1) % 5 == 0) {
                System.out.println("");
            }
        }
        System.out.println("");

    }

    public static void menu() {
        System.out.println("----------------------------------------------------");
        System.out.println("1 - Mostrar todas las obras.");
        System.out.println("2 - Votar una obra de arte");
        System.out.println("3 - Descalificar una obra de arte");
        System.out.println("4 - Mostrar todas las obras de artes de un autor en específico");
        System.out.println("5 - Mostrar la obra de arte que consiguio el mayor precio");
        System.out.println("6 - Lista de obras ganadoras");
        System.out.println("7 - Información completa de una obra a partir de un código");
        System.out.println("8 - Salir");
    }

}
