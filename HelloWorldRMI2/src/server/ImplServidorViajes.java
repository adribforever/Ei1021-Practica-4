package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import common.IntCallbackCliente;
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
    HashMap<String, Set<IntCallbackCliente>> clientList;
    boolean encontrado = false;

    public ImplServidorViajes() throws RemoteException {
        super();
        clientList = new HashMap<String, Set<IntCallbackCliente>>();
    }

    @Override
    public void guardaDatos() {
        gestor.guardaDatos();
    }

    @Override
    public JSONArray consultaViajes(String origen) {
        return gestor.consultaViajes(origen);

    }

    @Override
    public JSONObject reservaViaje(String origen, String codcli) {
        return gestor.reservaViaje(origen, codcli);
    }

    @Override
    public JSONObject anulaReserva(String codviaje, String codcli) {
        return gestor.anulaReserva(codviaje, codcli);
    }

    @Override
    public JSONObject ofertaViaje(String codcli, String origen, String destino, String fecha, long precio, long numplazas) throws RemoteException {
        JSONObject ofertado = gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas);
        if (!ofertado.isEmpty()) doCallbacks(origen);
        return ofertado;
    }

    @Override
    public JSONObject borraViaje(String codviaje, String codcli) {
        return gestor.borraViaje(codviaje, codcli);
    }

    @Override
    public void registerForCallback(String origen, IntCallbackCliente callbackClientObject) throws RemoteException {
        if (!clientList.containsKey(origen.toLowerCase())) {
            clientList.put(origen.toLowerCase(), new HashSet<>());
        }
        clientList.get(origen.toLowerCase()).add(callbackClientObject);
        System.out.println("Registered client ");
    }
    @Override
    public void unregisterForCallback(String origen, IntCallbackCliente callbackClientObject) throws RemoteException {
        if (clientList.get(origen.toLowerCase()).remove(callbackClientObject)) { // Si el cliente esta registrado en ese origen
            System.out.println("Unregistered client ");
        } else {
            System.out.println("unregister: client wasn't registered.");
        }
    }

    // Make callback to each registered client
    public void doCallbacks(String origen) throws RemoteException {
        Set<IntCallbackCliente> clientes = clientList.get(origen.toLowerCase());
        for (IntCallbackCliente cliente : clientes) {
            try {
                cliente.notifyMe("Ya hay viajes en: " + origen);
            } catch (Exception e) {
                unregisterForCallback(origen.toLowerCase(), cliente);
                System.out.println("Deleting inactive callback client");
            }
        }
        System.out.println("********\nServer completed callbacks ---");

    }
}
     // end doCallbacks*/

// end class
