package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.PlataDTO;
//import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Plata;

@Component
public class PlataMapper implements Mapper<PlataDTO, Plata>{

	@Override
	public PlataDTO map(Plata e) {
		if(e == null) {
			return null;
		}
		
		PlataDTO pDto = new PlataDTO(e.getId(), e.getDatumIsplate(), e.getIznos());
		
//		if( e.getZaposleni() != null) {
//			pDto.setZaposleni(new ZaposleniDTO(e.getZaposleni().getId(), e.getZaposleni().getIme(), e.getZaposleni().getPrezime(), e.getZaposleni().getKorisnickoIme(), null, null, null, null, null, null, null, null, null));
//		}
		return pDto;
	}

}
