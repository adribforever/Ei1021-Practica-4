package common;

// A simple RMI interface file - M. Liu
import java.rmi.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This is a remote interface.
 * @author M. L. Liu
 */

public interface IntServidorViajes extends Remote {
/**
 * This remote method returns a message.
 * It accepts no parameter.
 * @return a String message.
 */
void guardaDatos() throws RemoteException;
JSONArray consultaViajes(String origen) throws RemoteException;
JSONObject reservaViaje(String origen,String codcli)throws RemoteException;
JSONObject anulaReserva(String codviaje, String codcli)throws RemoteException;
JSONObject ofertaViaje(String codcli, String origen, String destino, String fecha, long precio, long numplazas)throws RemoteException;
JSONObject borraViaje(String codviaje, String codcli)throws RemoteException;

void registerForCallback(String origen, IntCallbackCliente callbackClientObject) throws RemoteException;
void unregisterForCallback(String origen, IntCallbackCliente callbackClientObject) throws RemoteException;

}
