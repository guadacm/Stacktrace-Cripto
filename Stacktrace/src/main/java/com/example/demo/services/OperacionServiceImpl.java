package com.example.demo.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Billetera;
import com.example.demo.models.Deposito;
import com.example.demo.models.Intercambio;
import com.example.demo.models.Usuario;
import com.example.demo.repositries.DepositoRepository;
import com.example.demo.repositries.IntercambioRepository;
import com.example.demo.repositries.SaldoDivisaRepository;

@Service
public class OperacionServiceImpl implements OperacionService {

	@Autowired
	IntercambioRepository intercambioRepo;
	
	@Autowired
	DepositoRepository depositoRepo;

	@Autowired
	BilleteraService billeteraService;
	
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	DivisaService divisaService;

	@Autowired
	SaldoDivisaRepository saldoRepo;

	@Override
	public float getSaldoBilletera(Long billeteraId) {
		Billetera billetera = billeteraService.getBilleteraById(billeteraId);
		if (billetera != null)
			return billetera.getSaldoTotal();
		else
			return -1;
	}

	@Override
	public float getSaldoUsuario(Long userId) {
		Usuario usuario = usuarioService.getUsuarioById(userId);		
		if(usuario != null)
			return billeteraService.getSaldoUsuario(userId);
		else
			return -1;
	}

	@Override
	public List<Intercambio> getIntercambios() {
		return intercambioRepo.findAll();
	}
	@Override
	public List<Deposito> getDepositos() {
		return depositoRepo.findAll(); //.getDepositos();
	}

	@Override
	public Intercambio intercambioDivisa(Intercambio intercambio) {
		intercambio.setbOrigen(billeteraService.getBilleteraById(intercambio.getbOrigen().getBilleteraId()));
		intercambio.setbDestino(billeteraService.getBilleteraById(intercambio.getbDestino().getBilleteraId()));
		
		intercambio.getDivOrigen().toUpperCase();
		intercambio.getDivDestino().toUpperCase();
		intercambio.setDivOrigen(divisaService.getDivisaById(intercambio.getDivOrigen().getDivisaTipo()));
		intercambio.setDivDestino(divisaService.getDivisaById(intercambio.getDivDestino().getDivisaTipo()));
		
		if(intercambio.getbOrigen()!=null && intercambio.getbDestino()!=null && intercambio.getDivOrigen()!=null && intercambio.getDivDestino()!=null){
			//verifico que haya saldo para intercambiar
			for(int i=0; i<intercambio.getbOrigen().getSaldoDivisa().size();i++) {
				if(intercambio.getbOrigen().getSaldoDivisa().get(i).getDivisa().getDivisaTipo().equals(intercambio.getDivOrigen().getDivisaTipo())) {
					if(intercambio.getMonto()<=intercambio.getbOrigen().getSaldoDivisa().get(i).getDivisaSaldo()) {
						//hago el intercambio
						//actualizo billetera de origen
						intercambio.getbOrigen().getSaldoDivisa().get(i).setDivisaSaldo(intercambio.getMonto()*(-1));
						intercambio.getbOrigen().setSaldoTotal((-1)*(intercambio.getMonto()*intercambio.getDivOrigen().getDivisaValor()));
						//actualizo billetera de destino
						float saldoDestino = intercambio.getMonto()*intercambio.getDivOrigen().getDivisaValor(); // paso monto a pesos
						intercambio.getbDestino().setSaldoTotal(saldoDestino);
						for(int j=0; j<intercambio.getbDestino().getSaldoDivisa().size();j++) {
							if(intercambio.getbDestino().getSaldoDivisa().get(j).getDivisa().getDivisaTipo().equals(intercambio.getDivDestino().getDivisaTipo())) {
								intercambio.getbDestino().getSaldoDivisa().get(j).setDivisaSaldo(saldoDestino/intercambio.getDivDestino().getDivisaValor());
								break;
							}
						}
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						intercambio.setOpFechaHora(dtf.format(LocalDateTime.now()));
						System.out.println("Intercambio correcto");
						//Guardo en la bd
						return intercambioRepo.save(intercambio);
					}
					
				}
			}
	
		}
		System.out.println("No se pudo completar la operacion");
		return null;
	}

	@Override
	public Deposito depositoDivisa(Deposito deposito) {
		
		deposito.setbDestino(billeteraService.getBilleteraById(deposito.getbDestino().getBilleteraId()));
		
		deposito.getDivDestino().toUpperCase();
		deposito.setDivDestino(divisaService.getDivisaById(deposito.getDivDestino().getDivisaTipo()));
		
		//verifico que exista la billetera de destino
		if(deposito.getbDestino()!=null && deposito.getDivDestino()!=null){
			//actualizo billetera de destino
			float saldoDestino = deposito.getMonto()/deposito.getDivDestino().getDivisaValor(); // paso monto al valor de la divisa de destino
			deposito.getbDestino().setSaldoTotal(deposito.getMonto()); //actualizo el saldo total de la billeters
			for(int j=0; j<deposito.getbDestino().getSaldoDivisa().size();j++) {
				if(deposito.getbDestino().getSaldoDivisa().get(j).getDivisa().getDivisaTipo().equals(deposito.getDivDestino().getDivisaTipo())) {
					deposito.getbDestino().getSaldoDivisa().get(j).setDivisaSaldo(saldoDestino);
					break;
				}
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			deposito.setOpFechaHora(dtf.format(LocalDateTime.now()));
			System.out.println("Deposito correcto");
			//Guardo en la bd
			return depositoRepo.save(deposito);
		}
		System.out.println("No se pudo completar la operacion");
		return null;
	}

}
