package br.ufrn.imd.rmi.server;

import br.ufrn.imd.rmi.interfaces.InterfaceCliente;
import br.ufrn.imd.rmi.interfaces.InterfaceRepositorio;
import br.ufrn.imd.rmi.interfaces.InterfaceServidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServidorImpl extends UnicastRemoteObject implements InterfaceServidor {
    private List<InterfaceRepositorio> repositorios;
//    public Random gerador;

    public ServidorImpl() throws RemoteException {
        this.repositorios = new ArrayList<>();
//        this.gerador = new Random();
    }

    @Override
    public void armazenar(String palavra, Integer posicaoRepositorio) throws RemoteException {
        Boolean palavraGuardada = false;
        while (!palavraGuardada) {
        	repositorios.get(posicaoRepositorio).armazenar(palavra);
//            for (InterfaceRepositorio repositorio : repositorios) {
//                if (this.gerador.nextBoolean()) {
//                    palavraGuardada = true;
//                    repositorio.armazenar(palavra);
//                }
//            }
        }
    }

    @Override
    public void buscar(InterfaceCliente cliente, String palavra) throws RemoteException {
        for (InterfaceRepositorio repositorio : repositorios) {
            repositorio.buscar(cliente, palavra);
        }
    }

    @Override
    public List<InterfaceRepositorio> getRepositorios() throws RemoteException {
        return this.repositorios;
    }
}
