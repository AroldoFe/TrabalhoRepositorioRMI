package br.ufrn.imd.rmi.client;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceServidor;
import br.ufrn.imd.rmi.utils.StringUtils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {
	
	/*
	 * Responsável por mostrar uma interface de comunicação para o usuário.
	 */
	@SuppressWarnings("resource")
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
    	// Conecta-se ao servidor
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
		}catch(ClassCastException e) {
			throw new RemoteException("Problemas na conexão com o servidor.");
		}
		
		if(servidor == null) {
			return;
		}
		
        InterfaceCliente cliente = new ClienteImpl();
        
        Integer opcao = 0;
        String palavra;
        
        while (opcao != -1) {
        	
            System.out.println(StringUtils.OPCOES);
            opcao = scanner.nextInt();
            
            switch(opcao) {
	        	case 1:
	        		System.out.println(StringUtils.GUARDAR_PALAVRA);
	                palavra = scanner.next();
	                servidor.armazenar(palavra);
	    		break;
	        	case 2:
	        		System.out.println(StringUtils.BUSCAR_PALAVRA);
	                palavra = scanner.next();
	                servidor.buscar(cliente, palavra);
	    		break;
	        	case -1:
	    		break;
	        	default:
	        		System.out.println(StringUtils.OPCAO_INVALIDA);
	        		
           }
        }
    }
}
