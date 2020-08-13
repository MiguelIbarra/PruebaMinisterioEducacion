package cl.prueba.cnt.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



import cl.prueba.cnt.app.service.ISismoService;

@RestController

public class SismoController {

	@Autowired
	private ISismoService iSismoService;
	
	
	
	@GetMapping(path ="/sismosFecha/{fechaInici}/{fechaFin}", produces=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> findSismosFecha(@PathVariable String fechaInici, @PathVariable String fechaFin) {
		
		String sismos = this.iSismoService.getSismosDate(fechaInici, fechaFin);
		
		if (sismos.equals("")) {
			return ResponseEntity.notFound().build();	}

		return ResponseEntity.ok(sismos);
	}
	
	
	
	@GetMapping(path ="/sismosMagnitud/{magInicio}/{magFin}", produces=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> findSismosMagnitud(@PathVariable Double magInicio, @PathVariable Double magFin) {
		
		String sismos = this.iSismoService.getSismosMagnitude(magInicio, magFin);
		
		if (sismos.equals("")) {
			return ResponseEntity.notFound().build();	}

		return ResponseEntity.ok(sismos);
	}
	
	
	
	@PostMapping (path = "/guardarSismoHoy")
	public ResponseEntity<?> saveSismosHoy(){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.iSismoService.saveSismosToday());
	}
}
