package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Billetera;
import com.example.demo.models.Divisa;
import com.example.demo.models.SaldoDivisa;
import com.example.demo.repositries.DivisaRepository;
import com.example.demo.repositries.SaldoDivisaRepository;

@Service
public class DivisaServiceImpl implements DivisaService {

	@Autowired
	DivisaRepository repository;

	@Autowired
	BilleteraService billeteraService;

	@Autowired
	SaldoDivisaRepository saldoDivisaRepository;

	// Borra una divisa, la borra de todas las billeteras y se piede ese saldo
	@Override
	public boolean deleteDivisa(String divisaTipo) {
		divisaTipo.toUpperCase();
		Optional<Divisa> div = repository.findById(divisaTipo);
		if (divisaTipo!="PESO" && !div.isEmpty()) {
			// Borro la divisa de todas las billeteras existentes
			List<SaldoDivisa> saldoDivisas = saldoDivisaRepository.findAllByDivisaTipo(divisaTipo);
			if (!saldoDivisas.isEmpty()) {
				Billetera billetera;
				float aux = div.get().getDivisaValor();
				for (int i = 0; i < saldoDivisas.size(); i++) {
					billetera = saldoDivisas.get(i).getBilletera();
					billetera.setSaldoTotal((-1) * saldoDivisas.get(i).getDivisaSaldo() * aux);
					saldoDivisaRepository.delete(saldoDivisas.get(i));
					billeteraService.update(billetera);
				}
			}
			repository.deleteById(divisaTipo);
			return true;
		} else
			return false;
	}

	@Override
	public List<Divisa> getDivisas() {
		return repository.findAll();
	}

	@Override
	public Divisa getDivisaById(String divisaTipo) {
		divisaTipo.toUpperCase();
		Optional<Divisa> divisa = repository.findById(divisaTipo);
		if (!divisa.isEmpty())
			return divisa.get();
		else
			return null;
	}

	// Regisrar una nueva divisa, se agrega a todas las billeteras existentes
	@Override
	public Divisa registrarDivisa(Divisa divisa) {
		divisa.toUpperCase();
		if (repository.findById(divisa.getDivisaTipo()).isEmpty()) {
			Divisa nueva = repository.save(divisa);
			// Agrego a todas las billeteras
			List<Billetera> billeteras = billeteraService.getBilleteras();
			if (!billeteras.isEmpty()) {
				for (int i = 0; i < billeteras.size(); i++) {
					SaldoDivisa ad = new SaldoDivisa(billeteras.get(i), divisa);
					saldoDivisaRepository.save(ad);
				}
			}
			return nueva;
		} else
			return null;
	}

	@Override
	public Divisa update(Divisa divisa) {
		divisa.toUpperCase();
		Optional<Divisa> temp = repository.findById(divisa.getDivisaTipo()); //Divisa con valor anterior
		if (!divisa.getDivisaTipo().equals("PESO") && !temp.isEmpty()) {
			float valorViejo = temp.get().getDivisaValor();
			float aux;
			Billetera billetera;
			List<SaldoDivisa> saldoDivisas = saldoDivisaRepository.findAllByDivisaTipo(divisa.getDivisaTipo());
			for (int i = 0; i < saldoDivisas.size(); i++) {
				aux = (saldoDivisas.get(i).getDivisaSaldo() * divisa.getDivisaValor())
						- (saldoDivisas.get(i).getDivisaSaldo() * valorViejo);
				billetera = saldoDivisas.get(i).getBilletera();
				billetera.setSaldoTotal(aux);
				billeteraService.update(billetera);
			}
			return repository.save(divisa);
		} else
			return null;
	}

}
