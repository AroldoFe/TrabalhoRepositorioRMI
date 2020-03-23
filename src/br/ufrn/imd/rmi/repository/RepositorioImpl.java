package br.ufrn.imd.rmi.repository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;

public class RepositorioImpl extends UnicastRemoteObject implements InterfaceRepositorio {
	private static final long serialVersionUID = -1553069973323620029L;
	
	private String nome;
	
    private String ip;
    
    private Integer porta;
    
    private Set<String> palavras;

    public RepositorioImpl() throws RemoteException {
        super();
        this.palavras = new HashSet<>();
    }

    public RepositorioImpl(String nome, String ip, Integer porta) throws RemoteException {
        this();
        this.nome = nome;
        this.ip = ip;
        this.porta = porta;
    }

    @Override
    public void armazenar(String palavra) throws RemoteException {
        this.palavras.add(palavra);
    }

    @Override
    public void buscar(InterfaceCliente cliente, String palavra) throws RemoteException {
        if (this.palavras.contains(palavra)) {
            cliente.print(this, palavra);
        }
    }

    @Override
    public String getNome() throws RemoteException {
        return this.nome;
    }
    
    @Override
	public String getIp() throws RemoteException {
		return this.ip;
	}

	@Override
	public String getEndereco() throws RemoteException {
		return "rmi://" + this.ip +":"+this.porta+"/" + this.nome;
	}

	@Override
	public Integer getPorta() throws RemoteException {
		return this.porta;
	}
}
