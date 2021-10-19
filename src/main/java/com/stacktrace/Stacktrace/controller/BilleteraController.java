package com.stacktrace.Stacktrace.controller;

import java.util.List;

import com.stacktrace.Stacktrace.model.Billetera;
import com.stacktrace.Stacktrace.service.BilleteraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Billeteras")
@RestController
public class BilleteraController {

    @Autowired
    BilleteraService billeteraService;
    
    @Operation(summary = "Eliminar billetera con", description = "Ingresar billeteraId. Se eliminan todos los saldos asociados a esa billetera. Devuelve true si se pudo eliminar y false en otro caso")
    @DeleteMapping("/billetera/{billeteraId}")
    public boolean deleteBilletera(@Parameter(description = "Id que desea eliminar. Use 1 al 10 para probar. ", required = true, example = "5")@PathVariable("billeteraId") Long billeteraId){
        return billeteraService.deleteBilletera(billeteraId);
    }

    @Operation(summary = "Listar Billeteras", description =  "Devuelve una lista de todas las billeteras que hay en el sistema")
    @GetMapping("/billetera")
    public List<Billetera> getBilleteras() {
        return billeteraService.getBilleteras();
    }

    @Operation(summary = "Listar Billeteras Huerfanas", description =  "Devuelve una lista de todas las billeteras huerfanas que hay en el sistema")
    @GetMapping("/billetera/huerfanas")
    public List<Billetera> getBilleterasHuerfanas() {
        return billeteraService.getBilleteraByUserId(-1L);
    }

    @Operation(summary = "Buscar una billetera", description = "Ingresar billeteraId. Devuelve la billetera con el id dado y los saldos asociados o null si no se encuentra")
    @GetMapping(value = "/billetera/{billeteraId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    private String getDivisaById(@Parameter(description = "userId que desea buscar. Use 1 al 10 para probar. ", required = true, example = "5")@PathVariable("billeteraId") Long billeteraId) {
        return billeteraService.getBilleteraById(billeteraId);
    }
    

    @Operation(summary = "Registrar una nueva billetera", description="Ingresar un userId. Solo se puede crear billeteras para usuarios existentes y devuelve la billetera creada. Si el usuario no existe devuelve null. La billetera se crea con todas divisas disponibles en el sistema.")
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Billetera.class)) }) })
      @PostMapping(value="/billetera", produces = MediaType.APPLICATION_JSON_VALUE)
      public String registrarBilletera(@RequestBody Long userId) {
        return billeteraService.registrarBilletera(userId);
      }
}
