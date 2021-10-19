package com.stacktrace.Stacktrace.service;

import java.util.ArrayList;
import java.util.List;

import com.stacktrace.Stacktrace.model.Billetera;
import com.stacktrace.Stacktrace.model.Divisa;
import com.stacktrace.Stacktrace.model.SaldoDivisa;
import com.stacktrace.Stacktrace.repository.QueryRepository;
import com.stacktrace.Stacktrace.repository.IBilleteraRepository;
import com.stacktrace.Stacktrace.repository.IDivisaRepository;
import com.stacktrace.Stacktrace.repository.ISaldoDivisaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisaService {
    @Autowired
    IDivisaRepository iDivisaRepository;

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    ISaldoDivisaRepository iSaldoDivisaRepository;

    @Autowired
    BilleteraService billeteraService;
    @Autowired
    IBilleteraRepository iBilleteraRepository;

    public List<Divisa> getDivisas() {
        List<Divisa> divisas = new ArrayList<Divisa>();
        iDivisaRepository.findAll().forEach(divisas1 -> divisas.add(divisas1));
        return divisas;
    }

    public Divisa getDivisaById(String tipo) {
        tipo.toUpperCase();
        if (!iDivisaRepository.findById(tipo).isEmpty())
            return iDivisaRepository.findById(tipo).get();
        else
            return null;
    }

    // Regisrar una nueva divisa, se agrega a todas las billeteras existentes
    public Divisa registrarDivisa(Divisa divisa) {
        divisa.toUpperCase();
        if (iDivisaRepository.findById(divisa.getDivisaTipo()).isEmpty()) {
            Divisa nueva = iDivisaRepository.save(divisa);
            List<Billetera> billeteras = billeteraService.getBilleteras();
            if (!billeteras.isEmpty()) {
                for (int i = 0; i < billeteras.size(); i++) {
                    SaldoDivisa ad = new SaldoDivisa(billeteras.get(i).getBilleteraId(), nueva.getDivisaTipo());
                    iSaldoDivisaRepository.save(ad);
                }
            }
            return nueva;
        } else
            return null;
    }

    // Actualizar una divisa existente
    public Divisa update(Divisa divisa) {
        divisa.toUpperCase();
        if (!iDivisaRepository.findById(divisa.getDivisaTipo()).isEmpty()) {
            float valorViejo = iDivisaRepository.findById(divisa.getDivisaTipo()).get().getDivisaValor();
            float aux;
            Billetera billetera;
            List<SaldoDivisa> saldoDivisas = iSaldoDivisaRepository.findAll();
            for(int i=0; i<saldoDivisas.size();i++){
                aux=(saldoDivisas.get(i).getDivisaSaldo()*divisa.getDivisaValor())-(saldoDivisas.get(i).getDivisaSaldo()*valorViejo);
                billetera=iBilleteraRepository.getById(saldoDivisas.get(i).getBilleteraId());
                billetera.setSaldoTotal(aux);
                iBilleteraRepository.save(billetera);
            }

            return iDivisaRepository.save(divisa);
        } else
            return null;
    }

    // Borra una divisa, la borra de todas las billeteras y se piede ese saldo
    public boolean deleteDivisa(String tipo) {
        if (!iDivisaRepository.findById(tipo).isEmpty()) {            
            // Borro la divisa de todas las billeteras existentes
            List<SaldoDivisa> saldoDivisas = queryRepository.findByTipo(tipo);
            if (!saldoDivisas.isEmpty()) {
                Billetera billetera;
                float aux= iDivisaRepository.findById(tipo).get().getDivisaValor();
                for (int i = 0; i < saldoDivisas.size(); i++) {
                    billetera=iBilleteraRepository.getById(saldoDivisas.get(i).getBilleteraId());
                    billetera.setSaldoTotal((-1)*saldoDivisas.get(i).getDivisaSaldo()*aux);
                    iSaldoDivisaRepository.delete(saldoDivisas.get(i));
                }
            }
            iDivisaRepository.deleteById(tipo);
            return true;
        } else
            return false;
    }
}
