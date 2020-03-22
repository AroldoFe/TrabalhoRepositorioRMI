package br.ufrn.imd.rmi.client;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClienteImpl extends UnicastRemoteObject implements InterfaceCliente {

    public ClienteImpl() throws RemoteException {
    }

    @Override
    public void print(InterfaceRepositorio repositorio, String palavra) throws RemoteException {
        System.out.println("\t==================\n" +
                repositorio.getNome() + " guarda a palavra: " + palavra +
                "\n\t==================");
    }
}
