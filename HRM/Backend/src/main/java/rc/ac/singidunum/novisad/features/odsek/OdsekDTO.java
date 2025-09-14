package rc.ac.singidunum.novisad.features.odsek;

import java.util.Set;

import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;

public class OdsekDTO {
	
	public record OdsekDTORecord(Long id,String naziv,String opis, Set<ZaposleniDTORecord> zaposleni) {}
	
}
