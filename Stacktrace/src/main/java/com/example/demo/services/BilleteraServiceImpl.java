package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Billetera;
import com.example.demo.models.Divisa;
import com.example.demo.models.SaldoDivisa;
import com.example.demo.models.Usuario;
import com.example.demo.repositries.BilleteraRepository;
import com.example.demo.repositries.SaldoDivisaRepository;

@Service
public class BilleteraServiceImpl implements BilleteraService {

	@Autowired
	BilleteraRepository repository;

	@Autowired
	SaldoDivisaRepository saldoRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	DivisaService divisaService;

	@Override
	public List<Billetera> getBilleteras() {
		List<Billetera> billeteras = repository.findAll();
		for (int i = 0; i < billeteras.size(); i++) {
			List<SaldoDivisa> saldos = saldoRepository.findByBilletera(billeteras.get(i));
			billeteras.get(i).setSaldoDivisa(saldos);
			;
		}
		return billeteras;
	}

	@Override
	public List<Billetera> getBilleteraByUserId(Long userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public Billetera getBilleteraById(Long billeteraId) {
		Optional<Billetera> aux = repository.findById(billeteraId);
		if (!aux.isEmpty()) {
			Billetera billetera = aux.get();
			List<SaldoDivisa> saldos = saldoRepository.findByBilletera(billetera);
			billetera.setSaldoDivisa(saldos);
			return billetera;
		} else
			return null;
	}

	@Override
	public Billetera registrarBilletera(Long userId) {
		Usuario usuario = usuarioService.getUsuarioById(userId);
		if (usuario != null) {
			Billetera nueva = new Billetera(0, usuario);
			nueva = repository.save(nueva);
			List<Divisa> divisas = divisaService.getDivisas();
			List<SaldoDivisa> saldos = new ArrayList<SaldoDivisa>();
			for (int i = 0; i < divisas.size(); i++) {
				SaldoDivisa addDivisa = new SaldoDivisa(nueva, divisas.get(i));
				addDivisa = saldoRepository.save(addDivisa);
				saldos.add(addDivisa);
			}
			nueva.setSaldoDivisa(saldos);
			return nueva;
		}
		return null;
	}

	@Override
	public void update(Billetera billetera) {

		if (!repository.findById(billetera.getBilleteraId()).isEmpty())
			repository.save(billetera);
	}

	@Override
	public boolean deleteBilletera(Long billeteraId) {
		Optional<Billetera> billetera = repository.findById(billeteraId);
		if (!billetera.isEmpty()) {
			repository.delete(billetera.get());
			return true;
		} else
			return false;
	}

	@Override
	public List<Billetera> getBilleteraHuerfanas() {
		List<Billetera> billeteras = repository.billeterasHuerfanas();
		for (int i = 0; i < billeteras.size(); i++) {
			List<SaldoDivisa> saldos = saldoRepository.findByBilletera(billeteras.get(i));
			billeteras.get(i).setSaldoDivisa(saldos);
		}
		return billeteras;
	}
	
	@Override
	public float getSaldoUsuario(Long userId) {
		return repository.getSaldoUsuario(userId);
	}
}
