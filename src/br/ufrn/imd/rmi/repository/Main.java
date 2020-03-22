package br.ufrn.imd.rmi.repository;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;
import br.ufrn.imd.rmi.interfaces.InterfaceServidor;
import br.ufrn.imd.rmi.utils.StringUtils;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException, NotBoundException {
    	System.setProperty("java.rmi.server.hostname", "127.0.0.1");
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Ip do servidor de repositórios: \t");
        String ipServidor = scanner.next();
        InterfaceServidor servidor = (InterfaceServidor) Naming.lookup("rmi://"+ipServidor+":2000/Servidor");
        
        InetAddress inetAddress = InetAddress.getLocalHost();
    	String ipRepositorio = inetAddress.getHostAddress();
    	
    	System.out.println(StringUtils.NOME_REPOSITORIO);
    	String nomeRepositorio = scanner.next();
    	
    	System.out.println("Porta do repositório");
    	Integer portaRepositorio = scanner.nextInt();
    	
        InterfaceRepositorio repositorio = new RepositorioImpl(nomeRepositorio, "127.0.0.1", portaRepositorio);
        LocateRegistry.createRegistry(repositorio.getPorta());
        Naming.rebind(repositorio.getEndereco(), repositorio);
        
        servidor.getRepositorios().add(repositorio);
        
        System.out.println(servidor.getRepositorios().toString());

//        InterfaceRepositorio repositorio2 = new RepositorioImpl("Repositorio2");
//        LocateRegistry.createRegistry(2002);
//        Naming.rebind("rmi://127.0.0.1:2002/Repositorio2", repositorio2);
//
//        InterfaceRepositorio repositorio3 = new RepositorioImpl("Repositorio3");
//        LocateRegistry.createRegistry(2003);
//        Naming.rebind("rmi://127.0.0.1:2003/Repositorio3", repositorio3);
    }
}
