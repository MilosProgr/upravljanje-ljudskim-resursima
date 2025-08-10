package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.RadnoVremeDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.RadnoVreme;

@Component
public class RadnoVremeMapper implements Mapper<RadnoVremeDTO, RadnoVreme> {

	@Override
	public RadnoVremeDTO map(RadnoVreme e) {
		if(e == null) {
			return null;
		}
		RadnoVremeDTO rvDTO = new RadnoVremeDTO(e.getId(),
				new ZaposleniDTO(e.getZaposleni().getId(), e.getZaposleni().getIme(), e.getZaposleni().getPrezime(), e.getZaposleni().getKorisnickoIme(), null, null, null, null, null, null, null, null, null),
				e.getDatum(), e.getVremeDolaska(), e.getVremeOdlaska(), e.getTipRadnogVremena());
		return rvDTO;
	}

}
