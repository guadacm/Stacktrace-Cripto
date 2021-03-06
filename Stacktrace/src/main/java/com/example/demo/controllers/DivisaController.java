package com.example.demo.controllers;

import java.util.List;

//import com.stacktrace.Stacktrace.model.Divisa;
//import com.stacktrace.Stacktrace.service.DivisaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Divisa;
import com.example.demo.services.DivisaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Divisas")
@RestController
public class DivisaController {

	@Autowired
	DivisaService divisaService;

	@Operation(summary = "Eliminar divisa", description = "Ingresar tipo de divisa a eliminar. Devuelve true si se pudo eliminar y false en otro caso. Las billeteras existentes pierden el saldo que tenian en la divisa eliminada.")
	@DeleteMapping("/divisa/{divisaTipo}")
	public boolean deleteDivisa(
			@Parameter(description = "Tipo de divisa que desea eliminar. Use BAT para probar. ", required = true, example = "BAT") @PathVariable("divisaTipo") String divisaTipo) {
		return divisaService.deleteDivisa(divisaTipo);
	}

	@Operation(summary = "Listar divisas", description = "Devuelve una lista de todas las divisas que hay en el sistema")
	@GetMapping("/divisa")
	public List<Divisa> getDivisas() {
		return divisaService.getDivisas();
	}

	@Operation(summary = "Buscar una divisa", description = "Ingresar tipo de divisa. Devuelve la divisa del tipo dado o null si no se encuentra")
	@GetMapping(value = "/divisa/{divisaTipo}", produces = MediaType.APPLICATION_JSON_VALUE)
	private Divisa getDivisaById(
			@Parameter(description = "Tipo de divisa que desea buscar.", required = true, example = "BTC") @PathVariable("divisaTipo") String divisaTipo) {
		return divisaService.getDivisaById(divisaTipo);
	}

	@Operation(summary = "Registrar una nueva divisa", description = "Ingresar datos de la divisa nueva. Si el registro tuvo exito devuelve la divisa registrada y la agrega a todas billeteras existentes, sino devuelve null")
	@ApiResponses(value = { @ApiResponse(description = "successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Divisa.class)) }) })
	@PostMapping(value = "/divisa", produces = MediaType.APPLICATION_JSON_VALUE)
	public Divisa registrarDivisa(@RequestBody Divisa divisa) {
		return divisaService.registrarDivisa(divisa);
	}

	@Operation(summary = "Actualizar valor de divisa", description = "Ingresar datos actualizados en el cuerpo de la peticion. Solo se actualiza el valor de la divisa. Devuelve la divisa con el nuevo valor o null si la divisa no existe.")
	@ApiResponses(value = { @ApiResponse(description = "successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Divisa.class)) }) })
	@PutMapping(value = "/divisa", produces = MediaType.APPLICATION_JSON_VALUE)
	private Divisa update(@RequestBody Divisa divisa) {
		return divisaService.update(divisa);
	}
}
