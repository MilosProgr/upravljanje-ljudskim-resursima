package rc.ac.singidunum.novisad.features.plata;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.plata.PlataDTO.PlataDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class PlataMapper implements Mapper<PlataDTORecord, Plata>{

	@Override
	public PlataDTORecord map(Plata e) {
		if(e == null) {
			return null;
		}
		
		PlataDTORecord pDto = new PlataDTORecord(e.getId(), e.getDatumIsplate(), e.getIznos());
		
//		if( e.getZaposleni() != null) {
//			pDto.setZaposleni(new ZaposleniDTO(e.getZaposleni().getId(), e.getZaposleni().getIme(), e.getZaposleni().getPrezime(), e.getZaposleni().getKorisnickoIme(), null, null, null, null, null, null, null, null, null));
//		}
		return pDto;
	}

}
