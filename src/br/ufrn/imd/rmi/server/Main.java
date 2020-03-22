package br.ufrn.imd.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import br.ufrn.imd.rmi.interfaces.InterfaceServidor;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException{
    	// Incializa o servidor
        InterfaceServidor servidor = new ServidorImpl();
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        LocateRegistry.createRegistry(2000);
        Naming.rebind("rmi://127.0.0.1:2000/Servidor", servidor);
    }
}
