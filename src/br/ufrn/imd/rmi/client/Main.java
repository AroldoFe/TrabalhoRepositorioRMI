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

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        InterfaceServidor servidor = (InterfaceServidor) Naming.lookup("rmi://localhost:2000/Servidor");
        InterfaceCliente cliente = new ClienteImpl();
        Scanner scanner = new Scanner(System.in);
        Integer opcao = 0;
        while (opcao != -1) {
            System.out.println(StringUtils.OPCOES);
            opcao = scanner.nextInt();
            if(opcao.equals(1)) {
                System.out.println(StringUtils.GUARDAR_PALAVRA);
                String palavra = scanner.next();
                servidor.armazenar(palavra);
            } else if (opcao.equals(2)) {
                System.out.println(StringUtils.BUSCAR_PALAVRA);
                String palavra = scanner.next();
                servidor.buscar(cliente, palavra);
            } else if(opcao.equals(-1)) {
                break;
            } else {
                System.out.println(StringUtils.OPCAO_INVALIDA);
            }
        }
    }
}
