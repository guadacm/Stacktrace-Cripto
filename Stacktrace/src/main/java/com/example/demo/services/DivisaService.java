package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Divisa;

public interface DivisaService {

	boolean deleteDivisa(String divisaTipo);

	List<Divisa> getDivisas();

	Divisa getDivisaById(String divisaTipo);

	Divisa registrarDivisa(Divisa divisa);

	Divisa update(Divisa divisa);
}
