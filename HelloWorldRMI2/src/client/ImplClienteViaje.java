package client;

import common.IntCallbackCliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplClienteViaje extends UnicastRemoteObject implements IntCallbackCliente {
    public ImplClienteViaje() throws RemoteException {
        super();
    }

    @Override
    public void notifyMe(String message) throws RemoteException {
        System.out.println(message);

    }
}
