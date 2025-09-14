package rc.ac.singidunum.novisad.features.odsek;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.odsek.OdsekDTO.OdsekDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class OdsekMapper implements Mapper<OdsekDTORecord, Odsek> {

	@Override
	public OdsekDTORecord map(Odsek e) {
		if(e == null) {
			return null;
		}
		
		Set<ZaposleniDTORecord> zaposleni = null;
		if(e.getZaposleni()!= null) {
			zaposleni = e.getZaposleni().stream()
				    .map(zaposlen ->
				        new ZaposleniDTORecord(
				            zaposlen.getId(),
				            zaposlen.getIme(),
				            zaposlen.getPrezime(),
				            zaposlen.getKorisnickoIme(),
				            zaposlen.getLozinka(),
				            zaposlen.getEmail(),
				            zaposlen.getTelefon(),
				            zaposlen.getDatumRodjenja(),
				            zaposlen.getDatumZaposlenja(),
				            zaposlen.getPol(),
				            zaposlen.getStatusZaposlenja(),
				            null,
				            null,
				            null,
				            null
				        )
				    ).collect(Collectors.toSet());					
		}
		OdsekDTORecord oDto = new OdsekDTORecord(e.getId(), e.getNaziv(), e.getOpis(),zaposleni);
		return oDto;
	}

}
