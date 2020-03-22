package br.ufrn.imd.rmi.repository;

import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");

        /*InterfaceRepositorio repositorio = new RepositorioImpl("Repositorio1");
        LocateRegistry.createRegistry(2001);
        Naming.rebind("rmi://127.0.0.1:2001/Repositorio1", repositorio);*/

         /*InterfaceRepositorio repositorio = new RepositorioImpl("Repositorio2");
         LocateRegistry.createRegistry(2002);
         Naming.rebind("rmi://127.0.0.1:2002/Repositorio2", repositorio);*/

         InterfaceRepositorio repositorio = new RepositorioImpl("Repositorio3");
         LocateRegistry.createRegistry(2003);
         Naming.rebind("rmi://127.0.0.1:2003/Repositorio3", repositorio);
    }
}
