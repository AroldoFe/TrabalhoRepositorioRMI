package br.ufrn.imd.rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;

public class ClienteImpl extends UnicastRemoteObject implements InterfaceCliente {
	
	private static final long serialVersionUID = 3986832337945698321L;

	public ClienteImpl() throws RemoteException {
    }

    @Override
    public void print(InterfaceRepositorio repositorio, String palavra) throws RemoteException {
        System.out.println("\t==================\n" +
                repositorio.getNome() + " guarda a palavra: " + palavra +
                "\n\t==================");
    }
}
