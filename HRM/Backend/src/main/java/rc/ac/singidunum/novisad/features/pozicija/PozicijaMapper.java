package rc.ac.singidunum.novisad.features.pozicija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.plata.PlataDTO.PlataDTORecord;
import rc.ac.singidunum.novisad.features.pozicija.PozicijaDTO.PozicijaDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class PozicijaMapper implements Mapper<PozicijaDTORecord, Pozicija> {

	@Override
	public PozicijaDTORecord map(Pozicija e) {
		if(e == null) {
			return null;
		}
		PozicijaDTORecord pDto = new PozicijaDTORecord(
				e.getId(), e.getNaziv(), e.getOpis(), new PlataDTORecord(e.getPlata().getId(), e.getPlata().getDatumIsplate(), e.getPlata().getIznos()));
//		if(e.getPlata() != null) {
//			pDto.setPlata(new PlataDTO(e.getPlata().getId(), e.getPlata().getDatumIsplate(), e.getPlata().getIznos()));
//		}
		return pDto;
	}

}
