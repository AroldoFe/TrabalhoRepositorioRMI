package br.ufrn.imd.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InterfaceServidor extends Remote {
    void armazenar(String palavra, Integer posicaoRepositorio) throws RemoteException;
    void buscar(InterfaceCliente cliente, String palavra) throws RemoteException;
    List<InterfaceRepositorio> getRepositorios() throws RemoteException;

}
