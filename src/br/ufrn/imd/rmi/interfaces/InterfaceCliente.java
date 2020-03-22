package br.ufrn.imd.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceCliente extends Remote {
    void print(InterfaceRepositorio repositorio, String palavra) throws RemoteException;
}
