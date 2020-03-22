package br.ufrn.imd.rmi.server;

import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;
import br.ufrn.imd.rmi.interfaces.InterfaceServidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        InterfaceServidor servidor = new ServidorImpl();

        servidor.getRepositorios().add((InterfaceRepositorio) Naming.lookup("rmi://127.0.0.1:2001/Repositorio1"));
        servidor.getRepositorios().add((InterfaceRepositorio) Naming.lookup("rmi://127.0.0.1:2002/Repositorio2"));
        servidor.getRepositorios().add((InterfaceRepositorio) Naming.lookup("rmi://127.0.0.1:2003/Repositorio3"));

        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        LocateRegistry.createRegistry(2000);
        Naming.rebind("rmi://127.0.0.1:2000/Servidor", servidor);
    }
}
