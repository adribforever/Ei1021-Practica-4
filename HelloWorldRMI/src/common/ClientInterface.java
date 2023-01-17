package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public void notifyMe(String message) throws RemoteException;
} // end interface