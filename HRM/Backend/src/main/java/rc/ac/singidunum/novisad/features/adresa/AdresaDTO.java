package rc.ac.singidunum.novisad.features.adresa;

public class AdresaDTO {
	
	
	public record AdresaDTORecord(
	        Long id,
	        String ulica,
	        String broj,
	        String grad,
	        String postanskiBroj,
	        String drzava
	) {}
	 
}
