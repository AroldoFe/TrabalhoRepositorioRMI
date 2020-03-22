package br.ufrn.imd.rmi.server;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;
import br.ufrn.imd.rmi.interfaces.InterfaceServidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServidorImpl extends UnicastRemoteObject implements InterfaceServidor {
    private List<InterfaceRepositorio> repositorios;
    public Random gerador;

    public ServidorImpl() throws RemoteException {
        super();
        this.repositorios = new ArrayList<>();
        this.gerador = new Random();
    }

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

    @Override
    public void buscar(InterfaceCliente cliente, String palavra) throws RemoteException {
        for (InterfaceRepositorio repositorio : repositorios) {
            repositorio.buscar(cliente, palavra);
        }
    }

    @Override
    public List<InterfaceRepositorio> getRepositorios() {
        return this.repositorios;
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        InterfaceServidor servidor = new ServidorImpl();

        servidor.getRepositorios().add( (InterfaceRepositorio) Naming.lookup("rmi://127.0.0.1:2001/Repositorio1"));
        servidor.getRepositorios().add( (InterfaceRepositorio) Naming.lookup("rmi://127.0.0.1:2002/Repositorio1"));
        servidor.getRepositorios().add( (InterfaceRepositorio) Naming.lookup("rmi://127.0.0.1:2003/Repositorio1"));

        System.setProperty("java.rmi.server.hostname","127.0.0.1");
        LocateRegistry.createRegistry(2000);
        Naming.rebind("rmi://127.0.0.1:1098/Servidor", servidor);
    }
}
