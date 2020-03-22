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

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
    	// Conecta-se ao servidor
        InterfaceServidor servidor = (InterfaceServidor) Naming.lookup("rmi://127.0.0.1:2000/Servidor");
        InterfaceCliente cliente = new ClienteImpl();
        
        Scanner scanner = new Scanner(System.in);
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
