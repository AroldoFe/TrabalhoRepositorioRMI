package br.ufrn.imd.rmi.interfaces;

import java.rmi.Remote;

public interface InterfaceCliente extends Remote {
    void print(InterfaceRepositorio repositorio, String palavra);
}
