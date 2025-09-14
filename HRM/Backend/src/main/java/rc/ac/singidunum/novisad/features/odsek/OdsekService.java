package rc.ac.singidunum.novisad.features.odsek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.odsek.OdsekDTO.OdsekDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class OdsekService extends GenericCrudService<OdsekDTORecord, Odsek,Long> {

	protected OdsekService(JpaRepository<Odsek, Long> repository, Mapper<OdsekDTORecord, Odsek> mapper) {
		super(repository, mapper);
	}

}
