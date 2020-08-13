package cl.prueba.cnt.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import cl.prueba.cnt.app.controller.SismoController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebaCntApplicationTests {

	@Autowired
	private SismoController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void sismosFecha() {

		ResponseEntity<?> resp = controller.findSismosFecha("2020-01-02", "2020-01-03");
		System.out.println("Test sismo por fecha: " + resp);
	}

	@Test
	public void sismosMagnitud() {
		ResponseEntity<?> resp = controller.findSismosMagnitud(6.0, 7.0);
		System.out.println("Test sismo por magnitudud: " + resp);
	}
	
	@Test
    public void guardarSismoHoy(){
        ResponseEntity<?> resp = controller.saveSismosHoy();
        System.out.println(resp);      
        
    }

}
