package com.example.demo.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Operacion;
import com.example.demo.models.SaldoDivisa;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {

	@Query("SELECT SUM(saldoTotal) FROM Billetera WHERE userId = ?1")
	float getSaldoUsuario(Long userId);

	@Query("FROM SaldoDivisa WHERE billeteraId = ?1 AND divisaTipo = ?2")
	public SaldoDivisa getSaldoDivisa(Long billeteraId, String divisaTipo);

	@Query("FROM Operacion WHERE opTipo = 'INTERCAMBIO'")
	List<Operacion> getIntercambios();

	@Query("FROM Operacion WHERE opTipo = 'DEPOSITO'")
	public List<Operacion> getDepositos();
}