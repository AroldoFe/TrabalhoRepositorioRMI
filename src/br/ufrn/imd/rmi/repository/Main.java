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
    public static void main(String[] args) throws RemoteException, MalformedURLException{
    	System.setProperty("java.rmi.server.hostname", "127.0.0.1");
    	
    	// Conecta-se com o servidor
        InterfaceServidor servidor;
		try {
			servidor = (InterfaceServidor) Naming.lookup("rmi://127.0.0.1:2000/Servidor");
		} catch (NotBoundException e) {
			throw new RemoteException("Ocorreu um problema na conex�o com o servidor.");
		}
        
        // Pega o host da m�quina
        InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			throw new RemoteException("Ip da m�quina n�o encontrado.");
		}
		
    	// Pega o endere�o IP
		String ipRepositorio = inetAddress.getHostAddress();
        
		Scanner scanner = new Scanner(System.in);
		// Requisita o usu�rio o nome do reposit�rio
    	System.out.println(StringUtils.NOME_REPOSITORIO);
    	String nomeRepositorio = scanner.next();
    	
    	// Requisita o usu�rio a porta do reposit�rio
    	System.out.println(StringUtils.PORTA_REPOSITORIO);
    	Integer portaRepositorio = scanner.nextInt();
    	
        InterfaceRepositorio repositorio = new RepositorioImpl(nomeRepositorio, ipRepositorio, portaRepositorio);
        LocateRegistry.createRegistry(repositorio.getPorta());
        Naming.rebind(repositorio.getEndereco(), repositorio);
        
        // Adiciona o reposit�rio a lista dos reposit�rios que o servidor escuta.
        servidor.registrarRepositorio(repositorio);
      
    }
}
