package cl.prueba.cnt.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import cl.prueba.cnt.app.entity.Sismo;
import cl.prueba.cnt.app.repository.SismoRepository;


@Service
public class SismoServiceImpl implements ISismoService {
	
	@Value("${url.base.sismo}")
	private String urlBase;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private SismoRepository sismoRepository;

	

	@Override
	public String getSismosDate(String fechaIni, String fechaFin) {
		String sismosFecha="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(fechaIni);
			end = sdf.parse(fechaFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(start.before(end) || start.equals(end)){
			fechaIni = fechaIni + "T00:00:00";
			fechaFin = fechaFin + "T23:59:59";
			sismosFecha = this.restTemplate.getForEntity(urlBase + "starttime=" + fechaIni +"&endtime=" + fechaFin, String.class).getBody();		
			
		}else{
			sismosFecha = "Fecha de inicio debe ser menor o igual a la Fecha fin";
		}
						
		return sismosFecha;
	}



	@Override
	public String getSismosMagnitude(Double magInicial, Double magFin) {
		
		String sismosMagnitud="";
		
		if(magInicial < magFin || magInicial.equals(magFin)  ){
		
		sismosMagnitud = this.restTemplate.getForEntity(urlBase +"minmagnitude=" + magInicial + "&maxmagnitude=" + magFin, String.class).getBody();
		}else{
			sismosMagnitud = "Magnitud de inicio debe ser menor o igual a la Magnitud fin";
		}
		
		return sismosMagnitud;
	}



	@Override
	public String saveSismosToday() {
		
		int i=0;
		String respuesta="";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaIni = sdf.format(date);
		String fechaFin = sdf.format(date);
		
		String sismosHoy = getSismosDate(fechaIni, fechaFin);
		
		
		try {
			
			JSONParser jParser = new JSONParser(sismosHoy);
	        LinkedHashMap<?, ?> linkedHashMap = (LinkedHashMap<?, ?>) jParser.parse();
	        List<?> lista = (List<?>) linkedHashMap.get("features");

	        for (Object sism : lista) {
	            LinkedHashMap<?, ?> feature = (LinkedHashMap<?, ?>) sism;
	            LinkedHashMap<?, ?> propiedades = (LinkedHashMap<?, ?>) feature.get("properties");
	            LinkedHashMap<?, ?> geometry = (LinkedHashMap<?, ?>) feature.get("geometry");
	            

	            Sismo sismo = new Sismo();
	            sismo.setMagnitud(propiedades.get("mag").toString());
	            sismo.setLugar(propiedades.get("place").toString());
	            sismo.setTiempo(propiedades.get("time").toString());
	            sismo.setUrlInfo(propiedades.get("url").toString());
	            sismo.setEstado(propiedades.get("status").toString());
	            sismo.setCoordenadas(geometry.get("coordinates").toString());
	            sismo.setFecha(sdf.format(date));
	            respuesta = sismoRepository.save(sismo).toString();
	            
	            i++;
	        }
			
		} catch (org.apache.tomcat.util.json.ParseException e) {
			
			e.printStackTrace();
		}
        System.out.println("respuesta: "+respuesta);
		
		return (i + " sismos guardados del dia de hoy "+sdf.format(date));
	}
	
	
	
	

}
