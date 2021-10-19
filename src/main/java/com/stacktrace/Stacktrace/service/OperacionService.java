package com.stacktrace.Stacktrace.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.stacktrace.Stacktrace.model.Billetera;
import com.stacktrace.Stacktrace.model.Operacion;
import com.stacktrace.Stacktrace.model.SaldoDivisa;
import com.stacktrace.Stacktrace.repository.IBilleteraRepository;
import com.stacktrace.Stacktrace.repository.IDivisaRepository;
import com.stacktrace.Stacktrace.repository.IOperacionRepository;
import com.stacktrace.Stacktrace.repository.ISaldoDivisaRepository;
import com.stacktrace.Stacktrace.repository.IUsuarioRepository;
import com.stacktrace.Stacktrace.repository.QueryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperacionService {

    @Autowired
    IOperacionRepository iOperacionRepository;

    @Autowired
    IBilleteraRepository iBilleteraRepository;

    @Autowired
    QueryRepository queryRepository;
    @Autowired
    IDivisaRepository iDivisaRepository;
    @Autowired
    ISaldoDivisaRepository iSaldoDivisaRepository;
    @Autowired
    IUsuarioRepository iUsuarioRepository;

    // INTERCAMBIO DE DIVISAS
    public Operacion intercambioDivisa(Operacion operacion) {
        operacion.toUpperCase();
        operacion.setOpTipo("INTERCAMBIO");
        Billetera billeteraOrigen = iBilleteraRepository.findById(operacion.getbOrigen()).get();
        Billetera billeteraDestino = iBilleteraRepository.findById(operacion.getbDestino()).get();
        if (operacion.getDivOrigen().equals("PESO")) {
            if (operacion.getMonto() <= billeteraOrigen.getPesos()) {
                // actualizo origen
                billeteraOrigen.setPesos((-1) * operacion.getMonto());
                billeteraOrigen.setSaldoTotal((-1) * operacion.getMonto());
                iBilleteraRepository.save(billeteraOrigen);
                if (operacion.getDivDestino().equals("PESO")) { // PESO-PESO
                    // actualizo destino
                    billeteraDestino.setPesos(operacion.getMonto());
                    billeteraDestino.setSaldoTotal(operacion.getMonto());
                    iBilleteraRepository.save(billeteraDestino);
                } else { // PESO-DIV
                    SaldoDivisa saldoDivisaDestino = queryRepository.getSaldo(operacion.getbDestino(),
                            operacion.getDivDestino());
                    float costoDestino = iDivisaRepository.getById(operacion.getDivDestino()).getDivisaValor();
                    float deposito = (operacion.getMonto() / costoDestino);
                    // actualizo destino
                    saldoDivisaDestino.setDivisaSaldo(deposito);
                    billeteraDestino.setSaldoTotal(deposito);
                    iSaldoDivisaRepository.save(saldoDivisaDestino);
                    iBilleteraRepository.save(billeteraDestino);
                }

            } else {
                return null;
            }
        } else {
            SaldoDivisa saldoDivisaOrigen = queryRepository.getSaldo(operacion.getbOrigen(), operacion.getDivOrigen());
            float costoOrigen = iDivisaRepository.getById(operacion.getDivOrigen()).getDivisaValor();
            if (operacion.getMonto() <= saldoDivisaOrigen.getDivisaSaldo()) {
                // actualizo origen
                saldoDivisaOrigen.setDivisaSaldo((-1) * operacion.getMonto());
                iSaldoDivisaRepository.save(saldoDivisaOrigen);
                billeteraOrigen.setSaldoTotal((-1) * operacion.getMonto());
                iBilleteraRepository.save(billeteraOrigen);
                if (operacion.getDivDestino().equals("PESO")) { // DIV-PESO
                    float deposito = operacion.getMonto() * costoOrigen;
                    // actualizo destino
                    billeteraDestino.setPesos(deposito);
                    billeteraDestino.setSaldoTotal(deposito);
                    iBilleteraRepository.save(billeteraDestino);
                } else { // DIV-DIV
                    SaldoDivisa saldoDivisaDestino = queryRepository.getSaldo(operacion.getbDestino(),
                            operacion.getDivDestino());
                    float costoDestino = iDivisaRepository.getById(operacion.getDivDestino()).getDivisaValor();
                    float deposito = (operacion.getMonto() * costoOrigen) / costoDestino;
                    // actualizo destino
                    saldoDivisaDestino.setDivisaSaldo(deposito);
                    billeteraDestino.setSaldoTotal(deposito);
                    iSaldoDivisaRepository.save(saldoDivisaDestino);
                    iBilleteraRepository.save(billeteraDestino);
                }
            } else {
                return null;
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        operacion.setOpFechaHora(dtf.format(LocalDateTime.now()));
        return iOperacionRepository.save(operacion);
    }

    // DEPOSITO
    public Operacion depositoDivisa(Operacion operacion) {
        operacion.toUpperCase();
        operacion.setOpTipo("DEPOSITO");
        if (!iBilleteraRepository.findById(operacion.getbDestino()).isEmpty()) {
            Billetera billeteraDestino = iBilleteraRepository.findById(operacion.getbDestino()).get();
            if (operacion.getDivOrigen().equals("PESO")) {
                if (operacion.getDivDestino().equals("PESO")) { // PESO-PESO
                    billeteraDestino.setPesos(operacion.getMonto());
                    billeteraDestino.setSaldoTotal(operacion.getMonto());
                    iBilleteraRepository.save(billeteraDestino);
                } else { // PESO-DIV
                    SaldoDivisa saldoDivisaDestino = queryRepository.getSaldo(operacion.getbDestino(),
                            operacion.getDivDestino());

                    float costoDestino = iDivisaRepository.getById(operacion.getDivDestino()).getDivisaValor();
                    float deposito = operacion.getMonto() / costoDestino;
                    saldoDivisaDestino.setDivisaSaldo(deposito);
                    billeteraDestino.setSaldoTotal(deposito*costoDestino);
                    iSaldoDivisaRepository.save(saldoDivisaDestino);
                    iBilleteraRepository.save(billeteraDestino);

                }
            } else { // DIV-DIV
                SaldoDivisa saldoDivisaDestino = queryRepository.getSaldo(operacion.getbDestino(),
                        operacion.getDivDestino());
                float costoOrigen = iDivisaRepository.getById(operacion.getDivOrigen()).getDivisaValor();
                float costoDestino = iDivisaRepository.getById(operacion.getDivDestino()).getDivisaValor();
                float deposito = (operacion.getMonto() * costoOrigen) / costoDestino;
                // actualizo destino
                saldoDivisaDestino.setDivisaSaldo(deposito);
                billeteraDestino.setSaldoTotal(deposito*costoDestino);
                iSaldoDivisaRepository.save(saldoDivisaDestino);
                iBilleteraRepository.save(billeteraDestino);
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            operacion.setOpFechaHora(dtf.format(LocalDateTime.now()));
            operacion.setbOrigen(-1L);
            return iOperacionRepository.save(operacion);

        } else
            return null;
    }

    public List<Operacion> getIntercambios() {
        return queryRepository.getIntercambios();

    }

    public List<Operacion> getDepositos() {
        return queryRepository.getDepositos();

    }

    public float getSaldoBilletera(Long billeteraId) {
        if(!iBilleteraRepository.findById(billeteraId).isEmpty()) return iBilleteraRepository.findById(billeteraId).get().getSaldoTotal();
        else return -1;
    }

    public float getSaldoUsuario(Long userId) {
        if(!iUsuarioRepository.findById(userId).isEmpty()) return queryRepository.getSaldoUsuario(userId);
        else return -1;

    }

}
