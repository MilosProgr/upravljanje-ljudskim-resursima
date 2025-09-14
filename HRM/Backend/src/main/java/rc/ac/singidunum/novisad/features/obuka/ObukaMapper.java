package rc.ac.singidunum.novisad.features.obuka;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.obuka.ObukaDTO.ObukaDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class ObukaMapper implements Mapper<ObukaDTORecord, Obuka> {

	@Override
	public ObukaDTORecord map(Obuka e) {
		if(e == null) {
			return null;
		}
		ObukaDTORecord obukaDTO = new ObukaDTORecord(
				e.getId(),
				e.getNaziv(), e.getOpis(), e.getDatumOdrzavanja(), 
				new ZaposleniDTORecord(e.getZaposleni().getId(), e.getZaposleni().getIme(), e.getZaposleni().getPrezime(), e.getZaposleni().getKorisnickoIme(), null, null, null, null, null, null, null, null, null, null, null) );
		return obukaDTO;
	}

}
