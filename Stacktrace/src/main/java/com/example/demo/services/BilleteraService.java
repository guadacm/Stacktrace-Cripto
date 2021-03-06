package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Billetera;

public interface BilleteraService {

	List<Billetera> getBilleteras();

	List<Billetera> getBilleteraByUserId(Long id);

	Billetera getBilleteraById(Long billeteraId);

	Billetera registrarBilletera(Long userId);

	void update(Billetera billetera);

	boolean deleteBilletera(Long billeteraId);

	List<Billetera> getBilleteraHuerfanas();

	float getSaldoUsuario(Long userId);

}
