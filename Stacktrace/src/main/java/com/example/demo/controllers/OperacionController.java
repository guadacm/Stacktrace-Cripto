package com.example.demo.controllers;

import java.util.List;

//import com.stacktrace.Stacktrace.model.Operacion;
//import com.stacktrace.Stacktrace.service.OperacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Deposito;
import com.example.demo.models.Intercambio;
import com.example.demo.services.OperacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Operaciones")
@RestController
public class OperacionController {

	@Autowired
	OperacionService operacionService;

	@Operation(summary = "Consultar saldo de una billetera", description = "Devuelve el saldo de una billetera particular. Si la billetera no existe devuelve -1.")
	@GetMapping(value = "/consulta/billetera{billeteraId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public float getSaldoBilletera(
			@Parameter(description = "billeteraId que desea consultar. Use 1 al 17 para probar. ", required = true, example = "12") @PathVariable("billeteraId") Long billeteraId) {
		return operacionService.getSaldoBilletera(billeteraId);
	}

	@Operation(summary = "Consultar saldo de un usuario", description = "Devuelve el saldo total de todas las billeteras de un usuario. Si el usuario no existe devuelve -1.")
	@GetMapping(value = "/consulta/usuario{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public float getSaldoUsuario(
			@Parameter(description = "userId que desea consultar. Use 1 al 10 para probar. ", required = true, example = "3") @PathVariable("userId") Long userId) {
		return operacionService.getSaldoUsuario(userId);
	}

	@Operation(summary = "Intercambio de divisas", description = "Ingrese los datos en el cuerpo de la peticion. divOrigen y divDestino puede algun tipo de divisa disponible en el sistema. El monto debe ser expresado en la divisa de origen del intercambio. Si el intercambio tiene exito devuelve los datos registrados, sino devuelve null.")
	@ApiResponses(value = { @ApiResponse(description = "successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Intercambio.class)) }) })
	@PostMapping(value = "/intercambio", produces = MediaType.APPLICATION_JSON_VALUE)
	public Intercambio intercambioDivisa(@RequestBody Intercambio intercambio) {
		return operacionService.intercambioDivisa(intercambio);
	}

	@Operation(summary = "Listar intercambios", description = "Devuelve una lista de todos los intercambios realizados")
	@GetMapping("/intercambio")
	public List<Intercambio> getIntercambios() {
		return operacionService.getIntercambios();
	}

	@Operation(summary = "Deposito de divisa", description = "Ingrese los datos en el cuerpo de la peticion. El monto debe ser expresado en pesos. Si el deposito tiene exito devuelve los datos registrados, sino devuelve null")
	@ApiResponses(value = { @ApiResponse(description = "successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Deposito.class)) }) })
	@PostMapping(value = "/deposito", produces = MediaType.APPLICATION_JSON_VALUE)
	public Deposito depositoDivisa(@RequestBody() Deposito deposito) {
		return operacionService.depositoDivisa(deposito);
	}

	@Operation(summary = "Listar depositos", description = "Devuelve una lista de todos los depositos realizados")
	@GetMapping("/deposito")
	public List<Deposito> getDepositos() {
		return operacionService.getDepositos();
	}

}
