package br.ufrn.imd.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;
import br.ufrn.imd.rmi.interfaces.InterfaceServidor;

public class ServidorImpl extends UnicastRemoteObject implements InterfaceServidor {
	
	private static final long serialVersionUID = 3680032288912171665L;

	private volatile List<InterfaceRepositorio> repositorios = new ArrayList<InterfaceRepositorio>();
    
    public Random gerador;

    public ServidorImpl() throws RemoteException {
        this.gerador = new Random();
    }

    /*
     * Adiciona a palavra aleatoriamente e unicamente entre os repositórios disponíveis. 
     */
    @Override
    public void armazenar(String palavra) throws RemoteException {
        Boolean palavraGuardada = false;
        while (!palavraGuardada) {
            for (InterfaceRepositorio repositorio : repositorios) {
                if (this.gerador.nextBoolean()) {
                    palavraGuardada = true;
                    repositorio.armazenar(palavra);
                }
            }
        }
    }
    
    /*
     * Busca entre o repositórios disponíveis a palavra.
     */
    @Override
    public void buscar(InterfaceCliente cliente, String palavra) throws RemoteException {
        for (InterfaceRepositorio repositorio : repositorios) {
            repositorio.buscar(cliente, palavra);
        }
    }

    @Override
    public List<InterfaceRepositorio> getRepositorios() throws RemoteException {
        return this.repositorios;
    }
    
    /*
     * Adiciona um repositório a lista.
     */

	@Override
	public void registrarRepositorio(InterfaceRepositorio repositorio) throws RemoteException{
		this.repositorios.add(repositorio);
	}
}
