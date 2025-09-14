package rc.ac.singidunum.novisad.features.korisnikHandler;

import rc.ac.singidunum.novisad.features.pozicija.PozicijaDTO.PozicijaDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;

public class KorisnikPravoPristupaDTO {
	
	
	public record KorisnikPravoPristupaDTORecord(Long id, PozicijaDTORecord pozicija, ZaposleniDTORecord zaposlen) {}
	
	
}
