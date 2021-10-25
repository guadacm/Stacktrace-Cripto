package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Operacion;

public interface OperacionService {

	float getSaldoBilletera(Long billeteraId);

	float getSaldoUsuario(Long userId);

	Operacion intercambioDivisa(Operacion operacion);

	List<Operacion> getIntercambios();

	Operacion depositoDivisa(Operacion operacion);

	List<Operacion> getDepositos();
}