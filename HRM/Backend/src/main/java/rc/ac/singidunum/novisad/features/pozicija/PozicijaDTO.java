package rc.ac.singidunum.novisad.features.pozicija;

import rc.ac.singidunum.novisad.features.plata.PlataDTO.PlataDTORecord;


public class PozicijaDTO {
	
	public record PozicijaDTORecord(Long id, String naziv, String opis,PlataDTORecord plata) {}
	
	
}
