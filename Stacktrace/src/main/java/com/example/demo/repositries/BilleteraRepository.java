package com.example.demo.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Billetera;

public interface BilleteraRepository extends JpaRepository<Billetera, Long> {

	@Query("FROM Billetera WHERE userId = ?1")
	List<Billetera> findByUserId(Long userId);

	@Query("FROM Billetera WHERE userId = NULL")
	List<Billetera> billeterasHuerfanas();
	
	@Query("SELECT SUM(saldoTotal) FROM Billetera WHERE userId = ?1")
	float getSaldoUsuario(Long userId);
}
