package br.ufrn.imd.rmi.repository;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RepositorioImpl extends UnicastRemoteObject implements InterfaceRepositorio {
    private String nome;
    private List<String> palavras;

    public RepositorioImpl() throws RemoteException {
        super();
        this.palavras = new ArrayList<>();
    }

    public RepositorioImpl(String nome) throws RemoteException {
        this();
        this.nome = nome;

    }

    @Override
    public void armazenar(String palavra) throws RemoteException {
        if (!this.palavras.contains(palavra)) {
            this.palavras.add(palavra);
        }
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

}
