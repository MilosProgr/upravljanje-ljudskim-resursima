package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.PlataDTO;
import rc.ac.singidunum.novisad.dto.PozicijaDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Pozicija;

@Component
public class PozicijaMapper implements Mapper<PozicijaDTO, Pozicija> {

	@Override
	public PozicijaDTO map(Pozicija e) {
		if(e == null) {
			return null;
		}
		PozicijaDTO pDto = new PozicijaDTO(
				e.getId(), e.getNaziv(), e.getOpis());
		if(e.getPlata() != null) {
			pDto.setPlata(new PlataDTO(e.getPlata().getId(), e.getPlata().getDatumIsplate(), e.getPlata().getIznos()));
		}
		return pDto;
	}

}
