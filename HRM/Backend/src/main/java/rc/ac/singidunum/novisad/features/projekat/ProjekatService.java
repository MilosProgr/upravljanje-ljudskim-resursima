package rc.ac.singidunum.novisad.features.projekat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.projekat.ProjekatDTO.ProjekatDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class ProjekatService extends GenericCrudService<ProjekatDTORecord, Projekat, Long> {

	protected ProjekatService(JpaRepository<Projekat, Long> repository, Mapper<ProjekatDTORecord, Projekat> mapper) {
		super(repository, mapper);
	}

}
