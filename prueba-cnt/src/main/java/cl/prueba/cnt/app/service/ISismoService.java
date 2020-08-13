package cl.prueba.cnt.app.service;

public interface ISismoService {
	
	public String getSismosDate (String fechaIni, String fechaFin);
	
	public String getSismosMagnitude (Double magInicial, Double magFin);
	
	public String saveSismosToday ();

}
