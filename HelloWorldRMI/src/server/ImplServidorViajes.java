package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;

import common.IntServidorViajes;
import gestor.GestorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class implements the remote interface HelloInterface.
 *
 * @author M. L. Liu
 */

public class ImplServidorViajes extends UnicastRemoteObject implements IntServidorViajes {
    GestorViajes gestor = new GestorViajes();

    public ImplServidorViajes() throws RemoteException { //ni puta de que es
        super();
    }

    @Override
    public synchronized void guardaDatos() {
        gestor.guardaDatos();
    }

    @Override
    public synchronized JSONArray consultaViajes(String origen) {
        return gestor.consultaViajes(origen);

    }

    @Override
    public synchronized JSONObject reservaViaje(String origen, String codcli) {
        return gestor.reservaViaje(origen, codcli);
    }

    @Override
    public synchronized JSONObject anulaReserva(String codviaje, String codcli) {
        return gestor.anulaReserva(codviaje, codcli);
    }

    @Override
    public synchronized JSONObject ofertaViaje(String codcli, String origen, String destino, String fecha, long precio, long numplazas) {
        return gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas);
    }

    @Override
    public synchronized JSONObject borraViaje(String codviaje, String codcli) {
        return gestor.borraViaje(codviaje, codcli);
    }
   /* public synchronized void registerForCallback(ClientInterface callbackClientObject) throws RemoteException {
        if (!(clientList.contains(callbackClientObject))) {
            clientList.addElement(callbackClientObject);
            System.out.println("Registered new client ");
            doCallbacks();
        } else {
            System.out.println("register: client already registered..");
        } // end if
    }

    public synchronized void unregisterForCallback(ClientInterface callbackClientObject) throws RemoteException {
        if (clientList.removeElement(callbackClientObject)) {
            System.out.println("Unregistered client ");
            doCallbacks();
        } else {
            System.out.println("unregister: client wasn't registered.");
        }
    }
    // Make callback to each registered client
    private synchronized void doCallbacks() {
        System.out.println("*********\nCallbacks initiated ---");
        for (int i = clientList.size() - 1; i >= 0 ; i--) {
            System.out.println("doing "+ i +"-th callback\n");
            ClientInterface nextClient = (ClientInterface) clientList.elementAt(i);
            try {
                nextClient.notifyMe("Number of registered clients=" + clientList.size());
            } catch (RemoteException e) {
                System.out.println(“Deleting inactive callback client”);
                clientList.remove(i);
            }
        }// end for
        System.out.println("********\nServer completed callbacks ---");
    } // end doCallbacks*/

} // end class
