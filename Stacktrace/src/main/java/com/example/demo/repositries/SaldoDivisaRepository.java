package com.example.demo.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Billetera;
import com.example.demo.models.SaldoDivisa;

public interface SaldoDivisaRepository extends JpaRepository<SaldoDivisa, Long> {
	@Query("FROM SaldoDivisa WHERE divisaTipo = ?1")
	List<SaldoDivisa> findAllByDivisaTipo(String divisaTipo);

	@Query("FROM SaldoDivisa WHERE billetera = ?1")
	List<SaldoDivisa> findByBilletera(Billetera billetera);
	
	@Query("FROM SaldoDivisa WHERE billeteraId = ?1 AND divisaTipo = ?2")
	public SaldoDivisa getSaldoDivisa(Long billeteraId, String divisaTipo);

}
