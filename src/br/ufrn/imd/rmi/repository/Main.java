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
	
	@SuppressWarnings({ "resource", "null" })
    public static void main(String[] args) throws RemoteException{
    	System.setProperty("java.rmi.server.hostname", "127.0.0.1");
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println(StringUtils.ENDERECO_SERVIDOR);
    	String enderecoServidor = scanner.next();
    	
    	if (enderecoServidor.isBlank() || enderecoServidor.isEmpty()) {
    		throw new RemoteException("Endereco do servidor inválido");
    	}
    	
    	// Conecta-se com o servidor
        InterfaceServidor servidor = null;
        
		try {
			servidor = (InterfaceServidor) Naming.lookup("rmi://"+enderecoServidor);
		} catch (NotBoundException e) {
			throw new RemoteException("Nenhuma servidor encontrado");
		} catch(MalformedURLException e) {
			throw new RemoteException("Url do servidor mal formada");
		}
		
		if(servidor == null) {
			return;
		}
		
        // Pega o host da máquina
        InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			throw new RemoteException("Ip da máquina não encontrado.");
		}
		
    	// Pega o endereço IP
		String ipRepositorio = inetAddress.getHostAddress();
        
		// Requisita o usuário o nome do repositório
    	System.out.println(StringUtils.NOME_REPOSITORIO);
    	String nomeRepositorio = scanner.next();
    	
    	if (nomeRepositorio.isBlank() || nomeRepositorio.isEmpty()) {
    		throw new RemoteException("Nome do repositório inválido");
    	}
    	
    	// Requisita o usuário a porta do repositório
    	System.out.println(StringUtils.PORTA_REPOSITORIO);
    	Integer portaRepositorio = scanner.nextInt();
    	
        InterfaceRepositorio repositorio = new RepositorioImpl(nomeRepositorio, ipRepositorio, portaRepositorio);
        LocateRegistry.createRegistry(repositorio.getPorta());
        try {
			Naming.rebind(repositorio.getEndereco(), repositorio);
		} catch (MalformedURLException e) {
			throw new RemoteException("Erro nregistro do repositório");
		}
        
        // Adiciona o repositório a lista dos repositórios que o servidor escuta.
        servidor.registrarRepositorio(repositorio);
      
    }
}
