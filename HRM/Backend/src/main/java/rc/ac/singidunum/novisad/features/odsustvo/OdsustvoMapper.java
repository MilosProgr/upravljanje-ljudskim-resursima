package rc.ac.singidunum.novisad.features.odsustvo;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.odsustvo.OdsustvoDTO.OdsustvoDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class OdsustvoMapper implements Mapper<OdsustvoDTORecord, Odsustvo> {

	
	@Override
	public OdsustvoDTORecord map(Odsustvo e) {
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

	    return new OdsustvoDTORecord(
	        e.getId(),
	        zaposleniDTO,
	        e.getTip(), // TipOdsustva – adjust if needed
	        e.getDatumPocetka(),
	        e.getDatumKraja(),
	        odobrioDTO
	    );
	}

}
