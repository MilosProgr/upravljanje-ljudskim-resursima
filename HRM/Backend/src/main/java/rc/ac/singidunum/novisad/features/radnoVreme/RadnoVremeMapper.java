package rc.ac.singidunum.novisad.features.radnoVreme;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.radnoVreme.RadnoVremeDTO.RadnoVremeDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class RadnoVremeMapper implements Mapper<RadnoVremeDTORecord, RadnoVreme> {

	@Override
	public RadnoVremeDTORecord map(RadnoVreme e) {
		if(e == null) {
			return null;
		}
		RadnoVremeDTORecord rvDTO = new RadnoVremeDTORecord(e.getId(),
				new ZaposleniDTO(e.getZaposleni().getId(), e.getZaposleni().getIme(), e.getZaposleni().getPrezime(), e.getZaposleni().getKorisnickoIme(), null, null, null, null, null, null, null, null, null),
				e.getDatum(), e.getVremeDolaska(), e.getVremeOdlaska(), e.getTipRadnogVremena());
		return rvDTO;
	}

}
