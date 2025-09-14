package rc.ac.singidunum.novisad.features.projekat;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.projekat.ProjekatDTO.ProjekatDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class ProjekatMapper implements Mapper<ProjekatDTORecord, Projekat>{

	@Override
	public ProjekatDTORecord map(Projekat e) {
		if(e == null) {
			return null;
		}
		
		ProjekatDTORecord projekatDTO = new ProjekatDTORecord(e.getId(), e.getNaziv(), e.getOpis(), e.getDatumPocetka(), e.getDatumKraja());
		return projekatDTO;
	}
	
}
