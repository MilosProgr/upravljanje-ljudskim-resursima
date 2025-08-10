package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.ObukaDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Obuka;

@Component
public class ObukaMapper implements Mapper<ObukaDTO, Obuka> {

	@Override
	public ObukaDTO map(Obuka e) {
		if(e == null) {
			return null;
		}
		ObukaDTO obukaDTO = new ObukaDTO(
				e.getId(),
				e.getNaziv(), e.getOpis(), e.getDatumOdrzavanja(), 
				new ZaposleniDTO(e.getZaposleni().getId(), e.getZaposleni().getIme(), e.getZaposleni().getPrezime(), e.getZaposleni().getKorisnickoIme(), null, null, null, null, null, null, null, null, null) );
		return obukaDTO;
	}

}
