package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Deposito;
import com.example.demo.models.Intercambio;

public interface OperacionService {

	float getSaldoBilletera(Long billeteraId);

	float getSaldoUsuario(Long userId);

	Intercambio intercambioDivisa(Intercambio intercambio);

	List<Intercambio> getIntercambios();

	Deposito depositoDivisa(Deposito deposito);

	List<Deposito> getDepositos();
}
