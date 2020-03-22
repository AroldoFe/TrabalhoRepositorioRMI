package br.ufrn.imd.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRepositorio extends Remote {
    void armazenar(String palavra) throws RemoteException;
    void buscar(InterfaceCliente cliente, String palavra) throws RemoteException;
    String getNome() throws RemoteException;
}
