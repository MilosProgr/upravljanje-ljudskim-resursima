package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.OdsustvoDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Odsustvo;

@Component
public class OdsustvoMapper implements Mapper<OdsustvoDTO, Odsustvo> {

	
	@Override
	public OdsustvoDTO map(Odsustvo e) {
	    if (e == null) {
	        return null;
	    }

	    ZaposleniDTO zaposleniDTO = new ZaposleniDTO(
	        e.getZaposleni().getId(),
	        e.getZaposleni().getIme(),
	        e.getZaposleni().getPrezime(),
	        e.getZaposleni().getKorisnickoIme(),
	        null, null, null, null, null, null, null, null, null
	    );

	    ZaposleniDTO odobrioDTO = null;
	    if (e.getOdobrio() != null) {
	        odobrioDTO = new ZaposleniDTO(
	            e.getOdobrio().getId(),
	            e.getOdobrio().getIme(),
	            e.getOdobrio().getPrezime(),
	            e.getOdobrio().getKorisnickoIme(),
	            null, null, null, null, null, null, null, null, null
	        );
	    }

	    return new OdsustvoDTO(
	        e.getId(),
	        zaposleniDTO,
	        e.getTip(), // TipOdsustva – adjust if needed
	        e.getDatumPocetka(),
	        e.getDatumKraja(),
	        odobrioDTO
	    );
	}

}
