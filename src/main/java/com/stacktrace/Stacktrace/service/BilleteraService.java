package com.stacktrace.Stacktrace.service;

import java.util.ArrayList;
import java.util.List;

import com.stacktrace.Stacktrace.model.Billetera;
import com.stacktrace.Stacktrace.model.Divisa;
import com.stacktrace.Stacktrace.model.SaldoDivisa;
import com.stacktrace.Stacktrace.repository.IBilleteraRepository;
import com.stacktrace.Stacktrace.repository.ISaldoDivisaRepository;
import com.stacktrace.Stacktrace.repository.QueryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilleteraService {

    @Autowired
    IBilleteraRepository iBilleteraRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    DivisaService divisaService;

    @Autowired
    ISaldoDivisaRepository iSaldoDivisaRepository;

    @Autowired
    QueryRepository queryRepository;

    public List<Billetera> getBilleteras() {
        List<Billetera> billeteras = new ArrayList<Billetera>();
        iBilleteraRepository.findAll().forEach(billeteras1 -> billeteras.add(billeteras1));
        return billeteras;
    }

    public String getBilleteraById(Long billeteraId) {
        if (!iBilleteraRepository.findById(billeteraId).isEmpty()) {
            StringBuilder json = new StringBuilder();
            json.append(iBilleteraRepository.findById(billeteraId).get().toStringUserId());
            List<SaldoDivisa> saldoDivisas = queryRepository.findByBilletera(billeteraId);
            if (!saldoDivisas.isEmpty()) {
                for (int i = 0; i < saldoDivisas.size(); i++) {
                    json.append("," + saldoDivisas.get(i).toString());
                }
                json.append("]}");
            }
            return json.toString();
        } else
            return null;
    }

    public void actualizarSaldoTotal(Long billeteraId, float saldo) {
        Billetera actSaldo = iBilleteraRepository.findById(billeteraId).get();
        actSaldo.setSaldoTotal(saldo); // el argumento se acumula con lo que esta en la variable saldoTotal
        iBilleteraRepository.save(actSaldo);
    }

    // Busca todas las billeteras de un mismo usuario
    public List<Billetera> getBilleteraByUserId(Long userId) {

        return queryRepository.findByUserId(userId);

    }

    public boolean deleteBilletera(Long billeteraId) {
        if (!iBilleteraRepository.findById(billeteraId).isEmpty()) {
            List<SaldoDivisa> saldoDivisas = queryRepository.findByBilletera(billeteraId);
            if (!saldoDivisas.isEmpty()) {
                for (int i = 0; i < saldoDivisas.size(); i++) {
                    iSaldoDivisaRepository.delete(saldoDivisas.get(i));
                }
            }
            iBilleteraRepository.deleteById(billeteraId);
            return true;
        } else
            return false;
    }

    // Se crea una billetera con todas las divisas asociadas con saldo 0
    public String registrarBilletera(Long userId) {
        if (usuarioService.getUsuarioById(userId) != null) {
            Billetera nueva = new Billetera(userId);
            nueva = iBilleteraRepository.save(nueva);
            List<Divisa> divisas = divisaService.getDivisas();
            for (int i = 0; i < divisas.size(); i++) {
                SaldoDivisa ad = new SaldoDivisa(nueva.getBilleteraId(), divisas.get(i).getDivisaTipo());
                iSaldoDivisaRepository.save(ad);
            }
            return nueva.toString();
        }
        return null;
    }

    public Billetera update(Billetera billetera) {
        if (!iBilleteraRepository.findById(billetera.getBilleteraId()).isEmpty()) {
            return iBilleteraRepository.save(billetera);
        } else
            return null;
    }

}
