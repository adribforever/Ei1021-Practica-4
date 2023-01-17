package client;

import java.io.IOException;
import java.rmi.Naming;
import java.util.Scanner;

import common.IntServidorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.ImplServidorViajes;

public class ClienteViajesRMI {

    // Sustituye esta clase por tu versión de la clase SubastasLocal de la práctica 1

    // Modifícala para que instancie un objeto de la clase AuxiliarClienteViajes

    // Modifica todas las llamadas al objeto de la clase GestorViajes
    // por llamadas al objeto de la clase AuxiliarClienteViajes.
    // Los métodos a llamar tendrán la misma signatura.

    /**
     * Muestra el menu de opciones y lee repetidamente de teclado hasta obtener una opcion valida
     *
     * @param teclado stream para leer la opción elegida de teclado
     * @return opción elegida
     */
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("\n\n");
        System.out.println("=====================================================");
        System.out.println("============            MENU        =================");
        System.out.println("=====================================================");
        System.out.println("0. Salir");
        System.out.println("1. Consultar viajes con un origen dado");
        System.out.println("2. Reservar un viaje");
        System.out.println("3. Anular una reserva");
        System.out.println("4. Ofertar un viaje");
        System.out.println("5. Borrar un viaje");
        do {
            System.out.print("\nElige una opcion (0..5): ");
            opcion = teclado.nextInt();
        } while ((opcion < 0) || (opcion > 5));
        teclado.nextLine(); // Elimina retorno de carro del buffer de entrada
        return opcion;
    }

    /**
     * Programa principal. Muestra el menú repetidamente y atiende las peticiones del cliente.
     *
     * @param args no se usan argumentos de entrada al programa principal
     */
    public static void main(String[] args) throws IOException {

        try {
            System.out.println("By default, the RMIRegistry host name is localhost and its RMIregistry port number is 1099.");
            String hostName = "localhost";
            int RMIPortNum = 1099;

            String registryURL = "rmi://" + hostName + ":" + RMIPortNum + "/hello";
            // find the remote object and cast it to an interface object
            IntServidorViajes h = (IntServidorViajes) Naming.lookup(registryURL);

            System.out.println("Lookup completed ");
            // invoke the remote method


            // end try


            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduzca el codigo de cliente: ");
            String codcli = scanner.nextLine();

            int opcion;

            do {
                opcion = menu(scanner);
                switch (opcion) {
                    case 0: // Guardar los datos en el fichero y salir del programa
                        h.guardaDatos();
                        System.out.println("Datos guardadosx");
                        break;

                    case 1: { // Consultar viajes con un origen dado
                        System.out.println("¿Cual es el origen?"); // Preguntamos el origen
                        String codigoDado = scanner.nextLine(); // Lo guardamos en una variable
                        if (h.consultaViajes(codigoDado).isEmpty()) { // si no existe
                            System.out.println("No existe ese origen");
                        } else { // si  existe en el JSON
                            System.out.println("La siguiente informacion corresponde con los viajes con origen " + codigoDado + " :");
                            System.out.println(h.consultaViajes(codigoDado));
                        }
                        break;
                    }

                    case 2: { // Reservar un viaje
                        System.out.println("Introduce el codigo del viaje:");
                        String codviaje = scanner.nextLine();
                        if (h.reservaViaje(codviaje, codcli).isEmpty()) { // Si el valor de retorno es vacio es que ha habido algun error
                            System.out.println("EL viaje no ha podido reservarse");
                        } else {
                            System.out.println("Viaje reservado con exito");
                        }

                        break;
                    }

                    case 3: { // Anular una reserva

                        System.out.println("Introduce el código del viaje que quieres anular"); // Preguntamos el que viaje que quiere anular
                        String codviaje = scanner.nextLine(); // Lo guardamos en una variable

                        if (h.anulaReserva(codviaje, codcli).isEmpty()) { // Si el valor de retorno es vacio es que ha habido algun error
                            System.out.println("El viaje no puede anularse");
                        } else {
                            System.out.println("Viaje anulado con exito");

                        }
                        break;
                    }
                    case 4: { // Ofertar un viaje
                        System.out.println("Introduce el origen:"); //guarda origen
                        String origen = scanner.nextLine();
                        System.out.println("Introduce el destino:"); //guarda destino
                        String destino = scanner.nextLine();
                        System.out.println("Introduce la fecha en formato: dd-MM-yyyy:"); //guarda fecha
                        String fecha = scanner.nextLine();
                        System.out.println("Introduce el precio del viaje:"); //guarda precio
                        long precio = Long.parseLong(scanner.nextLine());
                        System.out.println("Introduce el número de plazas:"); //guarda numplazas
                        long numplazas = Long.parseLong(scanner.nextLine());
                        if (h.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas).isEmpty()) { // Si el valor de retorno es vacio es que ha habido algun error
                            System.out.println("El viaje no ha podido crearse");
                        } else {
                            System.out.println("El siguiente viaje se ha ofertado con exito:");
                            System.out.println(h.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas));
                        }
                        break;
                    }

                    case 5: { // Borrar un viaje ofertado
                        System.out.println("Introduce el código del viaje que quieres anular:"); // Preguntamos el que viaje que quiere anular
                        String codviaje = scanner.nextLine(); // Lo guardamos en una variable
                        if (h.borraViaje(codviaje, codcli).isEmpty()) {// Si el valor de retorno es vacio es que ha habido algun error
                            System.out.println("No se ha podido borrar el viaje ofertado");
                        } else {
                            System.out.println("Viaje borrado con éxito");
                        }
                    }
                    break;

                } // fin switch

            } while (opcion != 0);
        }catch (Exception e) {
            System.out.println("Exception in HelloClient: " + e);
        }

    } // fin de main


} // fin class


